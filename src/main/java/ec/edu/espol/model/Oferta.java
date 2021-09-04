/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;
import ec.edu.espol.model.items.Vehiculo;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Jose
 */
public class Oferta implements Serializable{
    
    private String placa;
    private String correo;
    private double precioV;
    private double precioC;
    private String nombre;
    
    public Oferta(String p, String c, double preV, String n, double precioC){
        this.placa = p;
        this.nombre = n;
        this.precioV = preV;
        this.precioC = precioC;
        this.correo = c;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCorreo() {
        return correo;
    }


    public String getNombre() {
        return nombre;
    }

    public double getPrecioV() {
        return precioV;
    }

    public double getPrecioC() {
        return precioC;
    }
    
    

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    public void setPrecioC(double precioC) {
        this.precioC = precioC;
    }
    
    
    
    
    public static void serializar(ArrayList<Oferta> array,String archivo){
        try(FileOutputStream fout = new FileOutputStream(archivo)){
            try(ObjectOutputStream out = new ObjectOutputStream(fout)){
            out.writeObject(array);
            }   
        }catch(IOException e){
                System.out.println(e);
        }
    }

    public static ArrayList<Oferta> leer(String archivo){
        ArrayList<Oferta> lista = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))){
            lista = (ArrayList<Oferta>)in.readObject();
        }catch(Exception e){
            System.out.println(e);
        }
        return lista;
    }
    
    @Override
    public String toString(){
        return "Nombre del comprador: "+this.nombre+"\nCorreo del comprador: "+this.correo+"\nPlaca del carro: "+this.placa+"\nPrecio inicial: "+this.precioV+"\nPrecio ofertado: "+this.precioC;
    }
}
