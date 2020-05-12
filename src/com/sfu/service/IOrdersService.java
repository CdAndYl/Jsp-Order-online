package com.sfu.service;

import java.util.List;

import com.sfu.entity.Orders;
import com.sfu.utils.PageBean;

public interface IOrdersService {
	
	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
	
	public void getAll(PageBean<Orders> pb);
}
