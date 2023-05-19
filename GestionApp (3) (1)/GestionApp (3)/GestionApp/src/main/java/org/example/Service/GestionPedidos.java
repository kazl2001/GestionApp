package org.example.Service;

import org.example.Common.Categoria;
import org.example.Common.DestinoException;
import org.example.Dao.DaoPedidos;
import org.example.Dao.DaoPedidosImplementacion;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class GestionPedidos implements IGestionPedidos{
    private final DaoPedidos daoPedidos;

    public GestionPedidos(DaoPedidos daoPedidos) {
        this.daoPedidos = daoPedidos;
    }
    public GestionPedidos() throws IOException {
        this.daoPedidos = new DaoPedidosImplementacion();
    }

    @Override
    public List<Articulo> getArticulos() throws IOException {
        return daoPedidos.getArticulos();
    }

    @Override
    public boolean aniadirArticulo(int id) throws IOException {
        return daoPedidos.aniadirArticulo(id);
    }

    @Override
    public List<Articulo> verCarrito() throws IOException {
        return daoPedidos.verCarrito();
    }

    @Override
    public Pedido aniadirPedido(String destino, String nickName, LocalDate localDate, List<Articulo> carrito, boolean finalizado) throws DestinoException {
        return daoPedidos.aniadirPedido(destino,nickName,localDate,carrito,true);
    }

    @Override
    public List<Pedido> getPedidos(List<Pedido> aux2) {
        return daoPedidos.getPedidos(aux2);
    }

    @Override
    public List<Pedido> aniadirPedidos(Pedido pedido) throws DestinoException {
        return daoPedidos.aniadirPedidos(pedido);
    }


    @Override
    public boolean quitarArticulo(int id) {
        return daoPedidos.quitarArticulo(id);
    }

    @Override
    public boolean quitarArticulo(Articulo articulo) {
        return false;
    }

    @Override
    public boolean quitarTodosArticulos() {
        return daoPedidos.quitarTodosArticulos();
    }

    @Override
    public boolean quitarTodosPedidos() {
        return daoPedidos.quitarTodosPedidos();
    }

    @Override
    public boolean listaVacia() {
        return daoPedidos.listaVacia();
    }
    @Override
    public void crearPackArt(Categoria categoria) {

    }


    @Override
    public void listarDestinos() {

    }

    @Override
    public void aniadirDestino() {

    }

    @Override
    public void nuevoArticulo() {
        daoPedidos.nuevoArticulo();
    }

    @Override
    public void verFechaPedido(int idPedido) {

    }

    @Override
    public void comprobarDestinoPedido(int idPedido) {

    }

    @Override
    public void mostrarPedidosPosteriores(LocalDate localdate) {

    }

    @Override
    public void listarPedidosEntregados() {

    }
}
