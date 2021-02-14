package controles;

import java.util.List;

import javafx.scene.control.ComboBox;
 import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
 
 public class ListaDesplegable extends ComboBox<String>{
     public ListaDesplegable(List<String> elementos, EventHandler<ActionEvent> e){
         super();
         ObservableList<String> oList= FXCollections.observableArrayList(elementos);
         this.setItems(oList);
         this.getSelectionModel().selectFirst();
         this.setOnAction(e);
     }
     public ListaDesplegable(List<String> elementos){
         super();
         ObservableList<String> oList= FXCollections.observableArrayList(elementos);
         this.setItems(oList);
         this.getSelectionModel().selectFirst();
     }

 } 
