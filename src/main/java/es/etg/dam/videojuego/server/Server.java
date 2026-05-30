package es.etg.dam.videojuego.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.etg.dam.videojuego.conexion.Conexion;
import es.etg.dam.videojuego.exception.ServidorException;
import es.etg.dam.videojuego.partida.Partida;
import es.etg.dam.videojuego.util.LogUtil;

public class Server {
  public static final int PUERTO = 8888;
  private static final String FICHERO_LOG = "Servidor.log";
  private static final String MSG_PUERTO_ESCUCHA = "Servidor escuchando en el puerto %d";
  private static final String MSG_ESPERA = "Esperando jugadores...";
  private static final String MSG_CONEXION = "Jugador conectado: %s";

  public static void main(String[] args) throws ServidorException {

    Logger logger = null;

    try (ServerSocket server = new ServerSocket(PUERTO)) {
      logger = LogUtil.crearLog(FICHERO_LOG);

      LogUtil.escribirLog(logger, Level.INFO, String.format(MSG_PUERTO_ESCUCHA, PUERTO));

      Conexion conn = new Conexion();

      while (true) {
        Partida juego = new Partida();

        LogUtil.escribirLog(logger, Level.INFO, MSG_ESPERA);

        for (int i = 0; i < Partida.MAX_JUGADORES; i++) {
          Socket socket = server.accept();
          String nombre = conn.leer(socket);

          LogUtil.escribirLog(logger, Level.INFO, String.format(MSG_CONEXION, nombre));
          juego.guardarJugador(nombre, socket);
        }

        Thread hilo = new Thread(juego);
        hilo.start();
      }

    } catch (Exception e) {
      LogUtil.escribirLog(logger, Level.SEVERE, e.getMessage(), e);
      throw new ServidorException(e.getMessage(), e);
    }
  }
}