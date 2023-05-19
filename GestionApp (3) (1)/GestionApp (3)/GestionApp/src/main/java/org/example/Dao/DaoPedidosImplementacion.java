package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.DestinoException;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;
import org.example.Domain.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DaoPedidosImplementacion implements DaoPedidos{
    private Pedido pedido;
    private Pedidos pedidos;
    private Articulos articulos = new Articulos();
    private Usuarios usuarios;
    private static List<Articulo> aux = new ArrayList<Articulo>();
    private static List<Pedido> aux2 = new ArrayList<Pedido>();
    private static List<Usuario> aux3 = new ArrayList<Usuario>();
    public DaoPedidosImplementacion() throws IOException {
        pedidos = new Pedidos();
        pedido = new Pedido();
    }
    public DaoPedidosImplementacion(Pedidos pedidos, Pedido pedido) throws IOException {
        this.pedidos = pedidos;
        this.pedido = pedido;

    }
    @Override
    public boolean listaVacia() {
        return pedido.getCarrito().size() == 0;
    }

    @Override
    public List<Articulo> getArticulos() throws IOException {
        return articulos.getListaArticulos().stream()
                .filter(Articulo::isDisponibilidad)
                .collect(Collectors.toList());
    }

    @Override
    public boolean aniadirArticulo(int id) throws IOException {
        Optional<Articulo> articuloOptional = articulos.getListaArticulos().stream()
                .filter(a -> a.getId() == id && a.isDisponibilidad())
                .findFirst();
        articuloOptional.ifPresent(aux::add);
        return articuloOptional.isPresent();
    }

    @Override
    public List<Articulo> verCarrito(){
        return aux.size() == 0 ? null : aux;
    }

    @Override
    public Pedido aniadirPedido(String destino, String nickName, LocalDate localDate, List<Articulo> carrito, boolean finalizado) throws DestinoException {
        if (carrito.isEmpty()) {
            return null;
        } else {
            pedido = new Pedido(destino, nickName, localDate, new ArrayList<>(aux), true);
            aux.clear();
            return pedido;
        }
    }

    @Override
    public List<Pedido> aniadirPedidos(Pedido pedido) throws DestinoException {
        aux2.add(pedido);
        return aux2;
    }

    @Override
    public List<Pedido> getPedidos(List<Pedido> aux2) {
        return aux2;
    }

    @Override
    public boolean quitarArticulo(int id) {
        return aux.removeIf(a -> a.getId() == id);
    }

    @Override
    public boolean quitarArticulo(Articulo articulo) {
        return aux.remove(articulo);
    }


    @Override
    public boolean quitarTodosArticulos() {
        aux.clear();
        return true;
    }

    @Override
    public boolean quitarTodosPedidos() {
        aux2.clear();
        return true;
    }

    @Override
    public void nuevoArticulo() {
        aux.clear();
        articulos = new Articulos(aux);
    }

    @Override
    public void listarDestinos() {
        // No implementado
    }

    @Override
    public void aniadirDestino(String destino) {
        // No implementado
    }

    @Override
    public void verFechaPedido(int idPedido) {
        pedido.getCarrito().stream()
                .filter(a -> a.getId() == idPedido)
                .findFirst()
                .ifPresent(a -> System.out.println(pedido.getLocalDate()));
    }

    @Override
    public void comprobarDestinoPedido(int idPedido) {
        pedido.getCarrito().stream()
                .filter(a -> a.getId() == idPedido)
                .findFirst()
                .ifPresent(a -> System.out.println(pedido.getDestino()));
    }

    @Override
    public void mostrarPedidosPosteriores(LocalDate localdate) {
        pedidos.getListaPedidos().stream()
                .filter(pedido -> pedido.getLocalDate().isBefore(localdate))
                .forEach(pedido -> System.out.print(pedido));
    }

    @Override
    public void listarPedidosEntregados() {
        // No implementado
    }


    @Override
    public void obtenerPedidosUsuario(){
        //this.pedidos.getPedidos()
        //Ver censo estadística (gitHub)
    }


    @Override
    public String toString() {
        return "DaoPedidosImplementacion{" +
                "pedidos=" + pedidos +
                ", pedido=" + pedido +
                ", articulos=" + articulos +
                '}';
    }
}
