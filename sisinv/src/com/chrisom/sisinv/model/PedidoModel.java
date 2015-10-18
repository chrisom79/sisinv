package com.chrisom.sisinv.model;

import com.chrisom.sisinv.dao.PedidoDAO;

public class PedidoModel {
	PedidoDAO dao = new PedidoDAO();
	
	public Long getNewId() {
		Long id = null;
		
		id = dao.countIds(null);
		if(id == null) {
			id = new Long(0);
		}
		
		return id + 1;
	}
}
