package com.cloud.DB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Transaction {
	private static final String PERSISTENCE_UNIT_NAME = "cloud";
	static EntityManagerFactory factory=null;

	static{
		factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	public static EntityManager getEntityManager()	{
		return factory.createEntityManager();	
	}

	public static EntityManager start(){
		EntityManager em =factory.createEntityManager();
		EntityTransaction txn=em.getTransaction();
		txn.begin();
		return em;
	}
	public static void commit(EntityManager em){
		EntityTransaction txn=em.getTransaction();
		txn.commit();
	}
}
