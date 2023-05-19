package org.example.Common;

import org.example.Domain.Destino;

import java.util.Map;

// Paquete Common. Se encarga de proporcionar servicios compartidos y de uso general a las otras capas. Se incluyen sevicios de autenticacion, manejo de excepciones...
public class Comprobacion {
    public static void categoriaOk(String categoria) throws CategoriaException {
        boolean existe = false;
        Categoria aux [] = Categoria.values();
        for(int i=0; i<aux.length && !existe;i++){
            if (aux[i].toString().equalsIgnoreCase(categoria)){
                //System.out.println(aux[i].toString()+" - ");
                existe = true;
            }
        }
        /*for(Categoria c: Categoria.values())
            System.out.println(c+"-");*/
        if (!existe)
            throw new CategoriaException(categoria);

    }

    public static void destinoOk2(String destino) throws DestinoException {
        boolean existe=false;
        Map<Integer, String> destinos = new Destino().getDestinos();
        if (destinos.containsValue(destino)) {
            existe=true;
        } else {
            if (!existe){
                throw new DestinoException(destino);}
        }
    }
}
