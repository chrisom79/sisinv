package com.chrisom.sisinv.dao;

import org.hibernate.Session;

import com.chrisom.sisinv.entity.Vendedor;

public class VendedorDAO implements DAOInterface<Vendedor>{
	

	@Override
	public void insert(Vendedor vendedor) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.save(vendedor);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
			SessionFactoryDB.shutdown();
		}
		
	}
}
