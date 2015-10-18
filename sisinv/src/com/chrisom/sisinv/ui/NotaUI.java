package com.chrisom.sisinv.ui;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chrisom.sisinv.entity.NotaRemisionDetalle;
import com.chrisom.sisinv.entity.NotaRemisionDetalleId;
import com.chrisom.sisinv.entity.Producto;
import com.chrisom.sisinv.model.PedidoModel;
import com.chrisom.sisinv.model.ProductoModel;
import com.chrisom.sisinv.utils.ProductoUtils;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class NotaUI {
	DateField fecha = null;
	TextField numero = null;
	TextField nombre = null;
	TextField searcher = null;
	Set<NotaRemisionDetalle> prodsPedido = null;
	Panel addProdPnl = null;
	Panel mngmtProdPnl = null;
	IndexedContainer pedidoCntnr = null;
	Table pedidoTbl = null;
	PedidoModel model = new PedidoModel();
	
	public Window createWindowNew() {
		Window notaWndw = new Window(UIConstants.PEDIDO_ITEM);
		VerticalLayout content = new VerticalLayout();
		GridLayout subcontent1 = new GridLayout(5, 5);
		HorizontalLayout hcontent = new HorizontalLayout();
		
		prodsPedido = new HashSet<NotaRemisionDetalle>();
		
		
		fecha = new DateField(UIConstants.PEDIDO_FECHA);
		numero = new TextField(UIConstants.PEDIDO_NUMERO);
		nombre = new TextField(UIConstants.PEDIDO_NOMBRE);
		addProdPnl = createPanelAddProductos();
		mngmtProdPnl = createPanelMngmtProductos();
		Button cartButton = createCartButton();
		Button nextBtn = createNextButton();
		
		fecha.setValue(new Date());
		numero.setValue(model.getNewId().toString());
		nombre.setWidth("400");
		hcontent.addComponent(cartButton);
		
		subcontent1.addComponent(fecha, 0, 0);
		//subcontent2.setComponentAlignment(fecha, Alignment.MIDDLE_RIGHT);
		subcontent1.addComponent(numero, 1, 0);
		//subcontent4.setComponentAlignment(numero, Alignment.MIDDLE_RIGHT);
		subcontent1.addComponent(nombre, 2, 0, 4, 0);
		subcontent1.addComponent(hcontent, 0, 1);
		//subcontent1.addComponent(nextBtn, 4, 1);
		subcontent1.addComponent(addProdPnl, 0, 2, 4, 3);
		//subcontent1.addComponent(mngmtProdPnl, 4, 2, 9, 3);
		hcontent.setMargin(true);
		hcontent.setSpacing(true);
		subcontent1.setMargin(true);
		subcontent1.setSpacing(true);
		
		content.addComponent(subcontent1);
		
		notaWndw.setContent(content);
		notaWndw.center();
		
		return notaWndw;
	}
	
	private TextField createSearcher(IndexedContainer container, Table result) {
		TextField prodSrchr = new TextField(UIConstants.PEDIDO_PRODUCTO_SRCHR);
		
		
		prodSrchr.addShortcutListener(new ShortcutListener("shortcutId", ShortcutAction.KeyCode.ENTER, null) {
		    @Override
		    public void handleAction(Object sender, Object target) {
		    	executeFindProductosByParameters(container, result);
		    }
		});
		
		return prodSrchr;
	}
	
	private Button createAddButton(Producto prod, TextField cant) {
		Button button = new Button();
		button.setIcon(FontAwesome.PLUS_SQUARE_O);
		button.setId(prod.getId());
		
		button.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(cant.getValue() != null && !cant.getValue().isEmpty()) {
					NotaRemisionDetalleId itemId = new NotaRemisionDetalleId();
					itemId.setNotaRemisionId(Integer.valueOf(numero.getValue()));
					itemId.setProductosId(prod.getId());
					if(cant.getValue() != null && !cant.getValue().isEmpty())
						itemId.setCantidad(Integer.valueOf(cant.getValue()));
					itemId.setPrecio(ProductoUtils.calculatePrecioVenta(prod.getPrecioCompra(), prod.getPorcentaje()));
					
					NotaRemisionDetalle item = new NotaRemisionDetalle();
					item.setId(itemId);
					item.setProductos(prod);
					
					prodsPedido.add(item);
					
					refreshPedidoContainer();
					Notification.show("Producto agregado",
			                  "El producto ha sido agregado al pedido",
			                  Notification.Type.TRAY_NOTIFICATION);
				} else {
					Notification.show("Favor de asignar una cantidad de producto",
			                  Notification.Type.ERROR_MESSAGE);
				}
			}
		});

		return button;
	}
	
	private Button createCartButton() {
		Button button = new Button();
		button.setIcon(FontAwesome.SHOPPING_CART);
		
		button.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				GridLayout layout = (GridLayout) addProdPnl.getParent();
				if(addProdPnl.isVisible()) {
					addProdPnl.setVisible(false);
					layout.removeComponent(addProdPnl);
					layout.addComponent(mngmtProdPnl, 0, 2, 4, 3);
				}
			}
		});

		return button;
	}
	
	private Button createNextButton() {
		Button button = new Button();
		button.setIcon(FontAwesome.CHEVRON_CIRCLE_RIGHT);
		
		button.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				GridLayout layout = (GridLayout) addProdPnl.getParent();
				
				/*if(addProdPnl.isVisible()) {
					addProdPnl.setVisible(false);
					layout.removeComponent(addProdPnl);
					layout.addComponent(mngmtProdPnl);
				}*/
			}
		});

		return button;
	}
	
	private TextField createTxtCantidad(String id) {
		TextField txtCantidad = new TextField();
		txtCantidad.setId("txt" + id);
		txtCantidad.setWidth("50");
		
		return txtCantidad;
	}
	
	private Panel createPanelAddProductos() {
		Panel prodPnl = new Panel();
		VerticalLayout layout =  new VerticalLayout();
		Table result = new Table();
		IndexedContainer container = createResultContainer();
		
		searcher = createSearcher(container, result);
		
		searcher.setWidth("400");
		result.setContainerDataSource(container);
		result.setSelectable(true);
		result.setPageLength(5);
		result.setWidth("700");
		//prodPnl.setWidth("550");
		
		layout.addComponent(searcher);
		layout.addComponent(result);
		
		layout.setSpacing(true);
		layout.setMargin(true);
		
		prodPnl.setContent(layout);
		return prodPnl;
	}
	
	private Panel createPanelMngmtProductos() {
		Panel prodPnl = new Panel();
		VerticalLayout layout =  new VerticalLayout();
		pedidoTbl = new Table();
		pedidoCntnr = createPedidoContainer();
		Label lblPedido = new Label(UIConstants.PEDIDO_CREANDO);
		
		pedidoTbl.setContainerDataSource(pedidoCntnr);
		pedidoTbl.setSelectable(true);
		pedidoTbl.setPageLength(5);
		pedidoTbl.setWidth("700");
		//prodPnl.setWidth("550");
		layout.addComponent(lblPedido);
		layout.addComponent(pedidoTbl);
		
		layout.setSpacing(true);
		layout.setMargin(true);
		
		prodPnl.setContent(layout);
		return prodPnl;
	}
	
	private IndexedContainer createResultContainer() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty(UIConstants.PRODUCTO_NOMBRE, String.class, null);
		container.addContainerProperty(UIConstants.PRODUCTO_PRECIO_VENTA, Double.class, null);
		container.addContainerProperty(UIConstants.PRODUCTO_IVA, String.class, null);
		container.addContainerProperty(UIConstants.PEDIDO_CANTIDAD, TextField.class, null);
		container.addContainerProperty(UIConstants.AGREGAR, Button.class, null);
		
		return container;
	}
	
	private IndexedContainer createPedidoContainer() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty(UIConstants.PRODUCTO_NOMBRE, String.class, null);
		container.addContainerProperty(UIConstants.PRODUCTO_PRECIO_VENTA, Double.class, null);
		container.addContainerProperty(UIConstants.PRODUCTO_IVA, String.class, null);
		container.addContainerProperty(UIConstants.PEDIDO_CANTIDAD, Integer.class, null);
		container.addContainerProperty(UIConstants.PEDIDO_IMPORTE, Double.class, null);
		container.addContainerProperty(UIConstants.BUTTON_CANCELAR, Button.class, null);
		
		return container;
	}
	private void executeFindProductosByParameters(IndexedContainer container, Table table) {
		ProductoModel model = new ProductoModel();
		List<Producto> productos = model.findProductoByParameters(searcher.getValue());
		container.removeAllItems();
		for(Producto producto : productos) {
			Object id = container.addItem();
			Item item = container.getItem(id);
			TextField txCantidad = createTxtCantidad(producto.getId());
			item.getItemProperty(UIConstants.PRODUCTO_NOMBRE).setValue(producto.getNombre());
			item.getItemProperty(UIConstants.PRODUCTO_PRECIO_VENTA).setValue(ProductoUtils.calculatePrecioVenta(producto.getPrecioCompra(), producto.getPorcentaje()));
			item.getItemProperty(UIConstants.PRODUCTO_IVA).setValue(producto.getIva()? "Si":"No");
			item.getItemProperty(UIConstants.PEDIDO_CANTIDAD).setValue(txCantidad);
			item.getItemProperty(UIConstants.AGREGAR).setValue(createAddButton(producto, txCantidad));
		}
		
		table.refreshRowCache();
	}
	
	private void refreshPedidoContainer() {
		pedidoCntnr.removeAllItems();
		for(NotaRemisionDetalle detalle : prodsPedido) {
			Object id = pedidoCntnr.addItem();
			Item item = pedidoCntnr.getItem(id);
			Producto producto = detalle.getProductos();
			NotaRemisionDetalleId nrId = detalle.getId();
			Double precioVenta = ProductoUtils.calculatePrecioVenta(producto.getPrecioCompra(), producto.getPorcentaje());
			Double importe = precioVenta * nrId.getCantidad();
			item.getItemProperty(UIConstants.PRODUCTO_NOMBRE).setValue(producto.getNombre());
			item.getItemProperty(UIConstants.PRODUCTO_PRECIO_VENTA).setValue(precioVenta);
			item.getItemProperty(UIConstants.PRODUCTO_IVA).setValue(producto.getIva()? "Si":"No");
			item.getItemProperty(UIConstants.PEDIDO_CANTIDAD).setValue(nrId.getCantidad());
			item.getItemProperty(UIConstants.PEDIDO_IMPORTE).setValue(importe);
//			item.getItemProperty(UIConstants.AGREGAR).setValue(createAddButton(producto, txCantidad.getValue()));
		}
		pedidoTbl.refreshRowCache();
	}
}
