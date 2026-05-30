package es.etg.dam.videojuego.conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.etg.dam.videojuego.util.LogUtil;
import es.etg.dam.videojuego.util.UtilSeguridad;

public class Conexion {
        
   
    private static final Logger logger = Logger.getLogger("Logger");
    
    public void escribir(String msg, Socket socket) throws Exception {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


        dos.writeUTF(UtilSeguridad.prepararPaquete(msg));
    }

    public String leer(Socket socket) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String paqueteBruto = dis.readUTF();
        
        LogUtil.escribirLog(logger, Level.INFO, "DEBUG - Paquete recibido (cifrado + hash): " + paqueteBruto);
        //return UtilSeguridad.desempaquetar(dis.readUTF());
        return UtilSeguridad.desempaquetar(paqueteBruto);

        
    }
}
