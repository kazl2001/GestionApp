package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.CategoriaException;
import org.example.Common.Constantes;
import org.example.Domain.Articulo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DaoArticulosImplementacion implements DaoArticulos{

    private static Articulos lista;

    public DaoArticulosImplementacion() throws IOException {
        this.lista = new Articulos();
    }

    public DaoArticulosImplementacion(Articulos lista) {
        this.lista=lista;
    }


    @Override
    public boolean listaVacia() {
        if (lista.getListaArticulos().size()==0){return true;}else{
        return false;}
    }

    //Admin
    @Override
    public boolean aniadirArticulo(String nombre, String categoria, double precio, boolean disponibilidad) {
        try {
            lista.getListaArticulos().add(new Articulo(nombre, categoria, precio, disponibilidad));
            return true;
        } catch (CategoriaException e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean aniadirArticulo(Articulo articulo) {
            if (lista.getListaArticulos().add(articulo)){
            return true;}else{return false;}
    }

    @Override
    public boolean quitarArticulo(int id) {
        return lista.getListaArticulos().removeIf(a -> a.getId() == id);
    }

    @Override
    public boolean quitarArticulo(Articulo articulo) {
        return lista.getListaArticulos().removeIf(a -> a.equals(articulo));
    }

    public boolean modificarArticulo(int id, String categoria) throws CategoriaException {
        Articulo articulo = lista.getListaArticulos().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        if (articulo != null) articulo.setCategoria(categoria);
        return articulo != null;
    }

    @Override
    public boolean modificarArticulo(int id) throws CategoriaException {
        Scanner lector = new Scanner(System.in);
        String nombre = lector.nextLine();
        String categoria = lector.nextLine();
        double precio = lector.nextDouble();
        boolean disponibilidad;
        boolean salir = false;
        for (int i = 0; i < lista.getListaArticulos().size(); i++) {
            if (lista.getListaArticulos().get(i).getId() == id) {
                lista.getListaArticulos().get(i).setNombre(nombre);
                lista.getListaArticulos().get(i).setCategoria(categoria);
                lista.getListaArticulos().get(i).setPrecio(precio);
                int disponible;
                do {
                    disponible = lector.nextInt();
                    if (disponible == 1) {
                        disponibilidad = true;
                        lista.getListaArticulos().get(i).setDisponibilidad(disponibilidad);
                        salir = true;
                    } else if (disponible == 2) {
                        disponibilidad = false;
                        lista.getListaArticulos().get(i).setDisponibilidad(disponibilidad);
                        salir = true;
                    }
                } while (!salir);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modificarPrecioArt(int id, double precio) {
        return lista.getListaArticulos().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .map(a -> {
                    a.setPrecio(precio);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean modificarCategoriaArt(int id, String categoria) {
        return lista.getListaArticulos().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .map(a -> {
                    try {
                        a.setCategoria(categoria);
                        return true;
                    } catch (CategoriaException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(false);
    }

    @Override
    public List<Articulo> comprobarStockArt(int id) {
        return lista.getListaArticulos().stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Articulo> comprobarStockDisponibles(boolean disponibilidad) {
        return lista.getListaArticulos().stream()
                .filter(a -> a.isDisponibilidad() == disponibilidad)
                .collect(Collectors.toList());
    }

    public List<Articulo> getArticulos() {
        return lista.getListaArticulos();
    }
    @Override
    public List<Articulo> getArticulos(boolean ascendente) {
        List<Articulo> lista2 = new ArrayList<>(lista.getListaArticulos());
        lista2.sort(ascendente ? Comparator.naturalOrder() : Comparator.reverseOrder());
        return lista2;
    }

    @Override
    public List<Articulo> getArticulosCategoria(String categoria) {
        return lista.getListaArticulos().stream()
                .filter(a -> Categoria.valueOf(categoria).equals(a.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Articulo> getArticulosDisponiblidad(boolean disponibilidad) {
        return lista.getListaArticulos().stream()
                .filter(a -> a.isDisponibilidad() == disponibilidad)
                .collect(Collectors.toList());
    }

    @Override
    public List<Articulo> getArticulosPrecio(double precio) {
        return lista.getListaArticulos().stream()
                .filter(a -> Double.compare(a.getPrecio(), precio) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "DaoArticulosImplementacion{" +
                "lista =" + lista +
                '}';
    }
}
