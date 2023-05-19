package org.example.Dao;

import org.example.Domain.Usuario;
import java.util.List;
import java.util.Map;

public class DaoUsuariosImplementacion implements DaoUsuarios{

    private Pedidos listaPedidos;
    private int autonumerico = 0;
    private Usuario usuario = new Usuario();
    @Override
    public List<Pedidos> aniadirPedidosUsuario(String nickName) {
        for (int i = 0; i < listaPedidos.getListaPedidos().size(); i++) {
            if (usuario.getNickname().equalsIgnoreCase(nickName)){
                usuario.getPedidosList().get(i).getListaPedidos().add(listaPedidos.getListaPedidos().get(i));
            }
        }if (usuario.getPedidosList().size() > 0)
            return usuario.getPedidosList();

        return null;
    }

    @Override
    public void getUsuarios(Map<Integer, Usuario> usuarioMap) {
        usuarioMap.computeIfAbsent(autonumerico++, k -> usuario);
    }

}
