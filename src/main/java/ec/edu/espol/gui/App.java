package ec.edu.espol.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("VentanaFXML"));
        stage.setTitle("Venta de vehículos");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static void setRoot(String fxml,double ancho,double altura) throws IOException{
        scene.getWindow().setWidth(ancho);
        scene.getWindow().setHeight(altura);
        scene.setRoot(loadFXML(fxml));
    }
    public static void setHeight(double height){
        scene.getWindow().setHeight(height);
    }
    public static void setWidth(double width){
        scene.getWindow().setWidth(width);
    }
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static FXMLLoader loadFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void setRoot(FXMLLoader fxmlloader) throws IOException {
        scene.setRoot(fxmlloader.load());
    }

    public static void main(String[] args) {
        launch();
    }

}