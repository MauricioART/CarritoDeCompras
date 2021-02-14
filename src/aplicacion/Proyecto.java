package aplicacion;

import javafx.application.Application;
import javafx.stage.Stage;
import ventanas.VentanaIngreso;

public class Proyecto extends Application{
   
    	public void start(Stage primaryStage) {
    		new VentanaIngreso();
     	}
    
	public static void main(String[] args){
		launch(args);

	}
	
	
}
