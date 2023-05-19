package org.example.Domain;


import org.example.Dao.Pedidos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class Usuario implements Serializable{
    private static final Logger logger = Logger.getLogger(String.valueOf(Usuario.class));
    private String nickname;
    private List<Pedidos> pedidosList;
    public Usuario(){
        this.pedidosList = new ArrayList<Pedidos>();
        for (int i = 0; i < pedidosList.size(); i++) {
            System.out.println(pedidosList.get(i).toString());
        }
        logger.info("Se ha creado la lista del pedido correctamente");
        if (this.pedidosList.size()<0){
            logger.info("La lista de pedido está vacía");
            logger.warning("No se ha podido crear bien el pedido");
        }
    }
    public Usuario(String nickname){
        this.nickname = nickname;
        logger.info("Se ha creado un objeto Usuario con nombre: " + this.nickname);
    }
    public Usuario(String nickname, List<Pedidos> pedidosList) {
        this.nickname = nickname;
        this.pedidosList = pedidosList;
        logger.info("Se ha creado el usuario con "+nickname+" y lista de pedido correctamente");
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @Override
    public String toString() {
        return "Nombre = '" + nickname + '\'' +
                ", Factura = " + pedidosList;
    }
}
