package com.chrisom.sisinv.ui;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

public class Menu {
	public MenuBar init() {
		MenuBar mainBar =  new MenuBar();
		
		MenuBar.Command command = new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				
			}
		};
		
		MenuItem usuarios = mainBar.addItem(UIConstants.USUARIO_ITEM, null, null);
		usuarios.addItem(UIConstants.CREAR_USUARIO_ITEM, command);
		
		
		return mainBar;
	}
}
