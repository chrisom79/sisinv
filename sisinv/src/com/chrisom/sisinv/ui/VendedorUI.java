package com.chrisom.sisinv.ui;

import com.chrisom.sisinv.entity.Vendedor;
import com.chrisom.sisinv.model.VendedorModel;
import com.chrisom.sisinv.utils.Algorithms;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VendedorUI {
	TextField nombreLbl = null;
	TextField direccionLbl = null;
	TextField telefonoLbl = null;
	TextField usuarioLbl = null;
	
	public Window createWindow() {
		Window vendedorW = new Window(UIConstants.CREAR_USUARIO_ITEM);
		VerticalLayout content = new VerticalLayout();
		HorizontalLayout subcontent = new HorizontalLayout();
		content.setMargin(true);
		vendedorW.setContent(content);
		
		nombreLbl = new TextField(UIConstants.VENDEDOR_NOMBRE);
		direccionLbl = new TextField(UIConstants.VENDEDOR_DIRECCION);
		telefonoLbl = new TextField(UIConstants.VENDEDOR_TELEFONO);
		usuarioLbl = new TextField(UIConstants.VENDEDOR_USUARIO);
		
		nombreLbl.setWidth("500");
		direccionLbl.setWidth("500");
		
		content.addComponent(nombreLbl);
		content.addComponent(direccionLbl);
		content.addComponent(telefonoLbl);
		content.addComponent(usuarioLbl);
		
		
		Button guardarBtn = createGuardarButton();
		
		subcontent.addComponent(guardarBtn);
		
		content.setSpacing(true);
		subcontent.setSpacing(true);
		content.addComponent(subcontent);
		
		vendedorW.center();
		
		return vendedorW;
	}
	
	private Button createGuardarButton() {
		Button button = new Button(UIConstants.BUTTON_GUARDAR);
		VendedorModel model = new VendedorModel();
	
		button.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Vendedor vendedor = new Vendedor(model.createId(nombreLbl.getValue()), nombreLbl.getValue(),
						direccionLbl.getValue(), telefonoLbl.getValue(), usuarioLbl.getValue(), Algorithms.encryptMD5("password"));
				model.insertVendedor(vendedor);
				
				Window window = (Window) direccionLbl.getParent().getParent();
				window.close();
			}
		});
		
		return button;
	}
	
}
