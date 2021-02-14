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

/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* 
* @version 1.0
* @since   2014-03-31 
*/

public class VentanaRecibo extends Stage {
    private Boton volverBtn;
    private Etiqueta subtotalLbl;
    private Etiqueta numSubtotalLbl;
    private Etiqueta impuestosLbl;
    private Etiqueta numImpuestosLbl;
    private Etiqueta totalLbl;
    private Etiqueta numTotalLbl;
    private ListaDesplegable numDeProdListaD;
    private Carrito carrito;	
	
	public VentanaRecibo(Carrito carrito){
		
		this.carrito = carrito;
    
		VBox panelPrincipal = new VBox(50);
		panelPrincipal.setAlignment(Pos.CENTER);
		panelPrincipal.setPadding(new Insets(10, 20, 20, 20));
	
		Texto encabezado1 = new Texto("	La Tiendita de la Esquina ");
		encabezado1.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD,27));
		encabezado1.setTextAlignment(TextAlignment.CENTER);		
	
		Texto encabezado2 = new Texto(" CARRITO DE COMPRAS " + "\n" + " RECIBO ");
		encabezado2.setFont(Font.font("Courier New", FontWeight.NORMAL,20));
		encabezado2.setTextAlignment(TextAlignment.CENTER);

		GridPane reciboGrid = new GridPane();
		reciboGrid.setGridLinesVisible(true);
		ScrollPane scrollPane = new ScrollPane();
		Texto descripcion = new Texto("Descripcion");
		Texto precio = new Texto("Precio Unitario");
		Texto cantidad = new Texto("Cantidad");
		Texto subtotal = new Texto(" Subtotal ");
		reciboGrid.add(descripcion,0,0);
		reciboGrid.add(precio,1,0);
		reciboGrid.add(cantidad,2,0);
		reciboGrid.add(subtotal,3,0);

		for(int i = 0; i < carrito.getProducto().size(); i++) {
			reciboGrid.add(new Texto(carrito.getProducto(i).getNombre()),0,i+1);
			reciboGrid.add(new Texto(String.valueOf(carrito.getProducto(i).getPrecio())),1,i+1);
			reciboGrid.add(new Texto(String.valueOf(carrito.getNumeroElementos(i))),2,i+1);
			reciboGrid.add(new Texto(String.valueOf(carrito.getProducto(i).getPrecio()*carrito.getNumeroElementos(i))),3,i+1);
		}
		scrollPane.setContent(reciboGrid);

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

                this.numSubtotalLbl = new Etiqueta("$" + String.valueOf(df.format(carrito.getSubtotal())));
                this.numImpuestosLbl = new Etiqueta("$" + String.valueOf(df.format(carrito.getImpuestos())));
                this.numTotalLbl = new Etiqueta("$" + String.valueOf(df.format(carrito.getTotal())));
		
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
		
		this.volverBtn= new Boton("Regresar al Menú", new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				close();
			}
		});
		
		HBox boton = new HBox();
		boton.setSpacing(20);
		boton.setAlignment(Pos.CENTER_RIGHT);
		boton.getChildren().addAll(this.volverBtn);

		panelPrincipal.getChildren().addAll(encabezado1,encabezado2,scrollPane,seccionCj,boton);
		
		//grid.setGridLinesVisible(true);
		
		//creacion de la escena
		Scene sceneGrid = new Scene(panelPrincipal, 600, 600);
		
		this.setTitle("La Tiendita de la Esquina");
		this.setScene(sceneGrid);
		this.initModality(Modality.NONE);
		this.show();  
	}

}
