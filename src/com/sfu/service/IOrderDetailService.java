package com.sfu.service;

import java.util.List;

import com.sfu.entity.OrderDetail;

public interface IOrderDetailService {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderId(int id);
}
