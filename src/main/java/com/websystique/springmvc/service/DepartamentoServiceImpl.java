package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.BodegaDao;
import com.websystique.springmvc.model.Bodega;


@Service("bodegaService")
@Transactional
public class BodegaServiceImpl implements BodegaService{

	@Autowired
	private BodegaDao dao;

//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	public Bodega findByIdb(int id) {
		return dao.findByIdb(id);
	}

	public Bodega findByBODEG(String bodeg) {
		Bodega bodega = dao.findByBODEG(bodeg);
		return bodega;
	}

	public void saveBodega(Bodega bodega) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.saveb(bodega);
	}

	
	public void updateBodega(Bodega bodega) {
		Bodega entity = dao.findByIdb(bodega.getId());
		if(entity!=null){
			entity.setBodegId(bodega.getBodegId());
			
			entity.setUbicacion(bodega.getUbicacion());
			entity.setDescripcion(bodega.getDescripcion());
			entity.setFechaIngreso(bodega.getFechaIngreso());
			//entity.setCorreo(user.getCorreo());
			//entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	

	public List<Bodega> findAllBodegas() {
		return dao.findAllBodegas();
	}

	public boolean isBodegaBODEGUnique(Integer id, String bodeg) {
		Bodega bodega = findByBODEG(bodeg);
		return ( bodega == null || ((id != null) && (bodega.getId() == id)));
	}

    @Override
    public void deleteBodegaByBODEG(String bodeg) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       dao.deleteByBODEG(bodeg);
    }
	
}
