package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.ComprasDao;
import com.websystique.springmvc.model.Compras;


@Service("comprasService")
@Transactional
public class ComprasServiceImpl implements ComprasService{

	@Autowired
	private ComprasDao dao;

//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	public Compras findByIdc(int id) {
		return dao.findByIdc(id);
	}

	public Compras findByCOM(String com) {
		Compras compras = dao.findByCOM(com);
		return compras;
	}

	public void saveCompras(Compras compras) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.savec(compras);
	}

	
	public void updateCompras(Compras compras) {
		Compras entity = dao.findByIdc(compras.getId());
		if(entity!=null){
			entity.setNombreId(compras.getNombreId());
			
		        entity.setDescripcion(compras.getDescripcion());
			entity.setFechaCompra(compras.getFechaCompra());
			
		}
	}

	
	

	public List<Compras> findAllCompras() {
		return dao.findAllCompras();
	}

	public boolean isComprasCOMUnique(Integer id, String com) {
		Compras compras = findByCOM(com);
		return ( compras == null || ((id != null) && (compras.getId() == id)));
	}

    @Override
    public void deleteComprasByCOM(String com) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         dao.deleteByCOM(com);
    }

    
   

  
}
