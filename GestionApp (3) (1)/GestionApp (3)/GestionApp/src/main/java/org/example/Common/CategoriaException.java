package org.example.Common;

import java.util.Arrays;

// Paquete Common. Se encarga de proporcionar servicios compartidos y de uso general a las otras capas. Se incluyen sevicios de autenticacion, manejo de excepciones...
public class CategoriaException extends Exception{
    public CategoriaException() {
        super("La categoria debe ser alguna de las siguientes"+ Arrays.toString(Categoria.values()));
    }

    public CategoriaException(String categoria) {
        super("La categoria "+ categoria+" no está permitida. Sólo son válidas:"+ Arrays.toString(Categoria.values()));
    }
}
