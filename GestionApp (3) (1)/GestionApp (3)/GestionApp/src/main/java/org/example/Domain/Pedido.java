package org.example.Domain;

import org.example.Common.Comprobacion;
import org.example.Common.DestinoException;
import org.example.Dao.Articulos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Pedido implements Serializable {
    private static int autonumericoPedido;
    private int idPedido;
    private String destino;
    private String nickName;
    private LocalDate localDate = LocalDate.now();
    private List<Articulo> carrito;
    private boolean finalizado;

    public Pedido(){
    }

    public Pedido(String destino, String nickName, LocalDate localDate, List<Articulo> carrito, boolean finalizado) throws DestinoException {
        autonumericoPedido++;
        this.idPedido = autonumericoPedido;
        Comprobacion.destinoOk2(destino);
        this.nickName = nickName;
        this.localDate = localDate;
        this.carrito = carrito;
        this.finalizado = finalizado;
    }


}