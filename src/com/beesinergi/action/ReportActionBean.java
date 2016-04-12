/*package com.beesinergi.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.model.Barang;
import com.beesinergi.model.Customer;
import com.beesinergi.model.Jaminan;
import com.beesinergi.model.Lookup;
import com.beesinergi.model.Pembelian;
import com.beesinergi.model.Penjualan;
import com.beesinergi.model.Report;
import com.beesinergi.model.Stock;
import com.beesinergi.service.BarangService;
import com.beesinergi.service.CustomerService;
import com.beesinergi.service.JaminanService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.ReportService;
import com.beesinergi.util.SystemConstant.ReportType;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemParameter;

@UrlBinding("/action/report")
@PermitAll
public class ReportActionBean extends BaseActionBean {
	
	protected static final Log log = LogFactory.getLog(ReportActionBean.class);
	
	private String REPORT_PAGE = "/WEB-INF/pages/report/report.jsp";
	
	@SpringBean
	private ReportService reportService;
	@SpringBean
	private BarangService barangService;
	@SpringBean
	private JaminanService jaminanService;
	@SpringBean
	private CustomerService customerService;
	@SpringBean
	private LookupService lookupService;
	
	private Report report;
	
	public static void main(String[] a){
		StringBuilder contentSb	= new StringBuilder();
		
		contentSb.append("NO TRANSAKSI: x\n");
		contentSb.append("NO STNK: x\n");
		
		contentSb.append("KATEGORI: x\n");
		
		contentSb.append("JML LOKER\t: x\n");
		
		contentSb.append("HARGA\t\t: Rp. x\n");
		contentSb.append("JAM MASUK\t: x\n");
		System.out.println(contentSb.toString());
    }

	@Override
	public Resolution show() {
		try {
			if (report != null){
				if (report.getDateFrom() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(report.getDateFrom());
					report.setDateFrom(DateTimeFunction.addDate(cal.getTime(), -1, Calendar.DAY_OF_MONTH));
					report.setDateTo(DateTimeFunction.addDate(cal.getTime(), +1, Calendar.DAY_OF_MONTH));
				}
				if (report.getReportType().equals(ReportType.PEMBELIAN)){
					exportPembelian();
				} else if (report.getReportType().equals(ReportType.PENJUALAN)){
					exportPenjualan();
				} else if (report.getReportType().equals(ReportType.STOCK)){
					exportStock();
				} 
				return null;
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(REPORT_PAGE);
	}
	
	public void exportStock() throws JRException, IOException {
		List<JasperPrint> pages = new ArrayList<JasperPrint>();
		
		String sourceStockBarang = SystemParameter.REPORT_TEMPLATE+"stock_barang.jasper";
		List<Stock> barangList = reportService.getStockBarang(report);
		JasperPrint jasperPrintStockBarang = JasperFillManager.fillReport(sourceStockBarang, new HashMap<String,Object>(), new JRBeanCollectionDataSource(barangList));
		
		String sourceStockJaminan = SystemParameter.REPORT_TEMPLATE+"stock_jaminan.jasper";
		List<Stock> jaminanList = reportService.getStockJaminan(report);
		JasperPrint jasperPrintStockJaminan = JasperFillManager.fillReport(sourceStockJaminan, new HashMap<String,Object>(), new JRBeanCollectionDataSource(jaminanList));
		
		pages.add(jasperPrintStockBarang);
		pages.add(jasperPrintStockJaminan);
		
		JRPdfExporter exp = new JRPdfExporter();
        exp.setParameter(JRExporterParameter.JASPER_PRINT_LIST, pages);
        exp.setParameter(JRExporterParameter.OUTPUT_STREAM, getContext().getResponse().getOutputStream());
        exp.exportReport();	
	}
	
	public void exportPembelian() throws JRException, IOException {
		String sourceFileName = SystemParameter.REPORT_TEMPLATE+"pembelian/pembelian.jasper";
		List<Pembelian> list = reportService.getPembelian(report);
		
		HashMap<String,Object> param = new HashMap<String, Object>();
		param.put("SUBREPORT_DIR", SystemParameter.REPORT_TEMPLATE+"pembelian/");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, param, new JRBeanCollectionDataSource(list));
		JRPdfExporter exp = new JRPdfExporter();
        exp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exp.setParameter(JRExporterParameter.OUTPUT_STREAM, getContext().getResponse().getOutputStream());
        exp.exportReport();	
	}
	
	public void exportPenjualan() throws JRException, IOException {
		List<JasperPrint> pages = new ArrayList<JasperPrint>();
		
		String sourcePenjualanBarang = SystemParameter.REPORT_TEMPLATE+"penjualan/penjualan_barang.jasper";
		String sourcePenjualanJaminan = SystemParameter.REPORT_TEMPLATE+"penjualan/penjualan_jaminan.jasper";
		
		List<Barang> barangList = reportService.getBarang(report);
		List<Jaminan> jaminanList = reportService.getJaminan(report);
		HashMap<String,Object> param = new HashMap<String, Object>();
		param.put("SUBREPORT_DIR", SystemParameter.REPORT_TEMPLATE+"penjualan/");
		param.put("barangList", barangList);
		param.put("jaminanList", jaminanList);
		HashMap<String,Object> penjualanSummary = reportService.getPenjualanSummary(report);
		if (penjualanSummary != null){
			param.put("totalPenjualan", penjualanSummary.get("total_penjualan"));
			param.put("totalCash", penjualanSummary.get("total_cash"));
			param.put("totalVoucher", penjualanSummary.get("total_voucher"));
			param.put("totalPenitipan", penjualanSummary.get("total_penitipan"));
		}
		
		List<Integer> fkBarangList = new ArrayList<Integer>();
		for (Barang barang:barangList){
			fkBarangList.add(barang.getPkBarang());
		}
		if (fkBarangList.size() > 0){
			report.setFkBarangList(fkBarangList);
		}
		List<Integer> fkJaminanList = new ArrayList<Integer>();
		for (Jaminan jaminan:jaminanList){
			fkJaminanList.add(jaminan.getPkJaminan());
		}
		if (fkJaminanList.size() > 0){
			report.setFkJaminanList(fkJaminanList);
		}
		List<Penjualan> listPenjualan = reportService.getPenjualan(report);
		
		JasperPrint jasperPrintPenjBarang = JasperFillManager.fillReport(sourcePenjualanBarang, param, new JRBeanCollectionDataSource(listPenjualan));
		JasperPrint jasperPrintPenjJaminan = JasperFillManager.fillReport(sourcePenjualanJaminan, param, new JRBeanCollectionDataSource(listPenjualan));
		
        pages.add(jasperPrintPenjBarang);
        pages.add(jasperPrintPenjJaminan);
        
        JRPdfExporter exp = new JRPdfExporter();
        exp.setParameter(JRExporterParameter.JASPER_PRINT_LIST, pages);
        exp.setParameter(JRExporterParameter.OUTPUT_STREAM, getContext().getResponse().getOutputStream());
        exp.exportReport();	
	}
	
	public Resolution doGetBarangList() {
		JSONUtil jsonUtil = new JSONUtil();
		Barang barang = new Barang();
		barang.setFkLookup(report.getFkLookup());
		List<Barang> list = barangService.findAll(barang);
		jsonUtil.addData("barangList", list);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution doGetJaminanList() {
		JSONUtil jsonUtil = new JSONUtil();
		List<Jaminan> list = jaminanService.findAll(new Jaminan());
		jsonUtil.addData("jaminanList", list);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public List<Lookup> getLookupList() {
		List<Lookup> list = lookupService.findAllByName(Lookup.LookupName.CATEGORY_BARANG);
		return list;
	}
	
	public List<Barang> getBarangList() {
		Barang barang = new Barang();
		barang.setFkLookup(report.getFkLookup());
		List<Barang> list = barangService.findAll(barang);
		return list;
	}
	
	public List<Jaminan> getJaminanList() {
		List<Jaminan> list = jaminanService.findAll(new Jaminan());
		return list;
	}
	
	public List<Customer> getCustomerList() {
		List<Customer> list = customerService.findAll(new Customer());
		return list;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
*/