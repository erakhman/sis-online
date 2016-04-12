package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.AppStatus;
import com.beesinergi.model.Employee;
import com.beesinergi.model.Lookup;
import com.beesinergi.model.Po;
import com.beesinergi.model.Product;
import com.beesinergi.service.AppStatusService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.EmployeeService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.PoService;
import com.beesinergi.service.ProductService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class PoActionBean extends BaseMaintenanceActionBean<Po> {
	
	private String LIST_PAGE = "/WEB-INF/pages/po/poList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/po/poDetail.jsp";
	
	@SpringBean
	private PoService poService;
	@SpringBean
	private ProductService productService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private AppStatusService appStatusService;
	@SpringBean
	private EmployeeService employeeService;
	
	private Product product;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public Resolution doGetProductList() {
		JSONUtil jsonUtil = new JSONUtil();
		List<Product> list = productService.findAll(product);
		jsonUtil.addData("productList", list);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution doGetProductDetail() {
		JSONUtil jsonUtil = new JSONUtil();
		List<Product> list = productService.findAll(product);
		Product product = !list.isEmpty() ? list.get(0) : null;
		jsonUtil.addData("product", product);
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Po model = getModel();
	}
	
	public List<AppStatus> getStatusList() {
		List<AppStatus> list = appStatusService.findAllByType(SystemConstant.AppStatusType.PO);
		return list;
	}
	
	public List<Lookup> getCategoryList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.CATEGORY);
		return list;
	}
	
	public List<Lookup> getUnitList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.UNIT);
		return list;
	}

	@Override
	protected CommonService<Po> getCommonService() {
		return poService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PO);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
