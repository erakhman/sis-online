package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Lookup;
import com.beesinergi.model.Product;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.ProductService;
import com.beesinergi.util.SystemConstant;

public class ProductActionBean extends BaseMaintenanceActionBean<Product> {
	
	private String LIST_PAGE = "/WEB-INF/pages/product/productList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/product/productDetail.jsp";
	
	@SpringBean
	private ProductService productService;
	@SpringBean
	private LookupService lookupService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Product model = getModel();
		if (model.getProductCode() == null){ 
			addLocalizableError("err.required", "label.productCode");
		}
		if (model.getProductName() == null){
			addLocalizableError("err.required", "label.productName");
		}
		if (model.getBasicPrice() == null){
			addLocalizableError("err.required", "label.basicPrice");
		}
		if (model.getSellPrice() == null){
			addLocalizableError("err.required", "label.sellPrice");
		}
		if (model.getQty() == null){
			addLocalizableError("err.required", "label.qty");
		}
		if (model.getMerk() == null){
			addLocalizableError("err.required", "label.merk");
		}
		if (model.getMadeIn() == null){
			addLocalizableError("err.required", "label.madeIn");
		}
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
	protected CommonService<Product> getCommonService() {
		return productService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PRODUCT);
	}

}
