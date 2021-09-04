/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Jose
 */
public class OpcionesFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ArrayList<String> datos;
    @FXML
    private VBox cajaDeComprador;
    @FXML
    private VBox cajaDeVendedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cajaDeComprador.setSpacing(15);
        cajaDeVendedor.setSpacing(15);
    }    
    
    public void setDatos(ArrayList<String> datos){
        this.datos = datos;
    }
    
    @FXML
    private void CambiarADatos(MouseEvent event) {
        try {
                FXMLLoader fxmlloader = App.loadFXMLLoader("DatosFXML");
                App.setRoot(fxmlloader);
                App.setWidth(510);
                App.setHeight(278);
                DatosFXMLController bc = fxmlloader.getController();
                bc.setDatos(datos);
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de escena");
                a.show();   
            }
    }
    
    public void generarBotones(){
        int acceso = Integer.parseInt(datos.get(6));
        Button comprar = new Button("Hacer Oferta");
        Button vender1 = new Button("Aceptar Oferta");
        Button vender2 = new Button("Ingresar Automovil");
        Text txtV = new Text("No disponible.\nNo es un vendedor.");
        Text txtC = new Text("No disponible.\nNo es un comprador");
        switch(acceso){
            case 1:
                cajaDeComprador.getChildren().add(comprar);
                cajaDeVendedor.getChildren().add(txtV);
                break;
            case 2:
                cajaDeVendedor.getChildren().add(vender1);
                cajaDeVendedor.getChildren().add(vender2);
                cajaDeComprador.getChildren().add(txtC);
                break;
            case 3:
                cajaDeComprador.getChildren().add(comprar);
                cajaDeVendedor.getChildren().add(vender2);
                cajaDeVendedor.getChildren().add(vender1);
                break;
        }
        vender1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    FXMLLoader fxmlloader = App.loadFXMLLoader("AceptarOfertaFXML");
                    App.setRoot(fxmlloader);
                    App.setWidth(625);
                    App.setHeight(425);
                    AceptarOfertaFXMLController bc = fxmlloader.getController();
                    bc.setUsuario(datos);
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
                    a.show();
                }    
            }
        });
        vender2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                   FXMLLoader fxmlloader = App.loadFXMLLoader("VehiculoFXML");
                    App.setRoot(fxmlloader);
                    App.setWidth(525);
                    App.setHeight(440);
                    VehiculoFXMLController bc = fxmlloader.getController();
                    bc.setDatos(datos);
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
                    a.show();
                }    
            }
        });
        comprar.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                try {
                   FXMLLoader fxmlloader = App.loadFXMLLoader("RolComprador");
                    App.setRoot(fxmlloader);
                    App.setWidth(890);
                    App.setHeight(595);
                    RolCompradorController bc = fxmlloader.getController();
                    bc.setUsuario(datos);
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
                    a.show();
                }    
            }
        });
    }

    @FXML
    private void CambiarALogin(MouseEvent event) {
        try {
            App.setRoot("VentanaFXML",400,215);
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }
    
    

}
