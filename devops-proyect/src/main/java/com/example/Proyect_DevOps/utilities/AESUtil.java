package com.example.Proyect_DevOps.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1234567890123456"; // 16 chars = 128 bits

    public static String encriptar(String dato) {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(dato.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    public static String desencriptar(String datoEncriptado) {
        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(datoEncriptado);

            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }
}