package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.LibraryMember;
import com.beesinergi.model.Siswa;
import com.beesinergi.model.Staff;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LibraryMemberService;
import com.beesinergi.service.SiswaService;
import com.beesinergi.service.StaffService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

public class LibraryMemberActionBean extends BaseMaintenanceActionBean<LibraryMember> {

	private String LIST_PAGE = "/WEB-INF/pages/master/librarymember/libraryMemberList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/librarymember/libraryMemberDetail.jsp";
	
	@SpringBean 
	private LibraryMemberService libraryMemberService;
	@SpringBean 
	private SiswaService siswaService;
	@SpringBean 
	private StaffService staffService;
	
	private Integer memberType;
	
	public Resolution doGetMemberTypeDetail() {
		JSONUtil jsonUtil = new JSONUtil();
		LibraryMember member = new LibraryMember();
		if (getModel().getMemberType().equals(SystemConstant.MemberType.STUDENT)){
			Siswa siswa = siswaService.findById(memberType);
			member.setMemberName(siswa.getNamaSiswa());
			member.setMemberIdentityNo(siswa.getNis());
		} else{
			Staff staff = staffService.findById(memberType);
			member.setMemberName(staff.getName());
			member.setMemberIdentityNo(staff.getCode());
		} 
		jsonUtil.addData("member", member);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	@Override
	protected CommonService<LibraryMember> getCommonService() {
		return libraryMemberService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public List<Siswa> getStudentList() {
		List<Siswa> list = siswaService.findAll(null);
		return list;
	}
	
	public List<Staff> getTeacherList() {
		List<Staff> list = staffService.findAllTeacher();
		return list;
	}
	
	public List<Staff> getAdminList() {
		List<Staff> list = staffService.findAllAdmin();
		return list;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.LIBRARY_MEMBER);
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

}
