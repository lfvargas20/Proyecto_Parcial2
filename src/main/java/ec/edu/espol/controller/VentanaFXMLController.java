/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.items.Vehiculo;
import ec.edu.espol.model.users.Cuenta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jose
 */
public class VentanaFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtusuariologin;
    @FXML
    private PasswordField txtcontralogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
    }    
    
    @FXML
    private void CambiarAOpciones(MouseEvent event) {
        ArrayList<Cuenta> usu = Cuenta.leer("cuentas");
        if (Cuenta.verificarCuenta(txtusuariologin.getText(),txtcontralogin.getText(),"cuentas")){
            ArrayList<String> dat = Cuenta.datos(txtusuariologin.getText());
            try {
                FXMLLoader fxmlloader = App.loadFXMLLoader("OpcionesFXML");
                App.setRoot(fxmlloader);
                App.setHeight(205);
                App.setWidth(480);
                OpcionesFXMLController bc = fxmlloader.getController();
                bc.setDatos(dat);
                bc.generarBotones();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de escena");
                a.show();   
            }   
        }
        else{
            Alert a = new Alert(Alert.AlertType.ERROR,"Los datos introducidos son incorrectos.");
            a.show();
        }
    }
    
    @FXML
    private void CambiarAR(MouseEvent e){
        try {
            App.setRoot("RegistroFXML",300,500);
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }
    
    
}
