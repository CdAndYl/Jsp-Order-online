package com.sfu.dao;

import java.util.List;

import com.sfu.entity.DinnerTable;

public interface IDinnerTableDao {

	void add(DinnerTable dt);
	
	void delete(int id);
	
	void updata(DinnerTable dt);
	
	List<DinnerTable> query();

	DinnerTable findById(int id);

	List<DinnerTable> query(String keyword);
	
	void quitTable(int id);
}
