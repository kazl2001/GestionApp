package org.example.Service;

import org.example.Common.Categoria;
import org.example.Common.DestinoException;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IGestionPedidos {
    //meter aqui y en el otro, esqueleto de ficheros.
    List<Articulo> getArticulos() throws IOException;
    boolean aniadirArticulo(int id) throws IOException;

    List<Articulo> verCarrito() throws IOException;
    Pedido aniadirPedido(String destino, String nickName, LocalDate localDate, List<Articulo> carrito, boolean finalizado) throws DestinoException;
    List<Pedido> getPedidos(List<Pedido> aux2);
    List<Pedido> aniadirPedidos(Pedido pedido) throws DestinoException;
    void crearPackArt(Categoria categoria);

    boolean quitarArticulo(int id);

    boolean quitarArticulo(Articulo articulo);
    boolean quitarTodosArticulos();
    boolean quitarTodosPedidos();
    boolean listaVacia();

    void listarDestinos();

    void aniadirDestino();
    void nuevoArticulo();

    void verFechaPedido(int idPedido);

    void comprobarDestinoPedido(int idPedido);

    void mostrarPedidosPosteriores(LocalDate localdate);

    void listarPedidosEntregados();
}
