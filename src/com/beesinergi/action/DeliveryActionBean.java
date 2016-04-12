package com.beesinergi.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Delivery;
import com.beesinergi.model.DeliveryDetail;
import com.beesinergi.model.Po;
import com.beesinergi.model.Product;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.DeliveryService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.PoService;
import com.beesinergi.service.ProductService;
import com.beesinergi.service.VendorService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class DeliveryActionBean extends BaseMaintenanceActionBean<Delivery> {
	
	private String LIST_PAGE = "/WEB-INF/pages/delivery/deliveryList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/delivery/deliveryDetail.jsp";
	
	@SpringBean
	private PoService poService;
	@SpringBean
	private DeliveryService deliveryService;
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
		if (getModel().getPkDelivery() != null){
			Delivery delivery = deliveryService.findById(getModel().getPkDelivery());
			jsonUtil.addData("poDetailList", delivery.getDeliveryDetailList());
		} else{
			Po po = poService.findById(getModel().getFkPo());
			jsonUtil.addData("poDetailList", po.getPoDetailList());
		}
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Delivery model = getModel();
		if (model.getDeliveryDetailList() == null){
			addLocalizableErrorSimpleParam("err.required", getLocalizeableMessage("label.productList"));
		} else{
			for (DeliveryDetail detail:model.getDeliveryDetailList()){
				if (detail.getQty() > detail.getOutstanding()){
					addLocalizableErrorSimpleParam("err.delivery1", detail.getProductName());
				}
				if (detail.getQty() > detail.getProductStock()){
					addLocalizableErrorSimpleParam("err.delivery2", detail.getProductName());
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

	@Override
	protected CommonService<Delivery> getCommonService() {
		return deliveryService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.DELIVERY);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
