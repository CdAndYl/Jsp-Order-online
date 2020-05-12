package com.sfu.dao;

import java.util.List;

import com.sfu.entity.OrderDetail;

public interface IOrderDetailDao {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(int id);
}
