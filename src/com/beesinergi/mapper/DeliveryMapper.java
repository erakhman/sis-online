package com.beesinergi.mapper;

import com.beesinergi.model.Delivery;

public interface DeliveryMapper extends CommonMapper<Delivery> {
	
	public Delivery selectByPrimaryKey(int pkDelivery);
	
	public void makeNonEditable(int fkPo);
	
}
