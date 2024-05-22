package com.example.demobd3fx.utils;

import java.io.IOException;

public class IOUtils {

    /**
     * Méthode qui existe uniquement pour éviter d'avoir
     * 72 try/catch block dans le code de Database
     * @param closeable
     */
    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                // do nothing...
            }
        }
    }
}
