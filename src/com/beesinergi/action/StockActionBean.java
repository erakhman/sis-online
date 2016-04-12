package com.beesinergi.action;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.model.Lookup;
import com.beesinergi.model.Product;
import com.beesinergi.model.Stock;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.ProductService;
import com.beesinergi.service.StockService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class StockActionBean extends BaseMaintenanceActionBean<Stock> {
	
	private String LIST_PAGE = "/WEB-INF/pages/stock/stockList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/stock/stockDetail.jsp";
	
	@SpringBean
	private StockService stockService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private ProductService productService;
	
	private Product product;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public Resolution doAdjustment() {
		try {
			for (Stock stock:getModel().getProductList()){
				int trxQty = stock.getEndStock() - stock.getStartStock(); 
				productService.adjustmentQty(stock.getFkProduct(), trxQty, getModel().getTrxDate(), "ADJUSTMENT");
			}
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (SystemException e) {
			for (ErrorHolder error : e.getErrors()){
				addSimpleError(error.getError());
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
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
		Stock model = getModel();
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
	protected CommonService<Stock> getCommonService() {
		return stockService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.STOCK);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
