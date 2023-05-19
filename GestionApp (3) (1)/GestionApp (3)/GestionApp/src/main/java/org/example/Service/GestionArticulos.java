package org.example.Service;

import org.example.Common.CategoriaException;
import org.example.Dao.DaoArticulos;
import org.example.Dao.DaoArticulosImplementacion;
import org.example.Domain.Articulo;

import java.io.IOException;
import java.util.List;

public class GestionArticulos implements IGestionArticulos{
    private final DaoArticulos daoArticulos;

    public static void aniadirCategoria(String categoria) {
    }

    public DaoArticulos getDaoArticulos() {
        return daoArticulos;
    }

    public GestionArticulos(DaoArticulos daoArticulos) {
        this.daoArticulos = daoArticulos;
    }
    public GestionArticulos() throws IOException {
        this.daoArticulos = new DaoArticulosImplementacion();
    }
    @Override
    public boolean listaVacia() {
        if (daoArticulos.getArticulos().size()==0){return true;}else{
            return false;}
    }

    @Override
    public boolean aniadirArticulo(String nombre, String categoria, double precio, boolean disponibilidad) throws CategoriaException {
        return daoArticulos.aniadirArticulo(nombre, categoria, precio, disponibilidad);
    }
    @Override
    public boolean aniadirArticulo(Articulo articulo) throws CategoriaException {
        return daoArticulos.aniadirArticulo(articulo);
    }

    @Override
    public boolean quitarArticulo(int id) {
        return daoArticulos.quitarArticulo(id);
    }

    @Override
    public boolean quitarArticulo(Articulo articulo) {
        return daoArticulos.quitarArticulo(articulo);
    }

    @Override
    public boolean modificarArticulo(int id) {
        return false;
    }

    @Override
    public boolean modificarArticulo(int id, String categoria) throws CategoriaException {
        return daoArticulos.modificarArticulo(id,categoria);
    }

    @Override
    public boolean modificarPrecioArt(int id, double precio) {
        return daoArticulos.modificarPrecioArt(id, precio);
    }

    @Override
    public boolean modificarCategoriaArt(int id, String categoria) throws CategoriaException {
        return daoArticulos.modificarCategoriaArt(id, categoria);
    }

    @Override
    public List<Articulo> comprobarStockArt(int id) {
        return daoArticulos.comprobarStockArt(id);
    }

    @Override
    public List<Articulo> comprobarStockDisponibles(boolean disponibilidad) {
        return daoArticulos.comprobarStockDisponibles(disponibilidad);
    }

    public List<Articulo> getArticulos(){
        return daoArticulos.getArticulos();
    }
    @Override
    public List<Articulo> getArticulos(boolean ascendente) {
        return daoArticulos.getArticulos(ascendente);
    }

    @Override
    public List<Articulo> getArticulosCategoria(String categoria) {
        return daoArticulos.getArticulosCategoria(categoria);
    }

    @Override
    public List<Articulo> getArticulosDisponiblidad(boolean disponibilidad) {
        return daoArticulos.getArticulosDisponiblidad(disponibilidad);
    }

    @Override
    public List<Articulo> getArticulosPrecio(double precio) {
        return daoArticulos.getArticulosPrecio(precio);
    }

}
