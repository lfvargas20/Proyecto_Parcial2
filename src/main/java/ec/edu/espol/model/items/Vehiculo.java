/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.items;

import ec.edu.espol.model.Util;
import ec.edu.espol.model.Util;
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
import java.util.Arrays;
/**
 *
 * @author Jose
 */
public abstract class Vehiculo implements Serializable{
    protected String placa;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String transmision;
    protected double precio;
    protected String link;
    
    public Vehiculo(String placa,int año, double recorrido, String color, String transmision,double precio){
        this.placa = placa;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.transmision = transmision;
        this.precio = precio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public abstract String getTipo();
    
    
    public static boolean comprobarRegistro(String matricula,String datos){
        ArrayList<Vehiculo> vehi = Vehiculo.leer(datos);
        for (Vehiculo v:vehi){
            if(v.getPlaca().equals(matricula))
                return false;
        }
        return true;
    }
    public static void serializar(ArrayList<Vehiculo> array,String archivo){
        try(FileOutputStream fout = new FileOutputStream(archivo)){
            try(ObjectOutputStream out = new ObjectOutputStream(fout)){
            out.writeObject(array);
            }   
        }catch(IOException e){
                System.out.println(e);
        }
    }
    
    public static ArrayList<Vehiculo> leer(String archivo){
        ArrayList<Vehiculo> lista = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))){
            lista = (ArrayList<Vehiculo>)in.readObject();
        }catch(Exception e){
            System.out.println(e);
        }
        return lista;
    }
    
    
    public static double MaxPrecio(ArrayList<Vehiculo> array){
        ArrayList<Double> precios = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            precios.add(vehiculo.precio);
        }
        double max = precios.get(0);
        for(double precio:precios){
            if (max<precio)
                max = precio;
        }
        return max;
    }
    public static double MinPrecio(ArrayList<Vehiculo> array){
        ArrayList<Double> precios = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            precios.add(vehiculo.precio);
        }
        double min = precios.get(0);
        for(double preci:precios){
            if (min>preci)
                min = preci;
        }
        return min;
    }
    public static double MaxRecorrido(ArrayList<Vehiculo> array){
        ArrayList<Double> recorridos = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            recorridos.add(vehiculo.recorrido);
        }
        double max = recorridos.get(0);
        for(double reco:recorridos){
            if (max<reco)
                max = reco;
        }
        return max;
    }
    public static double MinRecorrido(ArrayList<Vehiculo> array){
        ArrayList<Double> recorridos = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            recorridos.add(vehiculo.recorrido);
        }
        double min = recorridos.get(0);
        for(double reco:recorridos){
            if (min>reco)
                min = reco;
        }
        return min;
    }
    public static int MaxAño(ArrayList<Vehiculo> array){
        ArrayList<Integer> años = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            años.add(vehiculo.año);
        }
        int max = años.get(0);
        for(int añ:años){
            if (max<añ)
                max = añ;
        }
        return max;
    }
    public static int MinAño(ArrayList<Vehiculo> array){
        ArrayList<Integer> años = new ArrayList<>();
        for (Vehiculo vehiculo:array){
            años.add(vehiculo.año);
        }
        int min = años.get(0);
        for(int añ:años){
            if (min>añ)
                min = añ;
        }
        return min;
    }
    
    public static int seleccionarTipo(){
        int num = Util.pedirIntEnIntervalo(1, 4);
        return num;
    }
    
    public static ArrayList<String> atributos(){
        ArrayList<String> lista = new ArrayList(Arrays.asList("Placa","Año","Recorrido",
                "Color","Transmisión","Precio","Marca","Modelo","Motor","Vidrio","Combustible"));
        return lista;
    }
    
    @Override
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        Vehiculo other = (Vehiculo)o;
        if (this.placa.equals(other.placa))
            return true;
        return false;
    }
}
