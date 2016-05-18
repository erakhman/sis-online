package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Kelas;
import com.beesinergi.model.Siswa;
import com.beesinergi.model.Staff;
import com.beesinergi.service.KelasService;
import com.beesinergi.service.SiswaService;
import com.beesinergi.service.StaffService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

public class SearchActionBean extends BaseActionBean {

	private String STUDENT_LIST = "/WEB-INF/pages/search/studentList.jsp";
	private String TEACHER_LIST = "/WEB-INF/pages/search/teacherList.jsp";
	private String STAFF_LIST = "/WEB-INF/pages/search/staffList.jsp";
	
	@SpringBean 
	private SiswaService siswaService;
	@SpringBean 
	private KelasService kelasService;
	@SpringBean 
	private StaffService staffService;
	
	private Siswa student;
	private Staff teacher;
	private Staff staff;
	
	@Override
	public Resolution show() {
		return new ForwardResolution(STUDENT_LIST);
	}
	
	public Resolution showTeacherList() {
		return new ForwardResolution(TEACHER_LIST);
	}
	
	public Resolution showStaffList() {
		return new ForwardResolution(STAFF_LIST);
	}
	
	public Resolution doGetStudentList() {
		JSONUtil jsonUtil = new JSONUtil();
		Paging regList = siswaService.findAllByPaging(getPaging(),student);
		jsonUtil.addData("studentList", regList.getResult());
		int count = siswaService.getCount(student);
		jsonUtil.addData("paging", populatePaging(count));
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution doGetTeacherList() {
		JSONUtil jsonUtil = new JSONUtil();
		teacher = teacher == null ? new Staff() : teacher;
		teacher.setType(SystemConstant.StaffType.TEACHER);
		Paging regList = staffService.findAllByPaging(getPaging(),teacher);
		jsonUtil.addData("teacherList", regList.getResult());
		int count = staffService.getCount(teacher);
		jsonUtil.addData("paging", populatePaging(count));
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution doGetStaffList() {
		JSONUtil jsonUtil = new JSONUtil();
		staff = staff == null ? new Staff() : staff;
		staff.setType(SystemConstant.StaffType.ADMIN);
		Paging regList = staffService.findAllByPaging(getPaging(),staff);
		jsonUtil.addData("staffList", regList.getResult());
		int count = staffService.getCount(staff);
		jsonUtil.addData("paging", populatePaging(count));
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public List<Kelas> getKelasList() {
		List<Kelas> list = kelasService.findAll(null);
		return list;
	}

	@Override
	public String getPageTitle() {return null;}

	public Siswa getStudent() {
		return student;
	}

	public void setStudent(Siswa student) {
		this.student = student;
	}

	public Staff getTeacher() {
		return teacher;
	}

	public void setTeacher(Staff teacher) {
		this.teacher = teacher;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
