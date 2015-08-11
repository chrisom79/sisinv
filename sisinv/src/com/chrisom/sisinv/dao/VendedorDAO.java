package com.chrisom.sisinv.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.chrisom.sisinv.entity.Vendedor;

public class VendedorDAO implements DAOInterface<Vendedor>{
	

	@Override
	public void insert(Vendedor vendedor) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		try {
			
			session.beginTransaction();
			session.save(vendedor);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
			//SessionFactoryDB.shutdown();
		}
		
	}

	@Override
	public Long countIds(String id) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		Long total = null;
		try {
			Query query = session.createQuery("select count(id) from Vendedor where id like :idVendedor");
			query.setParameter("idVendedor", id + "%");
		
			total = (Long) query.uniqueResult();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		} finally {
			//SessionFactoryDB.shutdown();
		}
		
		return total;
	}
	
	public Vendedor findVendedorByUserAndPassword(String user, String password) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		Vendedor vendedor = null;
		try {
			Query query = session.createQuery("from Vendedor where usuario = :user and password = :password");
			query.setParameter("user", user);
			query.setParameter("password", password);
			
			vendedor = (Vendedor) query.uniqueResult();
		
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		} finally {
			//SessionFactoryDB.shutdown();
		}

		return vendedor;
	}
}
