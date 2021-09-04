/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.ObjetoYaRegistradoException;
import ec.edu.espol.model.users.Cuenta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Jose
 */
public class RegistroFXMLController implements Initializable {


    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtapellido;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtorganizacion;
    @FXML
    private TextField txtusuario;
    @FXML
    private TextField txtcontraseña;
    @FXML
    private CheckBox checkcomprador;
    @FXML
    private CheckBox checkvendedor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void RegistrarVolverAV(MouseEvent event) {
    if( (!(txtnombre.getText().equals("")||txtapellido.getText().equals("")||txtemail.getText().equals("")
            ||txtorganizacion.getText().equals("")||txtusuario.getText().equals("")||txtcontraseña.getText().equals("")))
            && !(checkcomprador.selectedProperty().getValue()==false && checkvendedor.selectedProperty().getValue()==false) ){
        try{    
        if(Cuenta.verificarUsuariodisponible(txtusuario.getText(), txtemail.getText(),"cuentas")){
            try{
            String num = "0";
            if (checkcomprador.selectedProperty().getValue() && checkvendedor.selectedProperty().getValue())
                num = "3";
            else if (checkcomprador.selectedProperty().getValue())
                num = "1";
            else if (checkvendedor.selectedProperty().getValue())
                num = "2";
            Cuenta p1 = new Cuenta(txtnombre.getText(),txtapellido.getText(),txtemail.getText(),
                    txtorganizacion.getText(),txtusuario.getText(),txtcontraseña.getText(),num);
            ArrayList<Cuenta> arr = Cuenta.leer("cuentas");
            arr.add(p1);
            Cuenta.serializar(arr,"cuentas");
            } catch (Exception e1) {
                Alert a = new Alert(Alert.AlertType.WARNING,"No se han completado correctamente los campos.");
                a.show();
            }
            try {
                App.setRoot("VentanaFXML",400,215);
                    } 
            catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambir las ventanas");
                a.show();
                }
            }else{
                throw new ObjetoYaRegistradoException("Este usuario o correo ya esta registrado.");
            }
        } catch (ObjetoYaRegistradoException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING,ex.getMessage());
            a.show();
        }
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING,"No se han completado correctamente los campos.");
            a.show();
        }
    
    }

    @FXML
    private void VolverAV(MouseEvent event) {
        try {
            App.setRoot("VentanaFXML",400,215);
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }

}
