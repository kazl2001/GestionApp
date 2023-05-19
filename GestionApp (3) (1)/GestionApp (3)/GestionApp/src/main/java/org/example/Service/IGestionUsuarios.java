package org.example.Service;

import org.example.Dao.Pedidos;
import org.example.Domain.Usuario;

import java.util.List;
import java.util.Map;

public interface IGestionUsuarios {
    //meter aqui y en el otro, esqueleto de ficheros.
    List<Pedidos> aniadirPedidosUsuario(String nickName);
    void getUsuarios(Map<Integer, Usuario> usuarioMap);
}
