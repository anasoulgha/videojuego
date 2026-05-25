package es.etg.dam.videojuego.partida;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.videojuego.conexion.Conexion;
import es.etg.dam.videojuego.exception.PartidaException;

public class Partida implements Runnable {

    public static final int MAX_JUGADORES = 2;
    private static final String MSG_GANADOR = "Ganador :";

    private final List<Jugador> jugadores = new ArrayList<>();

    public void guardarJugador(String nombre, Socket socket) {
        jugadores.add(new Jugador(nombre, socket));
    }

    @Override
    public void run() {

        try {

            Conexion conn = new Conexion();

            Jugador ganador = null;
            int jugadorMaxPun = 0;

            for (Jugador jugador : jugadores) {
                jugador.setPuntos(NumeroAleatorio.generar());
            }

            for (int i = 0; i < MAX_JUGADORES; i++) {

                Jugador jugadorActual = jugadores.get(i);

                if (jugadorActual.getPuntos() > jugadorMaxPun) {

                    jugadorMaxPun = jugadorActual.getPuntos();
                    ganador = jugadorActual;
                }
            }

            for (Jugador jugador : jugadores) {

                conn.escribir(MSG_GANADOR + ganador.getNombre(), jugador.getSocket());
            }

        } catch (Exception e) {

            throw new PartidaException(e.getMessage(), e);
        }
    }
}