package es.etg.dam.videojuego.partida;

import java.util.Random;

public class NumeroAleatorio {
    private static final int MINIMO = 1;
    private static final int MAXIMO = 100;

    public static int generar(){
        return new Random().nextInt(MAXIMO - MINIMO + 1) + MINIMO;
    }
}
