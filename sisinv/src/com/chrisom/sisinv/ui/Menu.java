package com.chrisom.sisinv.ui;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;

public class Menu {
	public MenuBar init(UI ui) {
		MenuBar mainBar =  new MenuBar();
		VendedorUI vendedorUI = new VendedorUI();
		
		MenuBar.Command command = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if(selectedItem.getText().equals(UIConstants.CREAR_USUARIO_ITEM)) {
					ui.addWindow(vendedorUI.createWindow());;
				}
			}
		};
		
		MenuItem usuarios = mainBar.addItem(UIConstants.USUARIO_ITEM, null, null);
		usuarios.addItem(UIConstants.CREAR_USUARIO_ITEM, command);
		
		
		return mainBar;
	}
}
