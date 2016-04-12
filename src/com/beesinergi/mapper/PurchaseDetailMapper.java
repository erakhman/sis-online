package com.beesinergi.mapper;

import com.beesinergi.model.PurchaseDetail;

public interface PurchaseDetailMapper extends CommonMapper<PurchaseDetail> {
	
	public void deleteByFkPurchase(int fkPurchase);
	
}
