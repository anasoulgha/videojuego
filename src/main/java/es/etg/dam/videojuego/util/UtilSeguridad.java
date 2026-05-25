package es.etg.dam.videojuego.util;

import java.util.Base64;

import es.etg.dam.videojuego.exception.SeguridadException;

public class UtilSeguridad {
    
    public static String prepararPaquete(String msg) throws Exception {
        String cifrado = UtilSimetrico.cifrar(msg);
        byte[] hash = UtilHash.generarHash(msg.getBytes("UTF-8"));
        String hashStr = Base64.getEncoder().encodeToString(hash);
        
        return cifrado +":" + hashStr;
    }

    public static String desempaquetar(String paquete) throws Exception {
        String[] partes = paquete.split(":");
        String msgDescifrado = UtilSimetrico.descifrar(partes[0]);
        
        byte[] hashCalc = UtilHash.generarHash(msgDescifrado.getBytes("UTF-8"));
        String hashCalcStr = Base64.getEncoder().encodeToString(hashCalc);

        if (!hashCalcStr.equals(partes[1])) {
            throw new SeguridadException("Error inesperado en el hash: ");
        }
        return msgDescifrado;
    }
}