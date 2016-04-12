package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

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
	
	@SpringBean
	private PendaftaranService pendaftaranService;
	@SpringBean
	private PelajaranService pelajaranService;
	
	private String namaPelajaran;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Pendaftaran model = getModel();
//		if (model.getPendaftaranCode() == null){ 
//			addLocalizableError("err.required", "label.pendaftaranCode");
//		}
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

}
