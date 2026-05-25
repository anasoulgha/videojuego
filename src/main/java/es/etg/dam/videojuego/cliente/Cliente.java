package es.etg.dam.videojuego.cliente;


import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.etg.dam.videojuego.conexion.Conexion;
import es.etg.dam.videojuego.exception.ClienteException;
import es.etg.dam.videojuego.util.LogUtil;

public class Cliente {

     public static final int PUERTO = 8888;
     static final String HOST = "localhost";
     private static final String FICHERO_LOG = "Cliente.log";

     public static void main(String[] args) throws ClienteException {
          Logger logger = null;
          try (Socket socket = new Socket(HOST, PUERTO)) {
               logger = LogUtil.crearLog(FICHERO_LOG);

               Conexion conn = new Conexion();

               String nombre = args[0];
               conn.escribir(nombre, socket);

               String resultado = conn.leer(socket);
               System.out.println(resultado);

          } catch (Exception e) {
               LogUtil.escribirLog(logger, Level.SEVERE, e.getMessage(), e);
               throw new ClienteException(e.getMessage(), e);
          }
     }

}
