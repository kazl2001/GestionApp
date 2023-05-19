package org.example.Dao;

import org.example.Domain.Usuario;
import java.util.HashMap;
import java.util.Map;

public class Usuarios {
    private Map<Integer, Usuario> usuarioMap = new HashMap<>();
    public Usuarios(){
        this.usuarioMap = new HashMap<Integer, Usuario>();
        for (int i = 0; i < usuarioMap.size(); i++) {
            System.out.println(usuarioMap.get(i).toString());
        }
    }

    public Usuarios(Map<Integer, Usuario> usuarioMap) {
        this.usuarioMap = usuarioMap;
    }

    public Map<Integer, Usuario> getUsuarioMap() {
        return usuarioMap;
    }

    public void setUsuarioMap(Map<Integer, Usuario> usuarioMap) {
        this.usuarioMap = usuarioMap;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "Clave:" + usuarioMap +
                '}';
    }
}
