package ventanas;

import java.util.*;
import java.text.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import controles.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;

import elementos.*;
import almacen.*;

public class VentanaMostrarCarrito extends Stage {
    private Boton agregarBtn;
    private Boton pagarBtn;
    private Etiqueta subtotalLbl;
    private Etiqueta numSubtotalLbl;
    private Etiqueta impuestosLbl;
    private Etiqueta numImpuestosLbl;
    private Etiqueta totalLbl;
    private Etiqueta numTotalLbl;
    private ArrayList<Boton> eliminarBtn;
    private ArrayList<ListaDesplegable> cantidadLD;
    private Usuario usuario;
    private int index;
    private ScrollPane scrollPane;
	
	
	public VentanaMostrarCarrito(Usuario usuario){

		this.usuario = usuario;

		this.usuario.getCarrito().sumarSubtotal();
		this.usuario.getCarrito().calcularTotal();

		VBox panelPrincipal = new VBox(25);
		panelPrincipal.setAlignment(Pos.CENTER);
	
		Texto encabezado1 = new Texto("	La Tiendita de la Esquina ");
		encabezado1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD,27));
		encabezado1.setTextAlignment(TextAlignment.CENTER);		
	
		Texto encabezado2 = new Texto(" CARRITO DE COMPRAS " + "\n" + " DETALLE DE PRODUCTOS ");
		encabezado2.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
		encabezado2.setTextAlignment(TextAlignment.CENTER);

		inicializarElementos();

		VBox productosCj = new VBox(10);
		scrollPane = new ScrollPane();
		String productos[] = {"1","2","3","4","5"};
			this.index = 0;
		for(int i = 0; i < usuario.getCarrito().getProducto().size(); i++) {
			 this.index++;
			 HBox producto = new HBox(90);
			 VBox imagenCj = new VBox();
			 VBox titulos = new VBox(5);
			 VBox controles = new VBox(5);
			 try {  
                        	FileInputStream imageFile = new FileInputStream(new File(usuario.getCarrito().getProducto(i).getDireccionImagen()));
                        	ImageView imageView = new ImageView(new Image(imageFile));
                        	imageView.setPreserveRatio(true);
				//imageView.setFitWidth(50);
                        	imageView.setFitHeight(70);
                        	imagenCj.getChildren().add(imageView);
                	} catch(FileNotFoundException e) {
                        	e.printStackTrace();
                	}

			Etiqueta productoLbl = new Etiqueta("Producto: " + usuario.getCarrito().getProducto(i).getNombre());
			Etiqueta precioLbl = new Etiqueta("Precio: $" + String.valueOf(usuario.getCarrito().getProducto(i).getPrecio()));
			titulos.getChildren().addAll(productoLbl, precioLbl);

			Etiqueta cantidadLbl = new Etiqueta("Cantidad:");
			
			cantidadLD.get(i).setValue(String.valueOf(usuario.getCarrito().getNumeroElementos(i)));
			
			HBox cantidadCj = new HBox();
			cantidadCj.setAlignment(Pos.BASELINE_RIGHT);
			cantidadCj.getChildren().addAll(cantidadLbl,cantidadLD.get(i));
			
			eliminarBtn.get(i).setMinHeight(30);
                	eliminarBtn.get(i).setMinWidth(100);
                	eliminarBtn.get(i).setMaxHeight(30);
                	eliminarBtn.get(i).setMaxWidth(100);
			eliminarBtn.get(i).setAlignment(Pos.CENTER_RIGHT);
			controles.getChildren().addAll(cantidadCj,eliminarBtn.get(i));
			producto.getChildren().addAll(imagenCj,titulos,controles);	
			productosCj.getChildren().add(producto);
			
			controles.setStyle("-fx-border-color: black;");
			producto.setStyle("-fx-border-color: black;");
			productosCj.setStyle("-fx-border-color: black;");
			
		}
		scrollPane.setContent(productosCj);
		
		scrollPane.setMinHeight(300);
                scrollPane.setMinWidth(550);
                scrollPane.setMaxHeight(300);
                scrollPane.setMaxWidth(550);

		VBox seccionCj = new VBox();

		HBox subtotalCj = new HBox();
		HBox impuestosCj = new HBox();
		HBox totalCj = new HBox();

		HBox numTotalCj =new HBox();
                HBox numSubtotalCj = new HBox();
                HBox numImpuestosCj = new HBox();

		this.subtotalLbl = new Etiqueta("Subtotal");
		this.impuestosLbl = new Etiqueta("Impuestos");
		this.totalLbl = new Etiqueta("Total");

		DecimalFormat df = new DecimalFormat("#.00");
		
		this.numSubtotalLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getSubtotal()));
		this.numImpuestosLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getImpuestos()));
		this.numTotalLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getTotal()));

		numSubtotalCj.getChildren().add(this.numSubtotalLbl);
		numSubtotalCj.setStyle("-fx-border-color: black;");

		numImpuestosCj.getChildren().add(this.numImpuestosLbl);
                numImpuestosCj.setStyle("-fx-border-color: black;");
	
		numTotalCj.getChildren().add(this.numTotalLbl);
                numTotalCj.setStyle("-fx-border-color: black;");

		subtotalCj.getChildren().addAll(this.subtotalLbl,numSubtotalCj);
		subtotalCj.setAlignment(Pos.CENTER_RIGHT);
		impuestosCj.getChildren().addAll(this.impuestosLbl,numImpuestosCj);
		impuestosCj.setAlignment(Pos.CENTER_RIGHT);
		totalCj.getChildren().addAll(this.totalLbl,numTotalCj);
		totalCj.setAlignment(Pos.CENTER_RIGHT);

		seccionCj.getChildren().addAll(subtotalCj,impuestosCj,totalCj);
		seccionCj.setAlignment(Pos.CENTER_RIGHT);
		//seccionCj.setPadding(new Insets(10, 20, 10, 20));
		 

		this.pagarBtn= new Boton("Pagar ", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
				for(int i=0;i<cantidadLD.size();i++){
					usuario.getCarrito().setCantidadProducto(i,Integer.parseInt(cantidadLD.get(i).getSelectionModel().getSelectedItem()));
				}
				usuario.getCarrito().sumarSubtotal();
				usuario.getCarrito().calcularTotal();
				new VentanaRecibo(usuario.getCarrito());
				Carrito carritoVacio = new Carrito(usuario.getLlaveCarrito());
				usuario.setCarrito(carritoVacio);
				actualizarScroll();
                        }
                });
		
		this.agregarBtn= new Boton("Agregar Mas Productos", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				CargadorDeCarritos cdc = new CargadorDeCarritos();
                                cdc.guardarCarrito(usuario);
                                close();
			}
		});
		
		HBox botones = new HBox();
		botones.setSpacing(20);
		botones.setAlignment(Pos.CENTER_RIGHT);
		botones.getChildren().addAll(this.agregarBtn, this.pagarBtn);

		panelPrincipal.getChildren().addAll(encabezado1,encabezado2,scrollPane,seccionCj,botones);
                panelPrincipal.setMinHeight(500);
                panelPrincipal.setMinWidth(500);
                panelPrincipal.setMaxHeight(500);
                panelPrincipal.setMaxWidth(500);
		panelPrincipal.setAlignment(Pos.CENTER);
	
		Scene sceneGrid = new Scene(panelPrincipal, 600, 700);
		
		this.setTitle("La Tiendita de la Esquina");
		this.setScene(sceneGrid);
		this.initModality(Modality.NONE);
		this.show();  
			

	}

    	public int itemsPerPage() {
        	return 2;
    	}

	public void inicializarElementos(){
		
		this.cantidadLD = new ArrayList<ListaDesplegable>();
		this.eliminarBtn = new ArrayList<Boton>();
		int cantidad = this.usuario.getCarrito().getProducto().size();
		String[] cantidadStr = {"1","2","3","4","5"};
		for(int i=0 ; i < cantidad; i++){
			this.cantidadLD.add(new ListaDesplegable(Arrays.asList(cantidadStr),new EventHandler<ActionEvent>(){
                                public void handle(ActionEvent e){
					actualizarEtiquetas();
                                }
			}));
			this.eliminarBtn.add(new Boton("Eliminar",new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					e.getSource();
					int indexBoton = 0;
					for(int i=0;i<eliminarBtn.size();i++){
						if(eliminarBtn.get(i) == e.getSource()){
							indexBoton = i;
						}

					}
					usuario.getCarrito().eliminarProducto(usuario.getCarrito().getProducto(indexBoton));
					actualizarScroll();
					actualizarEtiquetas();
				}
			})); 
		}
	}

	public void actualizarScroll() {
	VBox productosCj = new VBox(10);
        String productos[] = {"1","2","3","4","5"};
        this.index = 0;
	for(int i = 0; i < usuario.getCarrito().getProducto().size(); i++) {
                         this.index++;
                         HBox producto = new HBox(90);
                         VBox imagenCj = new VBox();
                         VBox titulos = new VBox(5);
                         VBox controles = new VBox(5);
                         try {
                                FileInputStream imageFile = new FileInputStream(new File(usuario.getCarrito().getProducto(i).getDireccionImagen()));
                                ImageView imageView = new ImageView(new Image(imageFile));
                                imageView.setPreserveRatio(true);
                                //imageView.setFitWidth(50);
                                imageView.setFitHeight(70);
                                imagenCj.getChildren().add(imageView);
                        } catch(FileNotFoundException e) {
                                e.printStackTrace();
                        }

                        Etiqueta productoLbl = new Etiqueta("Producto: " + usuario.getCarrito().getProducto(i).getNombre());
                        Etiqueta precioLbl = new Etiqueta("Precio: $" + String.valueOf(usuario.getCarrito().getProducto(i).getPrecio()));
                        titulos.getChildren().addAll(productoLbl, precioLbl);

                        Etiqueta cantidadLbl = new Etiqueta("Cantidad:");

                        cantidadLD.get(i).setValue(String.valueOf(usuario.getCarrito().getNumeroElementos(i)));

                        HBox cantidadCj = new HBox();
                        cantidadCj.setAlignment(Pos.BASELINE_RIGHT);
                        cantidadCj.getChildren().addAll(cantidadLbl,cantidadLD.get(i));

                        eliminarBtn.get(i).setMinHeight(30);
                        eliminarBtn.get(i).setMinWidth(100);
                        eliminarBtn.get(i).setMaxHeight(30);
                        eliminarBtn.get(i).setMaxWidth(100);
                        eliminarBtn.get(i).setAlignment(Pos.CENTER_RIGHT);
                        controles.getChildren().addAll(cantidadCj,eliminarBtn.get(i));
                        producto.getChildren().addAll(imagenCj,titulos,controles);
                        productosCj.getChildren().add(producto);

                        controles.setStyle("-fx-border-color: black;");
                        producto.setStyle("-fx-border-color: black;");
                        productosCj.setStyle("-fx-border-color: black;");
                }	
		scrollPane.setContent(productosCj);
	}
	public void actualizarEtiquetas() {
		DecimalFormat df = new DecimalFormat("#.00");
		this.numSubtotalLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getSubtotal()));
                this.numImpuestosLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getImpuestos()));
                this.numTotalLbl = new Etiqueta("$" + df.format(usuario.getCarrito().getTotal()));
	}
}
