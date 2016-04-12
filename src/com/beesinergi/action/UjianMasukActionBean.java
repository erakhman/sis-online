package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Soal;
import com.beesinergi.model.UjianMasuk;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.SoalService;
import com.beesinergi.service.UjianMasukService;
import com.beesinergi.util.SystemConstant;

public class UjianMasukActionBean extends BaseMaintenanceActionBean<UjianMasuk> {
	
	private String SEARCH_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukSearch.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukDetail.jsp";
	
	@SpringBean
	private UjianMasukService ujianMasukService;
	@SpringBean
	private PelajaranService pelajaranService;
	@SpringBean
	private SoalService soalService;
	
	private UjianMasuk ujianMasuk;
	private Soal soal;
	private Boolean isAlreadyExam;
	private List<Soal> soalList;
	private List<String> jawabanList;
	private Integer fkPelajaran;

	@Override
	public Resolution show() {
		return new ForwardResolution(SEARCH_PAGE);
	}
	
	public Resolution showDetail() {
		ujianMasuk.setFkPelajaran(fkPelajaran);
		List<UjianMasuk> list = ujianMasukService.findAll(ujianMasuk);
		if (!list.isEmpty()){
			ujianMasuk = list.get(0);
			isAlreadyExam = true;
		} else{
			isAlreadyExam = false;
		}
		soal = new Soal();
		soal.setFkPelajaran(fkPelajaran);
		soalList = soalService.findAll(soal);
		return super.showDetail();
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		UjianMasuk model = new UjianMasuk();
//		if (model.getUjianMasukCode() == null){ 
//			addLocalizableError("err.required", "label.ujianMasukCode");
//		}
	}
	
	public Resolution doSave() {
		try {
			ujianMasuk.setFkPelajaran(fkPelajaran);
			isAlreadyExam = !ujianMasukService.findAll(ujianMasuk).isEmpty() ? true : false;
			if (isAlreadyExam){
				throw new SystemException(new ErrorHolder("Jawaban hanya boleh di submit satu kali!"));
			}
			ujianMasukService.save(ujianMasuk);
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (SystemException e) {
			for (ErrorHolder error : e.getErrors()){
				addSimpleError(error.getError());
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return showDetail();
	}
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
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
	
	public UjianMasuk getUjianMasuk() {
		return ujianMasuk;
	}

	public void setUjianMasuk(UjianMasuk ujianMasuk) {
		this.ujianMasuk = ujianMasuk;
	}

	public List<Soal> getSoalList() {
		return soalList;
	}
	
	public void setSoalList(List<Soal> soalList) {
		this.soalList = soalList;
	}

	public List<String> getJawabanList() {
		return jawabanList;
	}

	public void setJawabanList(List<String> jawabanList) {
		this.jawabanList = jawabanList;
	}

	public Boolean getIsAlreadyExam() {
		return isAlreadyExam;
	}

	public void setIsAlreadyExam(Boolean isAlreadyExam) {
		this.isAlreadyExam = isAlreadyExam;
	}

	public Soal getSoal() {
		return soal;
	}

	public void setSoal(Soal soal) {
		this.soal = soal;
	}

	public Integer getFkPelajaran() {
		return fkPelajaran;
	}

	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
	}

	

	

}
