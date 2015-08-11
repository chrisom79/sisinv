package com.chrisom.sisinv.model;

import com.chrisom.sisinv.dao.VendedorDAO;
import com.chrisom.sisinv.entity.Vendedor;
import com.chrisom.sisinv.utils.Algorithms;

public class VendedorModel {
	VendedorDAO dao = new VendedorDAO();
	
	public void insertVendedor(Vendedor vendedor) {
		dao.insert(vendedor);		
	}
	
	public String createId(String nombre) {
		String[] splitted = nombre.trim().split("\\s");
		StringBuffer sb = new StringBuffer();
		
		for(String name : splitted){
			if(name != null && !name.trim().isEmpty())
				sb.append(name.substring(0, 3));
		}
		
		sb.append(dao.countIds(sb.toString()) + 1);
		return sb.toString();
	}
	
	public boolean isLoginSuccess(String user, String password) {
		if(dao.findVendedorByUserAndPassword(user, Algorithms.encryptMD5(password)) != null) {
			return true;
		}
		
		return false;
	}
}
