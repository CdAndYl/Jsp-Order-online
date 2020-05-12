package com.sfu.service.impl;

import java.util.List;

import com.sfu.dao.IOrderDetailDao;
import com.sfu.entity.OrderDetail;
import com.sfu.factory.BeanFactory;
import com.sfu.service.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService{

	IOrderDetailDao dao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

	@Override
	public void add(OrderDetail od) {
		dao.add(od);
	}

	@Override
	public List<OrderDetail> query() {
		return dao.query();
	}
	@Override
	public List<OrderDetail> findByOrderId(int id) {
		return dao.findByOrderid(id);
	}
}
