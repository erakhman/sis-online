package com.beesinergi.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.HasilUjianMasuk;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Soal;
import com.beesinergi.model.UjianMasuk;
import com.beesinergi.model.UjianMasukDetail;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.SoalService;
import com.beesinergi.service.UjianMasukService;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

public class UjianMasukActionBean extends BaseMaintenanceActionBean<UjianMasuk> {
	
	private String DEFAULT_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasuk.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukDetail.jsp";
	private String RESULT_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukResult.jsp";
	
	@SpringBean
	private UjianMasukService ujianMasukService;
	@SpringBean
	private PelajaranService pelajaranService;
	@SpringBean
	private SoalService soalService;
	
	private Soal soal;

	@Override
	public Resolution show() {
		return new ForwardResolution(DEFAULT_PAGE);
	}
	
	public Resolution showDetail() {
		String session = (String) getSessionAttribute(SystemConstant.SESSION_WAKTU_UJIAN);
		if (session == null){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, SystemParameter.DURASI_UJIAN);
			String date = DateTimeFunction.date2String(cal.getTime(), "dd MMMMM yyyy HH:mm:ss");
			setSessionAttribute(SystemConstant.SESSION_WAKTU_UJIAN, date);
		}
		return super.showDetail();
	}
	
	public Resolution showResult() {
		return new ForwardResolution(RESULT_PAGE);
	}
	
	public Resolution doSave() {
		setSessionAttribute(SystemConstant.SESSION_WAKTU_UJIAN, null);
		return super.doSave();
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		UjianMasuk model = new UjianMasuk();
//		if (model.getUjianMasukCode() == null){ 
//			addLocalizableError("err.required", "label.ujianMasukCode");
//		}
	}
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
	}
	
	public List<Soal> getSoalList() {
		return soalService.findAll(soal);
	}
	
	public HasilUjianMasuk getHasilUjian() {
		List<UjianMasuk> list = ujianMasukService.findAll(getModel());
		HasilUjianMasuk hasilUjian = null;
		if (!list.isEmpty()){
			UjianMasuk ujianMasuk = list.get(0);
			hasilUjian = new HasilUjianMasuk();
			int jumlahSoal = ujianMasuk.getUjianMasukDetailList().size();
			hasilUjian.setJumlahSoal(jumlahSoal);
			int jawabanBenar = 0;
			int jawabanSalah = 0;
			for (UjianMasukDetail detail:ujianMasuk.getUjianMasukDetailList()){
				if (detail.getJawabanSoal().equalsIgnoreCase(detail.getJawabanSiswa())){
					jawabanBenar++;
				} else{
					jawabanSalah++;
				}
			}
			hasilUjian.setJawabanBenar(jawabanBenar);
			hasilUjian.setJawabanSalah(jawabanSalah);
			double score = (jawabanBenar/jumlahSoal)*100;
			hasilUjian.setScore(score);
		}
		return hasilUjian;
	}
	
	@Override
	protected CommonService<UjianMasuk> getCommonService() {
		return ujianMasukService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}	

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.UJIAN_MASUK);
	}

	public Soal getSoal() {
		return soal;
	}

	public void setSoal(Soal soal) {
		this.soal = soal;
	}
}
