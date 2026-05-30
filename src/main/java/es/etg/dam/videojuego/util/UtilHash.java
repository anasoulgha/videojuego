package es.etg.dam.videojuego.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilHash {
    public static final String FORMATO_HASH = "SHA-256";
    private static final int BYTES_POR_HEX = 2;
    private static final int MASCARA_BYTE = 0xff;
    private static final int LONGITUD_HEX_SIMPLE = 1;
    private static final char RELLENO_HEX = '0';

    public static byte[] generarHash(byte[] contenido) throws NoSuchAlgorithmException {
        MessageDigest mg = MessageDigest.getInstance(FORMATO_HASH);
        return mg.digest(contenido);
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(BYTES_POR_HEX * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(MASCARA_BYTE & hash[i]);
            if (hex.length() == LONGITUD_HEX_SIMPLE) {
                hexString.append(RELLENO_HEX);
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}