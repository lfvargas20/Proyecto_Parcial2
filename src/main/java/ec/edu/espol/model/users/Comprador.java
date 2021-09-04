/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.users;

import ec.edu.espol.model.users.Cuenta;
import ec.edu.espol.model.Util;
/**
 *
 * @author Jose
 */
public class Comprador extends Cuenta{
    
    public Comprador(String nombre, String apellido, String email, String organizacion, String usuario, String contraseña,String nivelAcceso){
        super(nombre,apellido,email,organizacion,usuario,contraseña,nivelAcceso);
    }
   
    
public static double[] rangoRecorrido(double inicio, double fin){
        double[] rango = Util.pedirDoublePosIntervalo(inicio,fin);
        return rango;
    }
    public static int[] rangoAño(int inicio, int fin){
        int[] rango = Util.pedirIntPosIntervalo(inicio,fin);
        return rango;
    }
    public static double[] rangoPrecio(double inicio, double fin){
        double[] rango = Util.pedirDoublePosIntervalo(inicio,fin);
        return rango;
    }

   
    
    
    
}
