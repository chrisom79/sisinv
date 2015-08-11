package com.chrisom.sisinv.entity;
// Generated 09-ago-2015 19:16:00 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * NotaRemision generated by hbm2java
 */
public class NotaRemision implements java.io.Serializable {

	private Integer identificador;
	private Vendedor vendedor;
	private Date fecha;
	private double total;
	private Set notaRemisionDetalles = new HashSet(0);

	public NotaRemision() {
	}

	public NotaRemision(Vendedor vendedor, Date fecha, double total) {
		this.vendedor = vendedor;
		this.fecha = fecha;
		this.total = total;
	}

	public NotaRemision(Vendedor vendedor, Date fecha, double total, Set notaRemisionDetalles) {
		this.vendedor = vendedor;
		this.fecha = fecha;
		this.total = total;
		this.notaRemisionDetalles = notaRemisionDetalles;
	}

	public Integer getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set getNotaRemisionDetalles() {
		return this.notaRemisionDetalles;
	}

	public void setNotaRemisionDetalles(Set notaRemisionDetalles) {
		this.notaRemisionDetalles = notaRemisionDetalles;
	}

}
