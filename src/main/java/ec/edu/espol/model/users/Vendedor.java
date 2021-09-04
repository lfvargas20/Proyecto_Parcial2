/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.users;
import ec.edu.espol.model.Util;
import ec.edu.espol.model.Util;
import ec.edu.espol.model.users.Cuenta;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Vendedor extends Cuenta{
    public Vendedor(){} 
    
    public Vendedor(String nombre, String apellido, String email, String organizacion, String usuario, String contraseña,String nivelAcceso){
        super(nombre,apellido,email,organizacion,usuario,contraseña,nivelAcceso);
    }
    
        

  public static void aceptarOferta(){
        try(BufferedReader leer = new BufferedReader(new FileReader("vehiculos"))){
            try(BufferedReader ofertas = new BufferedReader(new FileReader("ofertas"))){
            System.out.println("Ingrese el numero de placa del vehiculo a buscar.");
            String placa = Util.pedirCad();
            int activacion = 0;
            String linea;
            String linea1;
            while((linea = leer.readLine()) != null){
                String[] detalles = linea.split(";");
                linea1 = ofertas.readLine();
                String [] ofer = linea1.split(";");
                if(placa.equals(ofer[1])){
                    int cont = 1;
                    activacion = 1;
                    ArrayList<String> ofertas1 = new ArrayList<>();
                    System.out.println(detalles[2]+ " "+ detalles[3]+ "\nPrecio: "+ detalles[11]);
                    String linea2;
                    ofertas1.add(linea1);
                    while((linea2 = ofertas.readLine()) != null){
                        String[] ofer1 = linea2.split(";");
                        if(placa.equals(ofer1[1])){
                            ofertas1.add(linea2);
                            cont += 1;
                        }
                    }
                        
                    System.out.println("hay "+ cont + " ofertas por ese vehiculo");
                    int n = 0;
                    int salir = 0;
                    String correo = "";
                    OUTER:
                    while (true) {   
                        int tamaño = ofertas1.size();
                        String[] display = ofertas1.get(n).split(";");
                        int numOferta = n + 1;
                        System.out.println("\nOFERTA "+numOferta+"\nCorreo: "+display[0]+"\nPrecio ofertado: "+display[2]);
                        if (n==0 && tamaño != 1) {
                            System.out.println("1.Siguiente\n2.Aceptar oferta\n3. Salir");
                            int seleccion = Util.pedirIntEnIntervalo(1, 3);
                            switch (seleccion) {
                                case 1:
                                    n++;
                                    break;
                                case 3:
                                    salir = 1;
                                    break OUTER;
                                default:
                                    correo = display[0];
                                    break OUTER;
                            }
                        } else if (n>0 && n<tamaño-1) {
                            System.out.println("1.Siguiente\n2.atras\n3.Aceptar oferta\n4. Salir");
                            int seleccion = Util.pedirIntEnIntervalo(1, 4);
                            switch (seleccion) {
                                case 1:
                                    n++;
                                    break;
                                case 2:
                                    n--;
                                    break;
                                default:
                                    correo = display[0];
                                    break OUTER;
                                case 4:
                                    salir = 1;
                                    break OUTER;
                            }
                        }
                        else if(tamaño ==1){
                            System.out.println("1. Aceptar oferta. \n2. Salir");
                            int seleccion = Util.pedirIntEnIntervalo(1, 2);
                            if(seleccion == 1){
                                correo = display[0];
                                break;
                            }
                            else{
                                salir = 1;
                                break;
                            }                                
                        }
                        else {
                            System.out.println("1.atras\n2.Aceptar oferta\n3. Salir");
                            int seleccion = Util.pedirIntEnIntervalo(1, 3);
                            switch (seleccion) {
                                case 1:
                                    n--;
                                    break;
                                case 3:
                                    salir = 1;
                                    break;
                                default:
                                    correo = display[0];
                                    break OUTER;
                            }
                        }   
                    }
                    if(salir == 1)
                        break;
                    
                }    
            }
            if(activacion == 0){
                System.out.println("no hay ofertas para ese vehiculo");
            }
            
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
               
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    } 
  
}
