package org.example.Service;

import org.example.Common.CategoriaException;
import org.example.Domain.Articulo;

import java.util.List;

public interface IGestionArticulos {
    //meter aqui y en el otro, esqueleto de ficheros.

    boolean listaVacia();
    //Admin
    boolean aniadirArticulo(String nombre, String categoria, double precio, boolean disponibilidad) throws CategoriaException;

    boolean aniadirArticulo(Articulo articulo) throws CategoriaException;

    boolean quitarArticulo(int id);

    boolean quitarArticulo(Articulo articulo);

    boolean modificarArticulo(int id);
    public boolean modificarArticulo(int id, String categoria) throws CategoriaException;

    boolean modificarPrecioArt(int id, double precio);

    boolean modificarCategoriaArt(int id, String categoria) throws CategoriaException;

    List<Articulo> comprobarStockArt(int id);

    List<Articulo> comprobarStockDisponibles(boolean disponibilidad);

    List<Articulo> getArticulos();

    List<Articulo> getArticulos(boolean ascendente);

    List<Articulo> getArticulosCategoria(String categoria);

    List<Articulo> getArticulosDisponiblidad(boolean disponibilidad);

    List<Articulo> getArticulosPrecio(double precio);
}
