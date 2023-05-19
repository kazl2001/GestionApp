package org.example.Domain;

import org.example.Common.CategoriaException;
import org.example.Common.Comprobacion;

import java.io.Serializable;
import java.time.Instant;

public class Articulo implements Serializable,Comparable<Articulo>{

    private static int autonumerico;
    private int id;
    private String nombre;

    private double precio;

    private String categoria;

    private double valoracion;
    private boolean disponibilidad;


    public Articulo(){
    }

    public Articulo(String nombre, String categoria, double precio, boolean disponibilidad) throws CategoriaException {
        autonumerico++;
        this.id= autonumerico;
        this.nombre=nombre;
        Comprobacion.categoriaOk(categoria);
        this.precio=precio;
        this.valoracion=(double)(Math.random()*5+1);
        this.disponibilidad=disponibilidad;
        this.categoria = categoria;
    }

    public Articulo(String nombre,String categoria,double precio) throws CategoriaException {
        autonumerico++;
        this.id= autonumerico;
        this.nombre=nombre;
        Comprobacion.categoriaOk(categoria);
        this.precio=precio;
        this.valoracion=(double)(Math.random()*5+1);
        this.disponibilidad=true;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws CategoriaException {
        Comprobacion.categoriaOk(categoria);
        this.categoria = categoria;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getAutonumerico() {
        return autonumerico;
    }

    public void setAutonumerico(int autonumerico) {this.id=autonumerico;}

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nombre: " + nombre +
                ", Precio: " + precio +
                ", Categoria: " + categoria +
                ", Disponibilidad: " + disponibilidad +
                '.';
    }

    public int toStringFichero() {
        return 1;
    }

    @Override
    public int compareTo(Articulo o) {
        return Double.compare(this.precio, o.precio);
    }

}
