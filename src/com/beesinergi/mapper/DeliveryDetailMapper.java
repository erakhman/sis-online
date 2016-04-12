package com.beesinergi.mapper;

import com.beesinergi.model.DeliveryDetail;

public interface DeliveryDetailMapper extends CommonMapper<DeliveryDetail> {
	
	public void deleteByFkDelivery(int fkDelivery);
	
}
