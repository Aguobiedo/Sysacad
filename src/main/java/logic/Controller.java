package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Controller {
	
	
	public static String calcularSHA256(String texto) {
        try {
            // Crear una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Obtener los bytes del texto
            byte[] bytes = texto.getBytes();

            // Calcular el hash
            byte[] hashBytes = md.digest(bytes);

            // Convertir el hash a una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de excepciones en caso de que no se encuentre el algoritmo
            e.printStackTrace();
            return null; // O puedes lanzar una excepción personalizada aquí
        }
    }

}
