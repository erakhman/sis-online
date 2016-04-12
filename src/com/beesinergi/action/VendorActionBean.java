package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Vendor;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.VendorService;
import com.beesinergi.util.SystemConstant;

public class VendorActionBean extends BaseMaintenanceActionBean<Vendor> {
	
	private String LIST_PAGE = "/WEB-INF/pages/vendor/vendorList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/vendor/vendorDetail.jsp";
	
	@SpringBean
	private VendorService vendorService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Vendor model = getModel();
		if (model.getVendorCode() == null){ 
			addLocalizableError("err.required", "label.vendorCode");
		}
		if (model.getVendorName() == null){ 
			addLocalizableError("err.required", "label.vendorName");
		}
		if (model.getAddress() == null){ 
			addLocalizableError("err.required", "label.address");
		}
		if (model.getCity() == null){ 
			addLocalizableError("err.required", "label.city");
		}
		if (model.getProvince() == null){ 
			addLocalizableError("err.required", "label.province");
		}
		if (model.getPostalCode() == null){ 
			addLocalizableError("err.required", "label.postalCode");
		}
		if (model.getCountry() == null){ 
			addLocalizableError("err.required", "label.country");
		}
		if (model.getPhoneNo() == null){ 
			addLocalizableError("err.required", "label.phoneNo");
		}
		if (model.getFaxNo() == null){ 
			addLocalizableError("err.required", "label.faxNo");
		}
		if (model.getBankName() == null){ 
			addLocalizableError("err.required", "label.bankName");
		}
		if (model.getAccountNo() == null){ 
			addLocalizableError("err.required", "label.accountNo");
		}
		if (model.getAccountName() == null){ 
			addLocalizableError("err.required", "label.accountName");
		}
		if (model.getContactPerson() == null){ 
			addLocalizableError("err.required", "label.contactPerson");
		}
		if (model.getEmail() == null){ 
			addLocalizableError("err.required", "label.email");
		}
	}

	@Override
	protected CommonService<Vendor> getCommonService() {
		return vendorService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.VENDOR);
	}

}
