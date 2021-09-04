/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.items;

import ec.edu.espol.model.items.Vehiculo;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jose
 */
public class Moto extends Vehiculo{
    private String marca;
    private String modelo;
    private String motor;
    private String combustible;
    
    public Moto(String placa,int año,double recorrido,String color, String transmision,double precio,
            String marca, String modelo,String motor, String combustible){
        super(placa,año,recorrido,color,transmision,precio);
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.combustible = combustible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
    

    public static ArrayList<String> Atributos(){
          ArrayList<String> lista = new ArrayList(Arrays.asList("Placa","Año","Recorrido",
                "Color","Transmisión:","Precio","Marca","Modelo","Motor","Combustible"));
        return lista;
    }
    
    @Override
    public String toString(){
        String cad = "Moto\nMatrícula del vehículo: " + this.placa + "\nMarca: " + this.marca +
                "\nModelo del vehículo: "+this.modelo+"\nTipo de motor: " + this.motor + "\nAño del vehículo: " + this.año +
                "\nRecorrido en km: " + this.recorrido + "\nTipo de combustible: " + this.combustible +
                "\nTransmisión del vehículo: " + this.transmision + "\nPrecio: " + this.precio +"$\n";
        return cad;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
