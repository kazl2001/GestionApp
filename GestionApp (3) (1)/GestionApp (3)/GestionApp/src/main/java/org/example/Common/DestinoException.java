package org.example.Common;

import java.util.Arrays;

public class DestinoException extends Exception{
    public DestinoException() {
        super("El destino no es válido");
    }

    public DestinoException(String destino) {
        super("El destino " + destino + " no está permitido. Introduzca otro");
    }
}
