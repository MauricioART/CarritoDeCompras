package ventanas;

import java.util.*;

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
import javafx.util.Callback;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.ArrayList;

import elementos.*;
import almacen.*;

public class VentanaDepartamento extends Stage {
    private Boton cancelarBtn;
    private Boton guardarBtn;
    private Etiqueta mensajeLbl;
    private Pagination pagination;
    private ArrayList<CajaSeleccion> seleccionarBtn;
    private ArrayList<ListaDesplegable> cantidadLD;	
    private Departamento departamento;
    private Usuario usuario;
    private int currentIndex;
	
	public VentanaDepartamento(Departamento departamento,Usuario usuario){

		this.departamento = departamento;
		this.usuario = usuario;    
        	inicializarBotonesSeleccion();
		
		VBox  panelPrincipal = new VBox();
                panelPrincipal.setAlignment(Pos.CENTER);
                panelPrincipal.setMinHeight(200);
                panelPrincipal.setMinWidth(100);
	
		Texto encabezado1 = new Texto("	La Tiendita de la Esquina ");
		encabezado1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD,27));
		encabezado1.setTextAlignment(TextAlignment.CENTER);		
	
		Texto encabezado2 = new Texto(" DEPARTAMENTO DE " + departamento.getNombre().toUpperCase());
		encabezado2.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
		encabezado2.setTextAlignment(TextAlignment.CENTER);

		System.out.println(String.valueOf(departamento.getProductos().size()));
		pagination = new Pagination(departamento.getProductos().size()/2);
        	pagination.setStyle("-fx-border-color:white;");
        	pagination.setPageFactory(new Callback<Integer, Node>() {
            		@Override
            		public Node call(Integer pageIndex) {
                		return createPage(pageIndex);
            		}
        	});
	
		VBox anchor = new VBox();
		anchor.setAlignment(Pos.CENTER);
		anchor.setMinHeight(200);
                anchor.setMinWidth(200);
		anchor.setPadding(new Insets(0, 20, 10, 20));
        	anchor.getChildren().addAll(pagination);

		this.cancelarBtn= new Boton("Cancelar ", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
				CargadorDeCarritos cdc = new CargadorDeCarritos();
                                cdc.guardarCarrito(usuario);
				close();
                        }
                });
		
		this.guardarBtn= new Boton("Guardar en Carrito", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				for (int i = pagination.getCurrentPageIndex()*itemsPerPage() ; i <= pagination.getCurrentPageIndex()*itemsPerPage() + 1 ; i++){
					if (seleccionarBtn.get(i).isSelected()){	
				
						usuario.getCarrito().agregarProducto(departamento.getProducto(i),Integer.parseInt(cantidadLD.get(i).getSelectionModel().getSelectedItem()));
					}
				}
				resetearBotonesSeleccion();
			
			}
		});
		
		HBox botones = new HBox();
		botones.setSpacing(20);
		botones.setAlignment(Pos.CENTER_RIGHT);
		botones.setPadding(new Insets(0,150,20,0));
		botones.getChildren().addAll(this.cancelarBtn, this.guardarBtn);

		panelPrincipal.getChildren().addAll(encabezado1,encabezado2,anchor,botones);
		Scene sceneGrid = new Scene(panelPrincipal, 600, 700);
			
		this.setTitle("Librería El Lector Muy Feliz");
		this.setScene(sceneGrid);
		this.initModality(Modality.NONE);
		this.show();  
			

	}

    	public int itemsPerPage() {
        	return 2;
    	}

	public VBox createPage(int pageIndex) {
		VBox box = new VBox();
		try{
			box.setSpacing(50);
        		int page = pageIndex * itemsPerPage();
        		for (int i = page; i < page + itemsPerPage(); i++) {
				GridPane element1 = new GridPane();
                                HBox box2 = new HBox();
                                HBox box3 = new HBox(); 
                                HBox box4 = new HBox(); 
                                GridPane element2 = new GridPane();
                                //element1.setGridLinesVisible(true);
                                //element2.setGridLinesVisible(true);
                                
                                Etiqueta producto = new Etiqueta("Producto: " + departamento.getProducto(i).getNombre());
                                HBox productoCj = new HBox(5);
                                productoCj.setAlignment(Pos.CENTER_LEFT);
                                productoCj.setPadding(new Insets(0,5,0,5));
                                //productoCj.setStyle("-fx-border-color: black;");
                                productoCj.getChildren().addAll(producto);
                                Etiqueta precio = new Etiqueta("Precio: " + "$" + departamento.getProducto(i).getPrecio());
                                HBox precioCj = new HBox(5);
                                precioCj.setAlignment(Pos.CENTER_LEFT);
                                precioCj.setPadding(new Insets(0,5,0,5));
                                precioCj.getChildren().addAll(precio);
                                Etiqueta descripcion1 = new Etiqueta("Marca:" + departamento.getProducto(i).getMarca());
                                Etiqueta descripcion2 = new Etiqueta("Modelo: " + departamento.getProducto(i).getDescripcion());
                                HBox descripcionCj = new HBox(5);
                                descripcionCj.setAlignment(Pos.CENTER_LEFT);
                                descripcionCj.setPadding(new Insets(0,5,0,5));
                                descripcionCj.getChildren().addAll(descripcion1);
                                
                                FileInputStream image = new FileInputStream(new File(departamento.getProducto(i).getDireccionImagen()));
                                ImageView imagen = new ImageView(new Image(image));
                                imagen.setPreserveRatio(true);
                                imagen.setFitWidth(125);
                                imagen.setFitHeight(100);
                                HBox imagenCj = new HBox(10);
                                imagenCj.getChildren().addAll(imagen);
                                imagenCj.setStyle("-fx-border-color: black;");
                                imagenCj.setAlignment(Pos.CENTER_RIGHT);
                                //imagenCj.setMinHeight(125);
                                //imagenCj.setMinWidth(100);
                                //imagenCj.setMaxHeight(125);
                                //imagenCj.setMaxWidth(100);
                                imagenCj.setPadding(new Insets(5,5,5,5));
                                
                                element2.add(imagenCj,0,1);
                                element2.add(seleccionarBtn.get(i),0,0);
                                element2.add(cantidadLD.get(i),0,2);
                                
                                element1.add(productoCj,0,0);
                                element1.add(precioCj,0,1);
                                element1.add(descripcionCj,0,2);
                                //element1.add(descripcion2,0,3);
                                
                                element1.setAlignment(Pos.BASELINE_LEFT);
                                element2.setAlignment(Pos.BASELINE_RIGHT);
                                
                                
                                box2.getChildren().addAll(element1);
                                box2.setAlignment(Pos.BASELINE_RIGHT); 
                                box2.setPadding(new Insets(10, 20, 20, 20));
                                box2.setMinHeight(100);
                                box2.setMinWidth(400);
                                box2.setMaxHeight(100);
                                box2.setMaxWidth(400);
                                //box2.setStyle("-fx-border-color: black;");
                                box3.getChildren().addAll(element2);
                                box3.setAlignment(Pos.BASELINE_LEFT);
                                box3.setPadding(new Insets(10, 20, 20, 20));
                                box4.getChildren().addAll(box2,box3);
                                box4.setAlignment(Pos.CENTER);
                                box4.setMinHeight(200);
                                box4.setMinWidth(500);
                                box4.setMaxHeight(200);
                                box4.setMaxWidth(500);
                                //box4.setStyle("-fx-border-color: black;");
                                box4.setPadding(new Insets(10, 20, 20, 20));
                                box.getChildren().add(box4);
                                box.setAlignment(Pos.CENTER); 
                                box.setPadding(new Insets(30, 20, 20, 20));
        		}
        		
		}
		catch(Exception e){
			
		}
		finally{
			return box;
		}
    }
	public void inicializarBotonesSeleccion(){
		String productos[] = {"1","2","3","4","5"};
		this.seleccionarBtn = new ArrayList<CajaSeleccion>();
		this.cantidadLD = new ArrayList<ListaDesplegable>();
		for (int i=0;i<departamento.getProductos().size();i++){
			this.seleccionarBtn.add(new CajaSeleccion("Seleccionar"));
			this.cantidadLD.add(new ListaDesplegable(Arrays.asList(productos),new EventHandler<ActionEvent>()
			{
                        	@Override
                        	public void handle(ActionEvent event) {
					checkSpecificBox();
                                }     
                        })

		);
		}
		
	}
	public void resetearBotonesSeleccion(){
		for(int i=0 ; i<seleccionarBtn.size();i++){
			this.seleccionarBtn.get(i).setSelected(false);
			this.cantidadLD.get(i).setValue("1");
		}
	}
	public int getCurrentIndex() {
		return this.currentIndex;
	}
	public void checkSpecificBox() {
		for (int i = pagination.getCurrentPageIndex()*itemsPerPage() ; i <= pagination.getCurrentPageIndex()*itemsPerPage() + 1 ; i++){
                                        if (!cantidadLD.get(i).getSelectionModel().getSelectedItem().equals("1")){
						seleccionarBtn.get(i).setSelected(true);
					}
					if (cantidadLD.get(i).getSelectionModel().getSelectedItem().equals("1")){
                                                seleccionarBtn.get(i).setSelected(false);
                                        }
		}
	}
}

