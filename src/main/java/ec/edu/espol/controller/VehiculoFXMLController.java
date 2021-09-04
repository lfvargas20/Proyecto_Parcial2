/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.MalosDatosException;
import ec.edu.espol.model.ObjetoYaRegistradoException;
import ec.edu.espol.model.items.Auto;
import ec.edu.espol.model.items.Camion;
import ec.edu.espol.model.items.Camioneta;
import ec.edu.espol.model.items.Moto;
import ec.edu.espol.model.items.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * FXML Controller class
 *
 * @author Jose
 */
public class VehiculoFXMLController implements Initializable {

    private ArrayList<String> usuario;
    @FXML
    private ComboBox claseV;
    @FXML
    private FlowPane panel;
    @FXML
    private Button sf;
    @FXML
    private Text txtimg;
    
    private ArrayList<String> datos;

    
    public void setUsuario(ArrayList<String> datos){
        this.usuario = datos;
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> nombres = new ArrayList(Arrays.asList("Auto","Camion","Camioneta","Moto"));
        claseV.setItems(FXCollections.observableList(nombres));
        panel.setHgap(10);
       
    }    
     public void setDatos(ArrayList<String> datos){
        this.datos = datos;
    }
     
    public void añadirCaja(String cad){
        VBox vb = new VBox();
        Text txt = new Text(cad);
        vb.setSpacing(10);
        TextField tf = new TextField();
        vb.getChildren().add(txt);
        vb.getChildren().add(tf);
        panel.getChildren().add(vb);
    }
    
    public void añadirCajaNumeros(String cad, String atributo){
        VBox vb = new VBox();
        Text txt = new Text(cad);
        vb.setSpacing(10);
        TextField tf = new TextField();
        if(atributo.equals("Recorrido") || atributo.equals("Precio")){
            tf.setTextFormatter(new TextFormatter<> (change ->(change.getControlNewText().matches("([0.00-9.99]*)?"))? change:null));
        }else{
            tf.setTextFormatter(new TextFormatter<> (change ->(change.getControlNewText().matches("([0-9]*)?"))? change:null));
        }
        vb.getChildren().add(txt);
        vb.getChildren().add(tf);
        panel.getChildren().add(vb);
    }
    
    @FXML
        private void cb(ActionEvent event) {
            ComboBox cb = (ComboBox)event.getSource();
            String tipo = (String)cb.getValue();
            panel.getChildren().clear();
            if(tipo.equals("Auto") || tipo.equals("Camion") || tipo.equals("Camioneta")){
                ArrayList<String> atrib = Vehiculo.atributos();
                for(String cad:atrib)
                    if(cad.equals("Año") || cad.equals("Recorrido") || cad.equals("Precio"))
                        añadirCajaNumeros(cad+":", cad);
                    else
                        añadirCaja(cad+":");
            }else{
                ArrayList<String> atrib = Moto.Atributos();
                for(String cad:atrib)
                    if(cad.equals("Año") || cad.equals("Recorrido") || cad.equals("Precio"))
                        añadirCajaNumeros(cad+":", cad);
                    else
                        añadirCaja(cad+":");
            }
            
            
    }
        
