package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Compras;


public interface ComprasService {
	
	Compras findByIdc(int id);
	
	Compras findByCOM(String com);
	
	void saveCompras(Compras com);
	
	void updateCompras(Compras com);
	
	void deleteComprasByCOM(String com);

	List<Compras> findAllCompras(); 
	
	boolean isComprasCOMUnique(Integer id, String com);

}