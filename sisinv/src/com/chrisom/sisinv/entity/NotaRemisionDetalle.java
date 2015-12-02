package com.chrisom.sisinv.entity;
// Generated 27-oct-2015 22:28:42 by Hibernate Tools 3.4.0.CR1

/**
 * NotaRemisionDetalle generated by hbm2java
 */
public class NotaRemisionDetalle implements java.io.Serializable {

	private int id;
	private int cantidad;
	private double precio;
	private String productoId;
	private NotaRemision notaRemision;

	public NotaRemisionDetalle() {
	}

	public NotaRemisionDetalle(int id, int cantidad, double precio, String productoId) {
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.productoId = productoId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getProductoId() {
		return this.productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public NotaRemision getNotaRemision() {
		return notaRemision;
	}

	public void setNotaRemision(NotaRemision notaRemision) {
		this.notaRemision = notaRemision;
	}

}
