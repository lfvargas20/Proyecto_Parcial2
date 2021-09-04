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

import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Jose
 */
public class DatosFXMLController implements Initializable {


    @FXML
    private CheckBox checkcomprador;
    @FXML
    private CheckBox checkvendedor;
    @FXML
    private Text nom;
    @FXML
    private Text ape;
    @FXML
    private Text ema;
    @FXML
    private Text org;
    @FXML
    private Text usu;
    @FXML
    private Text contra;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private ArrayList<String> datos;
    
    public void setDatos(ArrayList<String> datos){
        this.datos = datos;
        setDatosText();
        estadoRol();
    }
    
    public void setDatosText(){
        nom.setText(datos.get(0));
        ape.setText(datos.get(1));
        ema.setText(datos.get(2));
        org.setText(datos.get(3));
        usu.setText(datos.get(4));
        contra.setText(datos.get(5));
    }
    
    public void estadoRol(){
        if(datos.get(6).equals("1"))
            checkcomprador.setSelected(true);
        else if(datos.get(6).equals("2"))
            checkvendedor.setSelected(true);
        else if(datos.get(6).equals("3")){
            checkcomprador.setSelected(true);
            checkvendedor.setSelected(true);}
        }
    
    
    
    @FXML
    private void CambiarAContraseña(MouseEvent event) {
        try {
                FXMLLoader fxmlloader = App.loadFXMLLoader("ContraFXML");
                App.setRoot(fxmlloader);
                App.setWidth(238);
                App.setHeight(293);
                ContraFXMLController bc = fxmlloader.getController();
                bc.setUsuario(usu.getText());
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }

    @FXML
    private void VolverAOpciones(MouseEvent event) {
        if(!(checkcomprador.selectedProperty().getValue()==false && checkvendedor.selectedProperty().getValue()==false)){
        try {
            ArrayList<Cuenta> cuentas = Cuenta.leer("cuentas");
            String num = "0";
            if (checkcomprador.selectedProperty().getValue() && checkvendedor.selectedProperty().getValue())
                num = "3";
            else if (checkcomprador.selectedProperty().getValue())
                num = "1";
            else if (checkvendedor.selectedProperty().getValue())
                num = "2";
            for(Cuenta c:cuentas){
            if (c.getUsuario().equals(datos.get(4)))
                c.setNivelAcceso(num);}
            Cuenta.serializar(cuentas, "cuentas");
                FXMLLoader fxmlloader = App.loadFXMLLoader("OpcionesFXML");
                App.setRoot(fxmlloader);
                App.setWidth(480);
                App.setHeight(205);
                OpcionesFXMLController bc = fxmlloader.getController();
                bc.setDatos(Cuenta.datos(usu.getText()));
                bc.generarBotones();
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING,"Tiene que seleccionar al menos un rol.");
            a.show();
        }
    }

}
