package es.etg.dam.videojuego.exception;

public class PartidaException extends RuntimeException {
 private static final String MSG = "Error al gestionar la partida: %s";

    public PartidaException(String detalle) {
        super(String.format(MSG, detalle));
    }

    public PartidaException(String detalle, Throwable causa) {
        super(String.format(MSG, detalle), causa);
    }
}
