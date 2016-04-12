package com.beesinergi.mapper;

import com.beesinergi.model.PoOut;
import com.beesinergi.model.Purchase;

public interface PurchaseMapper extends CommonMapper<Purchase> {
	
	public Purchase selectByPrimaryKey(int pkPurchase);
	
}
