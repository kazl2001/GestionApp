package org.example.Service;

import org.example.Dao.DaoUsuarios;
import org.example.Dao.Pedidos;
import org.example.Domain.Usuario;
import java.util.List;
import java.util.Map;

public class GestionUsuarios implements IGestionUsuarios{
    private DaoUsuarios daoUsuarios;
    @Override
    public List<Pedidos>aniadirPedidosUsuario(String nickName) {
        return daoUsuarios.aniadirPedidosUsuario(nickName);
    }

    @Override
    public void getUsuarios(Map<Integer, Usuario> usuarioMap) {
        daoUsuarios.getUsuarios(usuarioMap);
    }
}
