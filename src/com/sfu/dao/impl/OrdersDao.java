package com.sfu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sfu.dao.IOrdersDao;
import com.sfu.entity.Orders;
import com.sfu.utils.JdbcUtils;
import com.sfu.utils.PageBean;

public class OrdersDao implements IOrdersDao{

	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	@Override
	public void add(Orders orders) {
		String sql =" INSERT orders(table_id,orderDate,totalPrice) VALUES(?,?,?)";
		try {
			qr.update(sql,orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getCount(){
		String sql ="select count(*) from orders";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void update(Orders orders) {
		String sql = "UPDATE orders SET orderStatus =? WHERE id=?";
		try {
			qr.update(sql,orders.getOrderStatus(),orders.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		
	}

	@Override
	public List<Orders> query() {
		String sql = "SELECT * FROM orders";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void getAll(PageBean<Orders> pb) {
		
		//2. ��ѯ�ܼ�¼��;  ���õ�pb������
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);
		
		/*
		 * ���⣺ jspҳ�棬���ǰҳΪ��ҳ���ٵ����һҳ���?
		 *              ���ǰҳΪĩҳ���ٵ���һҳ��ʾ�����⣡
		 * �����
		 * 	   1. ���ǰҳ <= 0;       ��ǰҳ���õ�ǰҳΪ1;
		 * 	   2. ���ǰҳ > ���ҳ��  ��ǰҳ����Ϊ���ҳ��
		 */
		// �ж�
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // �ѵ�ǰҳ����Ϊ1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		// �ѵ�ǰҳ����Ϊ���ҳ��
		}
		
		//1. ��ȡ��ǰҳ�� �����ѯ����ʼ�С����ص�����
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// ��ѯ����ʼ��
		int count = pb.getPageCount();							// ��ѯ���ص�����
		
		
		//3. ��ҳ��ѯ���;  �Ѳ�ѯ����������õ�pb������
		String sql = "select * from orders limit ?,?";
		
		try {
			// ��ݵ�ǰҳ����ѯ��ǰҳ���(һҳ���)
			List<Orders> pageData = qr.query(sql, new BeanListHandler<Orders>(Orders.class), index, count);
			// ���õ�pb������
			pb.setPageData(pageData);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from orders";
		try {
			// ִ�в�ѯ�� ���ؽ��ĵ�һ�еĵ�һ��
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
