package com.chrisom.sisinv.utils;

public class ProductoUtils {
	public static Double calculatePrecioVenta(Double precioCompra, Integer ganancia){
		Double total = precioCompra + (precioCompra * (ganancia.doubleValue() / 100.0));
		
		return total;
	}
}
