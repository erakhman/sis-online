package com.beesinergi.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Lookup;
import com.beesinergi.model.Po;
import com.beesinergi.model.PoOut;
import com.beesinergi.model.PoOutDetail;
import com.beesinergi.model.Product;
import com.beesinergi.model.Vendor;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.PoOutService;
import com.beesinergi.service.PoService;
import com.beesinergi.service.ProductService;
import com.beesinergi.service.VendorService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class PoOutActionBean extends BaseMaintenanceActionBean<PoOut> {
	
	private String LIST_PAGE = "/WEB-INF/pages/poOut/poOutList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/poOut/poOutDetail.jsp";
	
	@SpringBean
	private PoService poService;
	@SpringBean
	private PoOutService poOutService;
	@SpringBean
	private ProductService productService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private VendorService vendorService;
	
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
	
	public Resolution doGetPoDetail() {
		JSONUtil jsonUtil = new JSONUtil();
		if (getModel().getFkPo() != null){
			Po po = poService.findById(getModel().getFkPo());
			jsonUtil.addData("poDetailList", po.getPoDetailList());
		} else{
			PoOut poOut = poOutService.findById(getModel().getPkPoOut());
			jsonUtil.addData("poDetailList", poOut.getPoOutDetailList());
		}
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		PoOut model = getModel();
		if (model.getPoOutDetailList() == null){
			addLocalizableErrorSimpleParam("err.required", getLocalizeableMessage("label.productList"));
		} else{
			for (PoOutDetail detail : model.getPoOutDetailList()){
				if (detail.getQty() > detail.getReqQty()){
					addLocalizableErrorSimpleParam("err.poOut1", detail.getProductName());
				}
			}
		}
	}
	
	public List<Po> getPoList() {
		Po param = new Po();
		List<String> statusList = new ArrayList<String>();
		statusList.add(SystemConstant.PoStatus.APPROVED);
		statusList.add(SystemConstant.PoStatus.OUTSTANDING);
		param.setStatusList(statusList);
		List<Po> list = poService.findAll(param);
		return list;
	}
	
	public List<Vendor> getVendorList() {
		List<Vendor> list = vendorService.findAll(new Vendor());
		return list;
	}
	
	public List<Lookup> getCategoryList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.CATEGORY);
		return list;
	}

	@Override
	protected CommonService<PoOut> getCommonService() {
		return poOutService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PO_OUT);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
