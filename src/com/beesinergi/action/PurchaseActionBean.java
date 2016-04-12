package com.beesinergi.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Po;
import com.beesinergi.model.PoOut;
import com.beesinergi.model.Product;
import com.beesinergi.model.Purchase;
import com.beesinergi.model.Vendor;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.PoOutService;
import com.beesinergi.service.ProductService;
import com.beesinergi.service.PurchaseService;
import com.beesinergi.service.VendorService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class PurchaseActionBean extends BaseMaintenanceActionBean<Purchase> {
	
	private String LIST_PAGE = "/WEB-INF/pages/purchase/purchaseList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/purchase/purchaseDetail.jsp";
	
	@SpringBean
	private PoOutService poOutService;
	@SpringBean
	private VendorService vendorService;
	@SpringBean
	private PurchaseService purchaseService;
	@SpringBean
	private ProductService productService;
	@SpringBean
	private LookupService lookupService;
	
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
	
	public Resolution doGetPoOutDetail() {
		JSONUtil jsonUtil = new JSONUtil();
		if (getModel().getFkPoOut() != null){
			PoOut po = poOutService.findById(getModel().getFkPoOut());
			jsonUtil.addData("poOutDetailList", po.getPoOutDetailList());
		} else{
			Purchase purchase = purchaseService.findById(getModel().getPkPurchase());
			jsonUtil.addData("poOutDetailList", purchase.getPurchaseDetailList());
		}
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Purchase model = getModel();
		if (model.getPurchaseNo() == null){ 
			addLocalizableError("err.required", "label.purchaseNo");
		}
		if (model.getPaidDate() == null){
			addLocalizableError("err.required", "label.paidDate");
		}
		if (model.getDueDate() == null){
			addLocalizableError("err.required", "label.dueDate");
		}
		if (model.getPayment() == null){
			addLocalizableError("err.required", "label.payment");
		}
		if (model.getOutstanding() == null){
			addLocalizableError("err.required", "label.qty");
		}
	}
	
	public List<PoOut> getPoOutList() {
		PoOut param = new PoOut();
		param.setStatus(SystemConstant.PoOutStatus.NEW);
		List<PoOut> list = poOutService.findAll(param);
		return list;
	}
	
	public List<Vendor> getVendorList() {
		List<Vendor> list = vendorService.findAll(new Vendor());
		return list;
	}

	@Override
	protected CommonService<Purchase> getCommonService() {
		return purchaseService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PURCHASE);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
