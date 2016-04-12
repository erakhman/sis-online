package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.service.CommonService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.Paging;

public abstract class BaseMaintenanceActionBean<T> extends BaseActionBean implements ValidationErrorHandler {
	
	protected static final Log log = LogFactory.getLog(BaseMaintenanceActionBean.class);
	
	protected abstract CommonService<T> getCommonService();
	protected abstract String getDetailPage();
	
	private int maintenanceId;
	private T model;
	
	public Resolution doSave() {
		try {
			getCommonService().save(model);
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

	public Resolution doGetList() {
		JSONUtil jsonUtil = new JSONUtil();
		Paging regList = getCommonService().findAllByPaging(getPaging(),model);
		jsonUtil.addData("maintenanceList", regList.getResult());
		int count = getCommonService().getCount(model);
		jsonUtil.addData("paging", populatePaging(count));
		return new StreamingResolution("text/html", jsonUtil.serialize());
	}
	
	public Resolution showDetail() {
		if (maintenanceId != 0){
			setModel(getCommonService().findById(maintenanceId));
		}
		return new ForwardResolution(getDetailPage());
	}
	
	public Resolution handleValidationErrors(ValidationErrors errors) {
        return new ForwardResolution(AJAX_RESULT_PAGE);
    }

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public int getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

}
