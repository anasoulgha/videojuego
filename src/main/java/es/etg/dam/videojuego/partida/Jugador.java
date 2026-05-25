package es.etg.dam.videojuego.partida;

import java.net.Socket;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Jugador {
    private String nombre;
    private Socket socket;
    private int puntos;

    public Jugador(String nombre, Socket socket) {
        this.nombre = nombre;
        this.socket = socket;

    }

}
