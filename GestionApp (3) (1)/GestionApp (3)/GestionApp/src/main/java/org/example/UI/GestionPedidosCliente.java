package org.example.UI;

import org.example.Common.CategoriaException;
import org.example.Common.Constantes;
import org.example.Common.DestinoException;
import org.example.Dao.Articulos;
import org.example.Dao.Pedidos;
import org.example.Dao.Usuarios;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;
import org.example.Domain.Usuario;
import org.example.Service.GestionPedidos;
import org.example.Service.GestionUsuarios;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.example.UI.GestionApp.panel;


public class GestionPedidosCliente {
    Articulos articulos = new Articulos();
    static List<Articulo> articulosList = new ArrayList<Articulo>();
    static List<Pedido> pedidosList = new ArrayList<Pedido>();
    static List<Pedidos> pedidosList2 = new ArrayList<>();
    static Map<Integer, Usuario> listMap = new HashMap<>();

    public GestionPedidosCliente() throws IOException {
    }

    public void menuCliente() throws IOException {
        int opcion = 0;
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.MENU);
        do {
            try {
                System.out.println("1. " + Constantes.ADMIN2A + "\n2. " + Constantes.OPCIONCA + "\n3. " + Constantes.OPCIONCB + "\n4. " + Constantes.OPCION4);
                opcion = lector.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println(articulos.getListaArticulos());
                        break;
                    case 2:
                        System.out.println(Constantes.OPCIONCA2);
                        int id = lector.nextInt();
                        for (int i = 0; i < articulos.getListaArticulos().size(); i++) {
                            Articulo articulo = new Articulo(
                                    articulos.getListaArticulos().get(i).getNombre(), articulos.getListaArticulos().get(i).getCategoria(), articulos.getListaArticulos().get(i).getPrecio(), articulos.getListaArticulos().get(i).isDisponibilidad());
                            if (articulos.getListaArticulos().get(i).getId() == id)
                                articulosList.add(articulo);
                        }
                        break;
                    case 3:
                        verCarrito();
                        break;
                    case 4:
                        System.out.println(Constantes.SALIR);
                        panel();
                        break;
                    default:
                        System.out.println(Constantes.OPCION0);
                }
            }catch (InputMismatchException e){
                lector.next();
                System.out.println(Constantes.OPCION0);
            }catch (RuntimeException e){
                System.out.println(Constantes.OPCION0);
            } catch (CategoriaException e) {
                throw new RuntimeException(e);
            }
        }while(opcion !=4);
    }
    public void verCarrito() throws IOException { //Cargar Articulos.
        int opcion = 0;
        Scanner lector = new Scanner(System.in);
        GestionPedidos gestionPedidos = new GestionPedidos();
        System.out.println(Constantes.MENU);
        do {
            try{
                System.out.println("1. " + Constantes.OPCIONC1 + "\n2. " + Constantes.OPCIONC2 + "\n3. " + Constantes.OPCIONC2A + "\n4. " + Constantes.OPCIONC3 + "\n5. " + Constantes.OPCION4);
                opcion = lector.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println(articulosList);
                        break;
                    case 2:
                        System.out.println(Constantes.OPCIONC1A);
                        int id = lector.nextInt();
                        lector.next();
                        gestionPedidos.quitarArticulo(id);
                        break;
                    case 3:
                        gestionPedidos.quitarTodosArticulos();
                        break;
                    case 4:
                        confirmarPedido();
                        break;
                    case 5:
                        System.out.println(Constantes.OPCION4);
                        break;
                    default:
                        System.out.println(Constantes.OPCION0);
                }
            }catch (InputMismatchException e){
                lector.next();
                System.out.println(Constantes.OPCION0);
            }catch (RuntimeException e){
                System.out.println(Constantes.OPCION0);
            }
        }while (opcion != 5);
    }
    public void confirmarPedido() throws IOException {
        int opcionPedido;
        Scanner lector = new Scanner(System.in);
        GestionPedidos gestionPedidos = new GestionPedidos();
        GestionPedidosCliente gestionPedidosCliente = new GestionPedidosCliente();
        System.out.println(articulosList);
        System.out.println(Constantes.CONFIRMARPEDIDO1);
        do {
            opcionPedido = lector.nextInt();
            if (opcionPedido == 1){
                System.out.println(Constantes.CONFIRMARPEDIDODESTI);
                lector.nextLine();
                String destino = lector.nextLine().toUpperCase();
                System.out.println(Constantes.CONFIRMARPEDIDONOMBRE);
                String nombre = lector.nextLine().toUpperCase();
                LocalDate localDate = LocalDate.now();
                try {
                    if (gestionPedidos.aniadirPedido(destino, nombre, localDate, gestionPedidos.verCarrito(), true) == null){
                        System.out.println(Constantes.CONFIRMARPEDIDOERROR);
                        opcionPedido = 2;
                    }else{
                        List<Articulo> articulos1 = new ArrayList<Articulo>();
                        for (int i = 0; i < articulosList.size(); i++) {
                            articulos1.add(articulosList.get(i));
                        }
                        articulosList.clear();
                        Pedido pedido = new Pedido(destino, nombre, localDate, articulos1, true);
                        gestionPedidosCliente.pago(pedido);
                    }
                }catch (DestinoException e) {
                    throw new RuntimeException(e);
                }
            }else if (opcionPedido == 2){
                System.out.println(Constantes.SALIR);
            }
            else
                System.out.println(Constantes.OPCION0);
        }while (opcionPedido != 2);
    }
    public void pago(Pedido pedido) throws DestinoException{
        boolean salir = false;
        int opcionPago = 0;
        int autonumerico = 1;
        this.pedidosList.add(pedido);
        Scanner lector = new Scanner(System.in);
        List<Pedido> pedidos1 = new ArrayList<Pedido>();
        for (int i = 0; i < pedidosList.size(); i++) {
            pedidos1.add(pedidosList.get(i));
        }
        pedidosList.clear();
        Pedidos pedidos = new Pedidos(pedidos1);
        pedidosList2.add(pedidos);
        List<Pedidos> pedidos2 = new ArrayList<Pedidos>();
        for (int i = 0; i < pedidosList2.size(); i++) {
            pedidos2.add(pedidosList2.get(i));
        }
        pedidosList2.clear();
        Usuario usuario = new Usuario(pedido.getNickName(),pedidos2);
        System.out.println(usuario);
        autonumerico = listMap.size() + 1;
        Usuarios usuarios = new Usuarios(listMap);
        listMap.put(autonumerico,usuario);
        System.out.println(usuarios);
        System.out.println(Constantes.MENU);
        do {
            System.out.println(Constantes.PAGO1);
            opcionPago = lector.nextInt();
            if (opcionPago == 1){
                System.out.println(Constantes.PAGOEXITO);
                salir = true;
            }else if (opcionPago == 2){
                System.out.println(Constantes.SALIR);
                salir = true;
            }else
                System.out.println(Constantes.OPCION0);

        }while (salir != true);
    }
}

