package org.example.Domain;

import java.util.HashMap;
import java.util.Map;

public class Destino {
    private Map<Integer, String> destinos = new HashMap<Integer, String>();

    public Destino(Map<Integer, String> destinos) {
        this.destinos = destinos;
    }
    public Destino(){
        this.destinos = new HashMap<Integer,String>();
        for (int i = 0; i < destinos.size(); i++) {
            //elementos.add(new Elemento(autonumerico++, 2, faker.pokemon().name(), Categoria.pokemon.name()));
            System.out.println(destinos.get(i).toString());
        }
        destinos.put(28, "Madrid");
        destinos.put(80, "Barcelona");
        destinos.put(46, "Valencia");
        destinos.put(41, "Sevilla");
        destinos.put(50, "Zaragoza");
    }

    public Map<Integer, String> getDestinos() {
        return destinos;
    }

    public void setDestinos(Map<Integer, String> destinos) {
        this.destinos = destinos;
    }

    @Override
    public String toString() {
        return "Destinos{" +
                "destinos=" + destinos +
                '}';
    }
}
