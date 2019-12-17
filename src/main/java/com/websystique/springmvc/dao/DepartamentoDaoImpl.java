package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Bodega;



@Repository("bodegaDao")
public class BodegaDaoImpl extends AbstractDao<Integer, Bodega> implements BodegaDao {

	static final Logger logger = LoggerFactory.getLogger(BodegaDaoImpl.class);
	
	public Bodega findByIdb(int id) {
		Bodega bodega = getByKey(id);
		if(bodega!=null){
			Hibernate.initialize(bodega.getId());
		}
		return bodega;
	}

	public Bodega findByBODEG(String bodeg) {
		logger.info("BODEG : {}", bodeg);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("bodegId", bodeg));
		Bodega bodega = (Bodega)crit.uniqueResult();
		if(bodega!=null){
			Hibernate.initialize(bodega.getId());
		}
		return bodega;
	}

	@SuppressWarnings("unchecked")
	public List<Bodega> findAllBodegas() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("bodegId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Bodega> bodegas = (List<Bodega>) criteria.list();
		
		
		return bodegas;
	}

	public void saveb(Bodega bodega) {
		persist(bodega);
	}

	public void deleteByBODEG(String bodeg) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("bodegId", bodeg));
		Bodega bodega = (Bodega)crit.uniqueResult();
		delete(bodega);
	}

    

}
