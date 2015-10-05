package com.cloud.pool;

import javax.persistence.EntityManager;

import com.cloud.DB.Transaction;

public class PoolDaoImpl implements PoolDao {

	@Override
	public void save(Pool pool) {
		EntityManager em=Transaction.start();
		em.persist(pool);
		Transaction.commit(em);
		em.close();
	}

	

	@Override
	public void delete(int id) {
		EntityManager em=Transaction.start();
		PoolVO pool=(PoolVO)em.find(PoolVO.class, id);
		System.out.println(pool);
		if(pool!=null){
			em.remove(pool);
		}
		Transaction.commit(em);
		em.close();
	}

	@Override
	public PoolVO findById(int id) {
		EntityManager em=Transaction.start();
		PoolVO pool=(PoolVO)em.find(PoolVO.class, id);
		return pool;
	}



	@Override
	public void update(PoolVO pool) {
		EntityManager em=Transaction.start();
		em.merge(pool);
		Transaction.commit(em);
		em.close();

	}

	
	
}