    public ArrayList<String> informacion() throws MalosDatosException{ //Aquí se podría añadir una Exception por si no estan todas las casillas llenadas.
        ArrayList<String> info = new ArrayList<>();
        VBox vb;
        for(int i = 0; i<panel.getChildren().size();i++){
            vb =(VBox)panel.getChildren().get(i);
            TextField tf =(TextField)vb.getChildren().get(1);
            info.add(tf.getText());
        }
        for(int j=0;j<info.size();j++){
            if(info.get(j).equals("")){
                throw new MalosDatosException("Uno de los datos esta vacio.");
            }
        }
        return info;
    }    
    public Auto crearAuto() throws MalosDatosException, ObjetoYaRegistradoException{
        ArrayList<String> info = informacion();
        int año = Integer.parseInt(info.get(1));
        double recorrido = Double.parseDouble(info.get(2));
        double precio = Double.parseDouble(info.get(5));
        if(año>0 && recorrido>0 && precio>0 && Vehiculo.comprobarRegistro(info.get(0), "vehiculos")){
        return new Auto(info.get(0),año,recorrido,info.get(3),info.get(4),precio,info.get(6),info.get(7),info.get(8),info.get(9),info.get(10));
        }else
            if (Vehiculo.comprobarRegistro(info.get(0), "vehiculos"))
                throw new ObjetoYaRegistradoException("Esa matrícula ya esta tomada");
            else
                throw new MalosDatosException("Ingreso de datos inválido. Revise que no introdujo un valor negativo.");
    }
    public Camion crearCamion() throws MalosDatosException, ObjetoYaRegistradoException{
        ArrayList<String> info = informacion();
        int año = Integer.parseInt(info.get(1));
        double recorrido = Double.parseDouble(info.get(2));
        double precio = Double.parseDouble(info.get(5));
        if(año>0 && recorrido>0 && precio>0 && Vehiculo.comprobarRegistro(info.get(0), "vehiculos")){
        return new Camion(info.get(0),año,recorrido,info.get(3),info.get(4),precio,info.get(6),info.get(7),info.get(8),info.get(9),info.get(10));
        }else
            if (!Vehiculo.comprobarRegistro(info.get(0), "vehiculos"))
                throw new ObjetoYaRegistradoException("Esa matrícula ya esta tomada");
            else
                throw new MalosDatosException("Ingreso de datos inválido. Revise que no introdujo un valor negativo.");
        }
    public Camioneta crearCamioneta() throws MalosDatosException, ObjetoYaRegistradoException{
        ArrayList<String> info = informacion();
        int año = Integer.parseInt(info.get(1));
        double recorrido = Double.parseDouble(info.get(2));
        double precio = Double.parseDouble(info.get(5));
        if(año>0 && recorrido>0 && precio>0 && Vehiculo.comprobarRegistro(info.get(0), "vehiculos")){
        return new Camioneta(info.get(0),año,recorrido,info.get(3),info.get(4),precio,info.get(6),info.get(7),info.get(8),info.get(9),info.get(10));
        }else
            if (!Vehiculo.comprobarRegistro(info.get(0), "vehiculos"))
                throw new ObjetoYaRegistradoException("Esa matrícula ya esta tomada");
            else
                throw new MalosDatosException("Ingreso de datos inválido. Revise que no introdujo un valor negativo.");
    }
    public Moto crearMoto() throws MalosDatosException, ObjetoYaRegistradoException{
        ArrayList<String> info = informacion();
        int año = Integer.parseInt(info.get(1));
        double recorrido = Double.parseDouble(info.get(2));
        double precio = Double.parseDouble(info.get(5));
        if(año>0 && recorrido>0 && precio>0 && Vehiculo.comprobarRegistro(info.get(0), "vehiculos")){
        return new Moto(info.get(0),año,recorrido,info.get(3),info.get(4),precio,info.get(6),info.get(7),info.get(8),info.get(9));
        }else
            if (!Vehiculo.comprobarRegistro(info.get(0), "vehiculos"))
                throw new ObjetoYaRegistradoException("Esa matrícula ya esta tomada");
            else
                throw new MalosDatosException("Ingreso de datos inválido. Revise que no introdujo un valor negativo.");
    }
    public void crearVehiculo() throws MalosDatosException, ObjetoYaRegistradoException{
        ArrayList<Vehiculo> vehi = Vehiculo.leer("vehiculos");
        Vehiculo v1 = null;
        String cad = (String)claseV.getValue();
        try {
            if (cad.equals("Auto")){
                v1 = crearAuto();
            }
            else if(cad.equals("Camion")){
                v1 = crearCamion();
            }
            else if (cad.equals("Camioneta")){
                v1 = crearCamioneta();
            }
            else if (cad.equals("Moto")){
                v1 = crearMoto();
            }
        } catch(MalosDatosException ex){
            throw new MalosDatosException(ex.getMessage());
        } catch (ObjetoYaRegistradoException ex) {
            throw new ObjetoYaRegistradoException(ex.getMessage());
        }
        v1.setLink(txtimg.getText());
        vehi.add(v1);
        Vehiculo.serializar(vehi, "vehiculos");
    }
    public void volver() throws IOException{
        FXMLLoader fxmlloader = App.loadFXMLLoader("OpcionesFXML");
        App.setRoot(fxmlloader);
        App.setHeight(205);
        App.setWidth(480);
        OpcionesFXMLController bc = fxmlloader.getController();
        bc.setDatos(datos);
        bc.generarBotones();
    }
        
    
    @FXML
    private void subirfoto(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Imagenes","*.jpg","*.jpeg","*.png"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            String placeToSaveFile = "src/main/resources/img/"+selectedFile.getName();
            Files.copy(selectedFile.toPath(),new File(placeToSaveFile).toPath(),StandardCopyOption.REPLACE_EXISTING);
            txtimg.setText(selectedFile.getName());
        } else {
            txtimg.setText("");
        }
    }
    
    
    
    @FXML
    private void RegresarAOpciones(MouseEvent event) {
        try {
        volver();
        }catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }
    
    
    //Se deben añadir mensajes de alerta por Excepciones cuando:
    //2.- No se haya subido una imagen
    @FXML
    private void ConfirmarYRegresar(MouseEvent event) {
        try {
        if(txtimg.getText().equals(("")))
            throw new MalosDatosException("No ha subido una foto del vehículo");
        crearVehiculo();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Vehículo añadido con exito");
        a.show();
        volver();
        }
        catch (IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        } catch (MalosDatosException | ObjetoYaRegistradoException ex) {
            Alert a = new Alert (Alert.AlertType.WARNING, ex.getMessage());
            a.show();
        }
    }    
}

