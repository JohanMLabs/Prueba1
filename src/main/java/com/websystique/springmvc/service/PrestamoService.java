package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Bodega;


public interface BodegaService {
	
	Bodega findByIdb(int id);
	
	Bodega findByBODEG(String bodeg);
	
	void saveBodega(Bodega bodega);
	
	void updateBodega(Bodega bodega);
	
	void deleteBodegaByBODEG(String bodeg);

	List<Bodega> findAllBodegas(); 
	
	boolean isBodegaBODEGUnique(Integer id, String bodeg);

}