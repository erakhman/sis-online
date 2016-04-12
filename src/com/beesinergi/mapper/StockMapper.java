package com.beesinergi.mapper;

import com.beesinergi.model.Stock;

public interface StockMapper extends CommonMapper<Stock> {
	
	public void deleteByTrxNo(String trxNo);
	
}