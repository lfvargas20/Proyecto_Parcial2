/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Leon
 */
public class Util {
    // LOS MÉTODOS ESTATICOS SOLO FUNCIONAN EN LA CLASE, Y SE LLAMAN CON LA CLASE
    // Los estáticos no se sobreescriben ni se heredan, como los private y los final
    
    public static int pedirInt(){
        Scanner sc = new Scanner(System.in);
        String cad;
        do {
            cad = sc.nextLine();
            if (cad.matches(""))
                System.out.println("Entrada inválida, intentelo de nuevo.");
            else if (cad.matches("[0-9]*") || cad.matches("-[0-9]*"))
                break;
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
        } while (true);
    return Integer.parseInt(cad);
    }
    
    public static int pedirIntPositivo(){
        Scanner sc = new Scanner(System.in);
        String cad;
        do {
            cad = sc.nextLine();
            if (cad.matches(""))
                System.out.println("Entrada inválida, intentelo de nuevo.");
            else if (cad.matches("[0-9]*"))
                break;
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
        } while (true);
    return Integer.parseInt(cad);
    }
    public static int pedirIntEnIntervalo(int inicio,int fin){ //Pide un int en un intervalo definido (Bueno para los menús)
        int num;
        do{
            num = pedirIntPositivo();
            if (num >= inicio && num <= fin)
                break;
            else
               System.out.println("Entrada inválida, intentelo de nuevo."); 
        } while(true);
        return num;
    }
    public static int[] pedirIntPosIntervalo(int inicio, int fin){
        String[] cads = {"Ingrese primer valor de busqueda entre "+inicio+" y "+fin,
        "Ingrese segundo valor de busqueda entre " + inicio + " y " + fin};
        int[] intervalo = new int[2];
        for (int i = 0;i<2;i++){
            System.out.println(cads[i]);
            while (true){
            int num = pedirIntPositivo();
            if (i==0 && num >= inicio && num <= fin){
                intervalo[i] = num;
                break;}
            else if (i==1 && num >= inicio && num <= fin && num > intervalo[0]){
                intervalo[i] = num;
                break;}
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
            }
        }
        return intervalo;
    }   
    public static double pedirDouble(){
    Scanner sc = new Scanner(System.in);
    String cad;
        do {
            cad = sc.nextLine();
            if (cad.matches(""))
                System.out.println("Entrada inválida, intentelo de nuevo.");
            else if (cad.matches("[0-9]*.[0-9]*") || cad.matches("-[0-9]*.[0-9]*"))
                break;
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
        } while (true);
    return Double.parseDouble(cad);
    }
    public static double pedirDoublePositivo(){
    Scanner sc = new Scanner(System.in);
    String cad;
        do {
            cad = sc.nextLine();
            if (cad.matches(""))
                System.out.println("Entrada inválida, intentelo de nuevo.");
            else if (cad.matches("[0-9]*.[0-9]*"))
                break;
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
        } while (true);
    return Double.parseDouble(cad);
    }
    public static double[] pedirDoublePosIntervalo(double inicio, double fin){  //Revisar como funciona los intervalos con los indices
        String[] cads = {"Ingrese primer valor de busqueda entre "+inicio+" y "+fin,
        "Ingrese segundo valor de busqueda entre " + inicio + " y " + fin};
        double[] intervalo = new double[2];
        for (int i = 0;i<2;i++){
            System.out.println(cads[i]);
            while (true){
            double num = pedirDoublePositivo();
            if (i==0 && num >= inicio && num <= fin){
                intervalo[i] = num;
                break;}
            else if (i==1 && num >= inicio && num <= fin && num > intervalo[0]){
                intervalo[i] = num;
                break;}
            else
                System.out.println("Entrada inválida, intentelo de nuevo.");
            }
        }
        return intervalo;
    }
    public static boolean VerificarDoubleEnIntervalo(double inicio,double fin,double num){ //Pide un int en un intervalo definido (Bueno para los menús)
            if (num >= inicio && num <= fin)
                return true;
            else
               return false;
    }
    public static boolean VerificarIntEnIntervalo(int inicio,int fin,int num){ //Pide un int en un intervalo definido (Bueno para los menús)
            if (num >= inicio && num <= fin)
                return true;
            else
               return false;
    }
    public static String pedirCad(){
        Scanner sc = new Scanner(System.in);
        String cad = sc.nextLine();
        return cad;
    }  
    public static boolean validarSiNo(){
        while (true){
        Scanner sc = new Scanner(System.in);
        String cad = sc.nextLine();
        if (cad.matches("Sí")||cad.matches("Si")||cad.matches("sí")||cad.matches("si"))
            return true;
        if (cad.matches("No")||cad.matches("no"))
            return false;
        else
            System.out.println("Entrada inválida, indique sí o no.");
        }
    }
    public static boolean comprobarNoEnFile(String archivo,String palabra) {
        try(BufferedReader leer = new BufferedReader(new FileReader(archivo))){
            String linea;
            int prueba = 0;
            while ((linea = leer.readLine()) != null){
            String[] terminos = linea.split(";");
            for (String termino: terminos){
                if (termino.equals(palabra))
                    prueba++;
                }
            }
            if (prueba>0)
               return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static String[] lineaConPalabra(String archivo,String palabra){
       try(BufferedReader leer = new BufferedReader(new FileReader(archivo))){
            String linea;
            while ((linea = leer.readLine()) != null){
            String[] terminos = linea.split(";");
                for (String termino: terminos){
                    if (termino.equals(palabra)){
                        return terminos;
                        }
                    }
            }
   } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return null;
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    public static String toHexString(byte[] hash) { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }
    public static String calcularHash(String a) throws NoSuchAlgorithmException{
        String hash = toHexString(getSHA(a));
        return hash;
    }

    public static void enviarConGMail(String remitente, String clave, String destinatario, String asunto, String cuerpo) {

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", clave);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
        try (Transport transport = session.getTransport("smtp")) {
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Correo enviado exitosamente");
        }
    }
    catch (MessagingException me) {
        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo enviar el correo");
        a.show();
    }
    

}
}
