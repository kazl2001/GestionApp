package org.example.Dao;

import org.example.Domain.Pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedidos implements Serializable {
    private final List<Pedido> pedidos;
    public Pedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public Pedidos(){
        this.pedidos = new ArrayList<Pedido>();
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println(pedidos.get(i).toString());
        }
    }

    public List<Pedido> getListaPedidos() {
        return pedidos;
    }
    public void setListaArticulos(){
        this.pedidos.clear();
        this.pedidos.addAll(pedidos);
    }

    @Override
    public String toString() {
        return "Lista de pedidos: " +
                pedidos +
                ']';
    }
}
