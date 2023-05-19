package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.CategoriaException;
import org.example.Domain.Articulo;
import org.example.Domain.Usuario;

import java.util.List;
import java.util.Map;

public interface DaoUsuarios {
    List<Pedidos> aniadirPedidosUsuario(String nickName);
    void getUsuarios(Map<Integer, Usuario> usuarioMap);

}
