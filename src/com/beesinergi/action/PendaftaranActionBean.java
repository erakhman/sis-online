package com.beesinergi.action;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.PendaftaranService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class PendaftaranActionBean extends BaseMaintenanceActionBean<Pendaftaran> {
	
	private String LIST_PAGE = "/WEB-INF/pages/pendaftaran/pendaftaranList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/pendaftaran/pendaftaranDetail.jsp";
	private String RE_REGISTER_PAGE = "/WEB-INF/pages/pendaftaran/pendaftaranUlang.jsp";
	
	@SpringBean
	private PendaftaranService pendaftaranService;
	@SpringBean
	private PelajaranService pelajaranService;
	
	private String namaPelajaran;
	private List<Integer> pkPendaftaranList;
	

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public Resolution showReRegister() {
		return new ForwardResolution(RE_REGISTER_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Pendaftaran model = getModel();
	}
	
	public Resolution doExamPass() {
		try {
			pendaftaranService.examPass(pkPendaftaranList);
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
	}
	
	public Resolution doReRegister() {
		try {
			pendaftaranService.reRegister(getModel().getPkPendaftaran());
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
	}
	
	public Resolution doGetPelajaranList() {
		JSONUtil jsonUtil = new JSONUtil();
		List<Pelajaran> list = pelajaranService.findAll(null);
		jsonUtil.addData("pelajaranList", list);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution doGetPelajaranDetail() {
		JSONUtil jsonUtil = new JSONUtil();
		Pelajaran pelajaran = pelajaranService.findByName(namaPelajaran);
		jsonUtil.addData("pelajaran", pelajaran);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
	}

	@Override
	protected CommonService<Pendaftaran> getCommonService() {
		return pendaftaranService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PENDAFTARAN);
	}

	public String getNamaPelajaran() {
		return namaPelajaran;
	}

	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
	}

	public List<Integer> getPkPendaftaranList() {
		return pkPendaftaranList;
	}

	public void setPkPendaftaranList(List<Integer> pkPendaftaranList) {
		this.pkPendaftaranList = pkPendaftaranList;
	}


}
