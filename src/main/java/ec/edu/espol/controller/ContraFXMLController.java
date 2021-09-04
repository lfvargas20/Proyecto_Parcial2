/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.users.Cuenta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Jose
 */
public class ContraFXMLController implements Initializable {


    @FXML
    private TextField contravieja;
    @FXML
    private TextField contranueva;
    @FXML
    private TextField contranuevaconf;
    
    private String usuario;
    private ArrayList<String> datos;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public void setDatos(ArrayList<String> datos){
        this.datos = datos;
    }
    
    @FXML
    private void RegresarADatos(MouseEvent event) {
        try {
            cambiarRootADatos();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }
    
    public void cambiarRootADatos() throws IOException{
            FXMLLoader fxmlloader = App.loadFXMLLoader("DatosFXML");
            App.setRoot(fxmlloader);
            App.setWidth(510);
            App.setHeight(278);
            DatosFXMLController bc = fxmlloader.getController();
            bc.setDatos(Cuenta.datos(usuario));
    }
    public void alertaE(String cad){
            Alert a = new Alert(Alert.AlertType.ERROR,cad);
            a.show();
    }
    public void alertaW(String cad){
        Alert a = new Alert(Alert.AlertType.WARNING,cad);
        a.show();
    }
    @FXML
    private void ConfirmarYRegresar(MouseEvent event) {
        try {
        ArrayList<Cuenta> cuentas = Cuenta.leer("cuentas");
        for(Cuenta c:cuentas){
            if (c.getUsuario().equals(usuario)){
                if(contravieja.getText().equals(c.getContraseña())){
                    if(contranueva.getText().equals(contranuevaconf.getText())){
                        c.setContraseña(contranueva.getText());
                        Cuenta.serializar(cuentas, "cuentas");
                        cambiarRootADatos();
                    }else{
                        alertaW("Las contraseñas no coinciden.");
                    }
                }else{
                    alertaW("La contraseña original no es correcta.");
                }}}
        }catch (IOException ex) {
            alertaE("No se pudo cambiar de ventana");
        }
    }

}
