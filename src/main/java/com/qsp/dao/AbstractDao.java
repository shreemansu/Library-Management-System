package com.qsp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract sealed class AbstractDao permits UserDao,BookDao {
	protected static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("shree");
	protected static final EntityManager em=emf.createEntityManager();
//	public static EntityManager getEntityManager() {
//	    return emf.createEntityManager();
//	}
}
