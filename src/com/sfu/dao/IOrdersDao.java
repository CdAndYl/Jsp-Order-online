package com.sfu.dao;

import java.util.List;

import com.sfu.entity.Orders;
import com.sfu.utils.PageBean;

public interface IOrdersDao {

	void update(Orders orders);
	
	List<Orders> query();

	void add(Orders orders);
	
	int getCount();

	void getAll(PageBean<Orders> pb);

	int getTotalCount();
}
