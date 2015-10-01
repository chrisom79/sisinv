package com.chrisom.sisinv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.chrisom.sisinv.entity.Productos;

public class ProductoDAO implements DAOInterface<Productos> {

	@Override
	public void insert(Productos element) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		try {
			
			session.beginTransaction();
			session.save(element);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(Productos element) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		try {
			
			session.beginTransaction();
			session.saveOrUpdate(element);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
			//SessionFactoryDB.shutdown();
		}
		
	}

	@Override
	public Long countIds(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByField(String field) {
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		try {
			
			session.beginTransaction();
			Query query = session.createQuery("delete Productos where id = :id");
			query.setParameter("id", field);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
			//SessionFactoryDB.shutdown();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Productos> findProductoByNombre(String nombre){
		List<Productos> productos = new ArrayList<Productos>();
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		Query query = session.createQuery("from Productos where nombre like :nombre");
		
		query.setParameter("nombre", "%" + nombre + "%");
		
		productos = query.list();
		return productos;
	}
	
	public Productos findProductoByCode(String id){
		Productos producto = new Productos();
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		Query query = session.createQuery("from Productos where id like :id");
		
		query.setParameter("id", id);
		
		producto = (Productos) query.uniqueResult();
		return producto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Productos> findProductoByParameters(String id, String nombre) {
		List<Productos> productos = new ArrayList<Productos>();
		Session session = SessionFactoryDB.getSessionFactory().openSession();
		StringBuffer querySb = new StringBuffer("from Productos where ");
		
		if(id != null && !id.isEmpty()) {
			querySb.append("id like :id ");
		} 
		if(nombre != null && !nombre.isEmpty()) {
			querySb.append("nombre like :name ");
		}
		
		Query query = session.createQuery(querySb.toString()); 
		
		if(id != null && !id.isEmpty()) {
			query.setParameter("id", "%" + id + "%");
		} 
		if(nombre != null && !nombre.isEmpty()) {
			query.setParameter("name", "%" + nombre + "%");
		}
		
		productos = query.list();
		return productos;
	}

}
