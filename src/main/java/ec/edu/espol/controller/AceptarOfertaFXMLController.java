/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author INTEL
 */
public class AceptarOfertaFXMLController implements Initializable {
    
    private ArrayList<String> usuario;

    @FXML
    private TextField placas;
    @FXML
    private VBox cajaDeBoton;
    @FXML
    private HBox cajaParaTexto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cajaParaTexto.setSpacing(15);
    }

    public void setUsuario(ArrayList<String> datos){
        this.usuario = datos;
    }    
    
    @FXML
    private void buscarCarro(MouseEvent event){
        if(placas.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.WARNING, "Llene la casilla de placa.");
            a.show();
        }else{
            ArrayList<Oferta> ofertas = Oferta.leer("ofertas");
            for(Oferta o : ofertas){
                if(o.getPlaca().equals(placas.getText())){
                    Text txt = new Text(o.toString());
                    cajaParaTexto.getChildren().add(txt);
                    txt.setOnMouseClicked(new EventHandler<MouseEvent> (){
                        @Override
                        public void handle(MouseEvent t) {
                            Button aceptar = new Button("ACEPTAR OFERTA");
                            cajaDeBoton.getChildren().clear();
                            cajaDeBoton.getChildren().add(aceptar);
                            aceptar.setOnMouseClicked(new EventHandler<MouseEvent> (){
                                @Override
                                public void handle(MouseEvent t) {
                                    try (InputStream inputStream = new FileInputStream(new File("correo.properties"))) {
                                    Properties prop = new Properties();
                                    prop.load(inputStream);
                                    String cuerpo = "Saludos "+o.getNombre()+"\nEl usuario " + usuario.get(0)+ " "+usuario.get(1)+" ha aceptado su oferta.\nFelicitaciones.\ntenga buen día.";
                                    Util.enviarConGMail(prop.getProperty("Correo"), prop.getProperty("Clave"), o.getCorreo(), prop.getProperty("Asunto"), cuerpo);
                                    } catch (IOException ex) {
                                        Alert a = new Alert(Alert.AlertType.ERROR, "no se encuentra el archivo");
                                        a.show();
                                    }
                                    Alert b = new Alert(Alert.AlertType.CONFIRMATION, "Se ha enviado el archivo");
                                    b.show();
                                }
                                
                            });
                        }
                    
                });
                }
            }
        }
    }

    @FXML
    private void regresarOpciones(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("OpcionesFXML");
            App.setRoot(fxmlloader);
            OpcionesFXMLController bc = fxmlloader.getController();
            bc.setDatos(usuario);
            bc.generarBotones();
        }catch(IOException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }

}

