package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Driver;
import com.beesinergi.model.Lookup;
import com.beesinergi.model.Reservation;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.DriverService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.ReservationService;
import com.beesinergi.util.SystemConstant;

public class ReservationActionBean extends BaseMaintenanceActionBean<Reservation> {
	
	private String LIST_PAGE = "/WEB-INF/pages/reservation/reservationList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/reservation/reservationDetail.jsp";
	
	@SpringBean
	private ReservationService reservationService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private DriverService driverService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Reservation model = getModel();
		if (model.getDateFrom() == null){ 
			addLocalizableError("err.required", "label.dateFrom");
		}
		if (model.getDateTo() == null){
			addLocalizableError("err.required", "label.dateTo");
		}
	}
	
	public List<Driver> getDriverList() {
		List<Driver> list = driverService.findAll(new Driver());
		return list;
	}
	
	public List<Lookup> getRoomList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.ROOM);
		return list;
	}
	
	public List<Lookup> getCarList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.CAR);
		return list;
	}

	@Override
	protected CommonService<Reservation> getCommonService() {
		return reservationService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.RESERVATION);
	}

}
