package com.chrisom.sisinv.ui;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VendedorUI {
	public Window createWindow() {
		Window vendedorW = new Window(UIConstants.CREAR_USUARIO_ITEM);
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		vendedorW.setContent(content);
		
		TextField nombreLbl = new TextField(UIConstants.VENDEDOR_NOMBRE);
		
		content.addComponent(nombreLbl);
		
		vendedorW.center();
		
		return vendedorW;
	}
}
