package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Bodega;


public interface BodegaDao {

	Bodega findByIdb(int id);
	
	Bodega findByBODEG(String bodeg);
	
	void saveb(Bodega bodega);
	
	void deleteByBODEG(String bodeg);
	
	List<Bodega> findAllBodegas();

}

