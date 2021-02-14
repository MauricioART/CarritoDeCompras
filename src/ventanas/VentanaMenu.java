package ventanas;

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

import elementos.*;
import almacen.*;

public class VentanaMenu extends Stage {
    private Boton depa1Btn;
    private Boton depa2Btn;
    private Boton depa3Btn;
    private Boton depa4Btn;
    private Boton salirBtn;
    private Boton comprarBtn;
    private Etiqueta mensajeLbl;
    private Usuario usuario;	
	
	public VentanaMenu(Usuario usuario){
		
		this.usuario = usuario;
		CargadorDeCarritos  cdc = new CargadorDeCarritos();
		if(cdc.cargarCarrito(usuario.getLlaveCarrito())){
			usuario.setCarrito(cdc.getCarrito());	
		}
		GridPane  grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(30);
				
		Texto encabezado1 = new Texto("		La Tiendita de la Esquina ");
		encabezado1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD,27));
		encabezado1.setTextAlignment(TextAlignment.CENTER);		
	
		Texto encabezado2 = new Texto(usuario.getNombre());
		encabezado2.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
		encabezado2.setTextAlignment(TextAlignment.CENTER);
	
		Texto encabezado3 = new Texto("Bienvenido a" + "\n" + "\"La tiendita de la Esquina\"");
                encabezado3.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
                encabezado3.setTextAlignment(TextAlignment.CENTER);

		Texto encabezado4 = new Texto("Por favor escoja el" + "\n" + "departamento de su" + "\n" + "interes");
                encabezado4.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
                encabezado4.setTextAlignment(TextAlignment.CENTER);

		Texto encabezado5 = new Texto("¡Buenas compras!");
                encabezado5.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
                encabezado5.setTextAlignment(TextAlignment.CENTER);

		VBox titulos = new VBox(5);
		titulos.setSpacing(75);
		titulos.setAlignment(Pos.TOP_CENTER);
		titulos.getChildren().addAll(encabezado2,encabezado3,encabezado4,encabezado5);
		//titulos.setPadding(new Insets(10, 20, 10, 20));

		this.depa1Btn= new Boton("Electronica", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
				CargadorDeDepartamentos cdd = new CargadorDeDepartamentos("Electronica");
				cdd.leerRegistroDepartamentos();
				new VentanaDepartamento(cdd.getDepartamento(),usuario);
                        }
                });

		this.depa2Btn= new Boton("Juguetes", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
                        	CargadorDeDepartamentos cdd = new CargadorDeDepartamentos("Juguetes");
                                cdd.leerRegistroDepartamentos();
				new VentanaDepartamento(cdd.getDepartamento(),usuario);
			}
                });

		this.depa3Btn= new Boton("Ropa", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
                        	CargadorDeDepartamentos cdd = new CargadorDeDepartamentos("Ropa");
                                cdd.leerRegistroDepartamentos();
                                new VentanaDepartamento(cdd.getDepartamento(),usuario);
			}
                });

		this.depa4Btn= new Boton("Videojuegos", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
                        	CargadorDeDepartamentos cdd = new CargadorDeDepartamentos("Videojuegos");
                                cdd.leerRegistroDepartamentos();
                                new VentanaDepartamento(cdd.getDepartamento(),usuario);
			}
                });

		VBox depas = new VBox();
		depas.setAlignment(Pos.CENTER);
		depas.setPrefWidth(100);
		depas.setPadding(new Insets(0, 20, 10, 20));
		depa1Btn.setMinWidth(150);
		depa1Btn.setMinHeight(75);
		depa2Btn.setMinWidth(150);
		depa2Btn.setMinHeight(75);
		depa3Btn.setMinWidth(150);
		depa3Btn.setMinHeight(75);
		depa4Btn.setMinHeight(75);
		depa4Btn.setMinWidth(150);
		depas.setSpacing(20);
		depas.getChildren().addAll(this.depa1Btn,this.depa2Btn,this.depa3Btn,this.depa4Btn);

		this.salirBtn= new Boton("Salir ", new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
				close();
                        }
                });
		
		this.comprarBtn= new Boton("Comprar", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				new VentanaMostrarCarrito(usuario);	
			}
		});


		this.mensajeLbl = new Etiqueta("Tiene " + " " + usuario.getCarrito().getNumeroProductos() + " " + "Productos en su carrito");
		this.mensajeLbl.setAlignment(Pos.CENTER_RIGHT);
		
		this.comprarBtn.setMinHeight(50);
                this.comprarBtn.setMinWidth(150);
                HBox comprarCj = new HBox();
		comprarCj.setAlignment(Pos.CENTER_RIGHT);
                comprarCj.getChildren().addAll(this.mensajeLbl,this.comprarBtn);
		comprarCj.setPadding(new Insets(0, 20, 0, 0));

		HBox salirCj = new HBox();
		salirCj.setAlignment(Pos.CENTER_RIGHT);
                salirCj.getChildren().addAll(this.salirBtn);
                salirCj.setPadding(new Insets(0, 20, 0, 0));

		grid.add(encabezado1, 0,0,5,1);
		grid.add(titulos, 0,1,1,2);
		grid.add(depas, 1,1,1,1);
		//grid.add(this.mensajeLbl,0,2);
		//grid.add(this.comprarBtn,1,2);
		grid.add(comprarCj,0,2,2,1);
		grid.add(salirCj,1,3);
		
		//grid.setGridLinesVisible(true);
		
		//creacion de la escena
		Scene sceneGrid = new Scene(grid, 550, 650);
		
		this.setTitle("La Tiendita de la esquina ");
		this.setScene(sceneGrid);
		this.initModality(Modality.NONE);
		this.show();  
			

	}
}
