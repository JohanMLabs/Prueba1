package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Compras;



@Repository("comprasDao")
public class ComprasDaoImpl extends AbstractDao<Integer, Compras> implements ComprasDao {

	static final Logger logger = LoggerFactory.getLogger(ComprasDaoImpl.class);
	
	public Compras findByIdc(int id) {
		Compras compras = getByKey(id);
		if(compras!=null){
			Hibernate.initialize(compras.getId());
		}
		return compras;
	}

	public Compras findByCOM(String com) {
		logger.info("COM : {}", com);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nombreId", com));
		Compras compras = (Compras)crit.uniqueResult();
		if(compras!=null){
			Hibernate.initialize(compras.getId());
		}
		return compras;
	}

	@SuppressWarnings("unchecked")
	public List<Compras> findAllCompras() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("nombreId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Compras> compras = (List<Compras>) criteria.list();
		
		
		return compras;
	}

	public void savec(Compras compras) {
		persist(compras);
	}

	public void deleteByCOM(String com) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nombreId", com));
		Compras compras = (Compras)crit.uniqueResult();
		delete(compras);
	}

    

}
