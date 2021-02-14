package ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import controles.*;
import elementos.*;
import almacen.*;
import excepciones.*;

import java.util.ArrayList;

public class VentanaIngreso extends Stage {
	private Boton cancelarBtn;
    	private Boton ingresarBtn;
	private CajaDeTexto usuarioTxt;
	private CajaDePassword contrasenaTxt;
	private Etiqueta usuarioLbl;
	private Etiqueta contrasenaLbl;
	private Etiqueta intentosLbl;
	private Usuario usuario;
	private CargadorDeUsuarios cdu;

	ArrayList<Exception> excepciones;
	VBox cajaExcepciones;
	GridPane  grid;
	ArrayList<Etiqueta> nodos;
	Etiqueta error1;
	Etiqueta error2;
	Etiqueta error3;
	Etiqueta error4;
	Etiqueta error5;
	public VentanaIngreso(){    
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		excepciones = new ArrayList<Exception>();
				
		Texto encabezado1 = new Texto("	La Tiendita de la Esquina");
		encabezado1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD,34));
		encabezado1.setTextAlignment(TextAlignment.CENTER);
		
		this.ingresarBtn= new Boton("Ingresar", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				cdu = new CargadorDeUsuarios();
				try {
					if(contrasenaTxt.getText().equals(""))
						excepciones.add(new EmptyFieldPassWordException());
					if (usuarioTxt.getText().equals(""))
						excepciones.add(new EmptyFieldUserException());
					else {
					if(cdu.cargarUsuario(usuarioTxt.getText())){
						usuario = cdu.getUsuario();
						if (usuario.getContrasenia().equals(contrasenaTxt.getText()) && usuario.getIntentosRestantes()>0){
							usuario.setIntentosRestantes(5);
							cdu.actualizarIntentos();
							new VentanaMenu(usuario);
						}
						else {
							if(usuario.getIntentosRestantes() != 0) {
								usuario.restarIntentos();
								cdu.actualizarIntentos();
								excepciones.add(new WrongPasswordException(usuario.getIntentosRestantes()));
							}else {
								excepciones.add(new TooManyAtempsException());
							}
						}

					}
					else {
						excepciones.add(new UserNotFoundException());
					}
					}
					if(excepciones.size() != 0) {
						throw new Exception();
					}
					
				}
				catch(Exception e){
					mostrarExcepcionesMetodo(excepciones);
				}
			}
		});
		this.cancelarBtn= new Boton("Cancelar", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});
		
		HBox botones = new HBox();
		botones.setSpacing(20);
		botones.setAlignment(Pos.CENTER);
		//botones.setPadding(new Insets(0, 20, 10, 20));
		botones.getChildren().addAll(this.ingresarBtn,this.cancelarBtn);

		this.usuarioLbl = new Etiqueta("Usuario: ");
		this.usuarioLbl.setAlignment(Pos.CENTER_RIGHT);
		this.usuarioTxt = new CajaDeTexto();
		//this.usuarioTxt.setAlignment(Pos.CENTER_RIGHT);
		HBox usuarioCaja = new HBox();
		usuarioCaja.setAlignment(Pos.CENTER);
		usuarioCaja.setPadding(new Insets(0, 20, 20, 15));
		usuarioCaja.getChildren().addAll(this.usuarioLbl,this.usuarioTxt);

		this.contrasenaLbl = new Etiqueta("Contraseña: ");
		this.contrasenaLbl.setAlignment(Pos.CENTER_RIGHT);
		this.contrasenaTxt = new CajaDePassword();
		this.contrasenaTxt.setPromptText("Your password");
		
		HBox contrasenaCaja = new HBox();
		contrasenaCaja.setAlignment(Pos.CENTER);
		contrasenaCaja.setPadding(new Insets(0, 50, 20, 20));
		contrasenaCaja.getChildren().addAll(this.contrasenaLbl,this.contrasenaTxt);
		
		grid.add(encabezado1, 0,0,2,1);
		grid.add(usuarioCaja,0,4,2,1);
		grid.add(contrasenaCaja,0,5,2,1);
		grid.add(botones,0,7,2,1);
		//grid.setGridLinesVisible(true);
		
		//creacion de la escena
		Scene sceneGrid = new Scene(grid, 700, 600);
		
		this.setTitle("Carrito de compras - Inicio de Secion");
		this.setScene(sceneGrid);
		this.initModality(Modality.NONE);
		this.show();  
			

	}
	public void actualizarEtiquetaIntentos() {
		
	}
	private void mostrarExcepciones(ArrayList<Exception> l){
		nodos = new ArrayList<Etiqueta>();
		for(Exception item:l){
			Etiqueta e = new Etiqueta(item.getMessage());
			e.setTextFill(Color.RED);
			nodos.add(e);
		}
		cajaExcepciones = crearCajaV(nodos,Pos.TOP_CENTER, 5);
		grid.add(cajaExcepciones , 1, 10);
	}

	private VBox crearCajaV(ArrayList<Etiqueta> nodos, Pos posicion, double espacio){
		VBox vbox = null;
		if(nodos!=null && posicion !=null){
			vbox=new VBox(espacio);
			vbox.setAlignment(posicion);
			vbox.getChildren().addAll(nodos);
		}
		return vbox;
	}
	private void mostrarExcepcionesMetodo(ArrayList<Exception> l){
		this.removerEtiquetasExcepcion();
		for(Exception iie:l){
			if( iie instanceof TooManyAtempsException){
				this.error1 = crearEtiquetaExcepcion(iie.getMessage(),true);
				grid.add(this.error1, 1, 15,2,1);
			}else if(iie instanceof UserNotFoundException){
				this.error2 = crearEtiquetaExcepcion(iie.getMessage(),true);
				grid.add(this.error2, 1, 14,2,1);
			}else if (iie instanceof WrongPasswordException) {
                                this.error3 = crearEtiquetaExcepcion(iie.getMessage(),true);
                                grid.add(this.error3, 1, 13,2,1);
			}else if (iie instanceof EmptyFieldPassWordException) {
                                this.error4 = crearEtiquetaExcepcion(iie.getMessage(),true);
                                grid.add(this.error4, 1, 12,2,1);
			}else if (iie instanceof EmptyFieldUserException) {
                                this.error4 = crearEtiquetaExcepcion(iie.getMessage(),true);
                                grid.add(this.error4, 1, 11,2,1);
                        }
			
		}
	}
	private void removerEtiquetasExcepcion(){
		this.grid.getChildren().remove(this.error1);
		this.grid.getChildren().remove(this.error2);
		this.grid.getChildren().remove(this.error3);
	}
	private Etiqueta crearEtiquetaExcepcion(String mensaje, boolean visible){
		Etiqueta e = new Etiqueta(mensaje);
		e.setVisible(visible);
		e.setTextFill(Color.RED);
		e.setAlignment(Pos.CENTER_RIGHT);
		e.setFont(Font.font("Tahoma", FontWeight.BOLD,10));
		return e;
	}
}
