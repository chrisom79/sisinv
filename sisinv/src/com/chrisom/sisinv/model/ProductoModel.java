package com.chrisom.sisinv.model;

import java.util.List;

import com.chrisom.sisinv.dao.ProductoDAO;
import com.chrisom.sisinv.entity.Productos;

public class ProductoModel {
	ProductoDAO dao = new ProductoDAO();
	public void insertProducto(Productos producto) {
		dao.insert(producto);
	}
	
	public void updateProducto(Productos producto) {
		dao.update(producto);
	}
	
	public void deleteById(String id) {
		dao.deleteByField(id);
	}
	
	public List<Productos> findProductoByNombre(String nombre) {
		return dao.findProductoByNombre(nombre);
	}
	
	public List<Productos> findProductoByParameters(String id, String nombre) {
		return dao.findProductoByParameters(id, nombre);
	}
	
	public Productos findProductoByCode(String id) {
		return dao.findProductoByCode(id);
	}
}
