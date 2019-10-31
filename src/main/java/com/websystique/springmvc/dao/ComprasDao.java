package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Compras;


public interface ComprasDao {

	Compras findByIdc(int id);
	
	Compras findByCOM(String com);
	
	void savec(Compras compras);
	
	void deleteByCOM(String com);
	
	List<Compras> findAllCompras();

}

