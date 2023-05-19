package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.DestinoException;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;
import org.example.Domain.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface DaoPedidos {

    boolean listaVacia();
    List<Articulo> getArticulos() throws IOException;
    List<Articulo> verCarrito() throws IOException;
    boolean aniadirArticulo(int id) throws IOException;

    List<Pedido> getPedidos(List<Pedido> aux2);
    Pedido aniadirPedido(String destino, String nickName, LocalDate localDate, List<Articulo> carrito, boolean finalizado) throws DestinoException;
    List<Pedido> aniadirPedidos(Pedido pedido) throws DestinoException;

    boolean quitarArticulo(int id);

    boolean quitarArticulo(Articulo articulo);
    boolean quitarTodosArticulos();
    boolean quitarTodosPedidos();
    void nuevoArticulo();
    void listarDestinos();

    void aniadirDestino(String destino);

    void verFechaPedido(int idPedido);

    void comprobarDestinoPedido(int idPedido);

    void mostrarPedidosPosteriores(LocalDate localdate);

    void listarPedidosEntregados();
    void obtenerPedidosUsuario();
}
