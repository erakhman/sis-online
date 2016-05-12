package com.beesinergi.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.AppUser;
import com.beesinergi.model.HasilUjianMasuk;
import com.beesinergi.model.JadwalUjian;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.model.Soal;
import com.beesinergi.model.UjianMasuk;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.HasilUjianMasukService;
import com.beesinergi.service.JadwalUjianService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.PendaftaranService;
import com.beesinergi.service.SoalService;
import com.beesinergi.service.UjianMasukService;
import com.beesinergi.service.UserService;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

public class UjianMasukActionBean extends BaseMaintenanceActionBean<UjianMasuk> {
	
	private String DEFAULT_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasuk.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukDetail.jsp";
	private String RESULT_PAGE = "/WEB-INF/pages/ujianMasuk/ujianMasukResult.jsp";
	private String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";
	
	@SpringBean
	private UjianMasukService ujianMasukService;
	@SpringBean
	private HasilUjianMasukService hasilUjianMasukService;
	@SpringBean
	private PelajaranService pelajaranService;
	@SpringBean
	private PendaftaranService pendaftaranService;
	@SpringBean
	private SoalService soalService;
	@SpringBean
	private UserService userService;
	@SpringBean
	private JadwalUjianService jadwalUjianService;
	
	private HasilUjianMasuk hasilUjian;
	private JadwalUjian jadwalUjian;

	@Override
	public Resolution show() {
		if (getModel() == null){
			populateJadwalUjian();
			populateUjianMasuk();
			List<UjianMasuk> list = ujianMasukService.findAll(getModel());
			if (!list.isEmpty()){
				addSimpleError("Anda sudah melakukan ujian!");
				getContext().getSession().invalidate();
				return new ForwardResolution(LOGIN_PAGE);
			}
		}
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
		HasilUjianMasuk param = new HasilUjianMasuk();
		param.setFkPendaftaran(getModel().getFkPendaftaran());
		param.setFkPelajaran(getModel().getFkPelajaran());
		hasilUjian = hasilUjianMasukService.findAll(param).get(0);
		return new ForwardResolution(RESULT_PAGE);
	}
	
	public Resolution doSave() {
		setSessionAttribute(SystemConstant.SESSION_WAKTU_UJIAN, "0");
		return super.doSave();
	}
	
	public void populateUjianMasuk() {
		UjianMasuk ujianMasuk = new UjianMasuk();
		Pendaftaran param = new Pendaftaran();
		param.setUserName(getUserInfo().getUserName());
		Pendaftaran pendaftaran = pendaftaranService.findAll(param).get(0);
		ujianMasuk.setFkPendaftaran(pendaftaran.getPkPendaftaran());
		ujianMasuk.setFkPelajaran(jadwalUjian.getFkPelajaran());
		setModel(ujianMasuk);
	}
	
	public void populateJadwalUjian() {
		JadwalUjian param = new JadwalUjian();
		param.setIsActive(SystemConstant.YES);
		param.setFkLookupType(SystemConstant.JadwalUjianType.UJIAN_MASUK);
		jadwalUjian = jadwalUjianService.findAll(param).get(0);
	}
	
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
	}
	
	public List<Soal> getSoalList() {
		Soal soal = new Soal();
		soal.setFkPelajaran(getModel().getFkPelajaran());
		return soalService.findAll(soal);
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

	public HasilUjianMasuk getHasilUjian() {
		return hasilUjian;
	}
	
	public void setHasilUjian(HasilUjianMasuk hasilUjian) {
		this.hasilUjian = hasilUjian;
	}

	public JadwalUjian getJadwalUjian() {
		return jadwalUjian;
	}

	public void setJadwalUjian(JadwalUjian jadwalUjian) {
		this.jadwalUjian = jadwalUjian;
	}
	
}
