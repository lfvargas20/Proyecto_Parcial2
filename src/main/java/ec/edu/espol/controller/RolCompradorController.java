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
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Edwin Chavez
 */
public class RolCompradorController implements Initializable {

private ArrayList<String> usuario;
    @FXML
    private ComboBox cbx1;

    
   
    @FXML
    private ComboBox cbx2;
  
    @FXML
    private ComboBox cbx3;
  
    @FXML
    private ComboBox cbx7;
    @FXML
    private ComboBox cbx6;
    @FXML
    private ComboBox cbx5;
    @FXML
    private ComboBox cbx4;
    @FXML
    private VBox fotos;
    
   
    
    /**
     * Initializes the controller class.
     */
    ArrayList<Vehiculo> filtrada = new ArrayList<>();
    ArrayList<Vehiculo> filtrada2 = new ArrayList<>();
    ArrayList<Vehiculo> filtrada3 = new ArrayList<>();
    ArrayList<Vehiculo> filtrada4 = new ArrayList<>();
    @FXML
    private HBox hBoxOferta;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    hBoxOferta.setSpacing(10);
    ArrayList<String> tipos = new ArrayList<>();
    tipos.add("Auto"); tipos.add("Camion");
    tipos.add("Camioneta");tipos.add("Moto");
    cbx1.setItems(FXCollections.observableArrayList(tipos));
    ArrayList<Vehiculo> vehiculos = Vehiculo.leer("vehiculos");
    ArrayList<Integer> anio = new ArrayList<>();
    ArrayList<Double> precio = new ArrayList<>();
    ArrayList<Double> recorrido = new ArrayList<>() ;
    for(Vehiculo v:vehiculos){
        anio.add(v.getAño());
        precio.add(v.getPrecio());
        recorrido.add(v.getRecorrido());
    }
    cbx2.setItems(FXCollections.observableArrayList(anio));
    cbx3.setItems(FXCollections.observableArrayList(anio));
    cbx4.setItems(FXCollections.observableArrayList(recorrido));
    cbx5.setItems(FXCollections.observableArrayList(recorrido));
    cbx6.setItems(FXCollections.observableArrayList(precio));
    cbx7.setItems(FXCollections.observableArrayList(precio));
    fotos.setSpacing(15);
    ArrayList<Oferta> ofertas = Oferta.leer("ofertas");
    for(Oferta o : ofertas){
        System.out.println(o);
    }
    }
    

    
    public void setUsuario(ArrayList<String> datos){
        this.usuario = datos;
    }
    
    private void ofertar(ImageView v){
        v.setOnMouseClicked(new EventHandler<MouseEvent> () {
                @Override
                public void handle(MouseEvent t) {
                    hBoxOferta.getChildren().clear();
                    TextField txtF = new TextField();
                    Button of = new Button("OFERTAR");
                    txtF.setTextFormatter(new TextFormatter<> (change ->(change.getControlNewText().matches("([0.00-9.99]*)?"))? change:null));
                    hBoxOferta.getChildren().add(txtF);
                    hBoxOferta.getChildren().add(of);
                    
                    of.setOnMouseClicked(new EventHandler<MouseEvent> () {
                        @Override
                        public void handle(MouseEvent t) {
                            if(txtF.getText().equals("")){
                                Alert al = new Alert(Alert.AlertType.WARNING, "no hay valor a ofertar");
                                al.show();
                            }else{
                                String nombre = usuario.get(0)+" "+usuario.get(1);
                                String correo = usuario.get(2);
                                Image im = v.getImage();
                                Vehiculo ve = null;
                                String[] seg = im.getUrl().split("/");
                                for(Vehiculo v : filtrada){
                                    if(seg[seg.length-1].equals(v.getLink())){
                                       ve = v; 
                                    }
                                }
                                double precioV = ve.getPrecio();
                                String placa = ve.getPlaca();
                                double precioC = Double.parseDouble(txtF.getText());
                                Oferta o = new Oferta(placa, correo, precioV, nombre, precioC);
                                ArrayList<Oferta> ofertas = Oferta.leer("ofertas");
                                ofertas.add(o);
                                Oferta.serializar(ofertas, "ofertas");
                            }
                        }
                    }); 
                }
            });
    }
    

    @FXML
    private void regresar(ActionEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("OpcionesFXML");
            App.setRoot(fxmlloader);
            App.setWidth(480);
            App.setHeight(205);
            OpcionesFXMLController bc = fxmlloader.getController();
            bc.setDatos(usuario);
            bc.generarBotones();
        }catch(IOException e){
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo cambiar de ventana");
            a.show();
        }
    }
    
    @FXML
    private void buscarAutos(MouseEvent event) {
        ArrayList<Vehiculo> vehic = Vehiculo.leer("vehiculos");
        filtrada.clear();
        filtrada2.clear();
        filtrada3.clear();
        filtrada4.clear();
        for(Vehiculo v : vehic){
            if((String)cbx1.getValue() == null){
                filtrada.add(v);
            }else if(v.getTipo().equals((String)cbx1.getValue())){
                filtrada.add(v);
            }
        }
        for(Vehiculo v1 : filtrada){
            Integer añoIni = (Integer)cbx2.getValue();
            Integer añoFin = (Integer)cbx3.getValue();
            if(añoIni == null && añoFin==null){
                filtrada2.add(v1);
            }else if(añoIni == null && v1.getAño()<= añoFin){
                filtrada2.add(v1);
            }else if(v1.getAño()>= añoIni && añoFin == null){
                filtrada2.add(v1);
            }else if((v1.getAño() >= añoIni) && (v1.getAño() <= añoFin)){
                filtrada2.add(v1);
            }
        }
        for(Vehiculo v2 : filtrada2){
            Double recoIni = (Double)cbx4.getValue();
            Double recoFin = (Double)cbx5.getValue();
            if(recoIni == null && recoFin==null){
                filtrada3.add(v2);
            }else if(recoIni == null && v2.getRecorrido()<= recoFin){
                filtrada3.add(v2);
            }else if(v2.getRecorrido()>= recoIni && recoFin == null){
                filtrada3.add(v2);
            }else if((v2.getRecorrido()>= recoIni) && (v2.getRecorrido()<= recoFin)){
                filtrada3.add(v2);
            }
        }
        for(Vehiculo v3 : filtrada3){
            Double precioIni = (Double)cbx6.getValue();
            Double precioFin = (Double)cbx7.getValue();
            if(precioIni == null && precioFin==null){
                filtrada4.add(v3);
            }else if(precioIni == null && v3.getPrecio() <= precioFin){
                filtrada4.add(v3);
            }else if(v3.getPrecio()>= precioIni && precioFin == null){
                filtrada4.add(v3);
            }else if((v3.getPrecio()>= precioIni) && (v3.getPrecio()<= precioFin)){
                filtrada4.add(v3);
            }
        }
        fotos.getChildren().clear();
        for(Vehiculo v : filtrada4){
            HBox datosCarro = new HBox();
            Text t = new Text(v.toString());
            datosCarro.setSpacing(10);
            Image img = new Image("img/"+v.getLink());
            ImageView imgview = new ImageView(img);
            imgview.setFitWidth(150);
            imgview.setFitHeight(150);
            datosCarro.getChildren().add(t);
            datosCarro.getChildren().add(imgview);
            fotos.getChildren().add(datosCarro);
            ofertar(imgview);
        }
    }

}