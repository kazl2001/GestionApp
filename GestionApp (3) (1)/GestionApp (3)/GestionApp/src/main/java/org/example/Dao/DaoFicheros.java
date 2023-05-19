package org.example.Dao;

import org.example.Common.CategoriaException;
import org.example.Common.Constantes;
import org.example.Domain.Articulo;
import org.example.Domain.Usuario;
import org.example.Service.GestionArticulos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaoFicheros {
    public static final String FICHERO = "FicheroTexto";
    public static final String FICHEROB = "FicheroBinario";
    public static void crearFicheros() throws IOException {
        File fichero1 = new File(FICHERO);
        File fichero2 = new File(FICHEROB);
        if (!fichero1.exists())
            fichero1.createNewFile();
        if (!fichero2.exists())
            fichero2.createNewFile();
    }
    public static List<Articulo> leerFichero() throws IOException {
        return leerFichero(FICHERO);
    }
    public static List<Articulo> leerFichero(String fichero) throws IOException {
        crearFicheros();
        ArrayList<Articulo> auxiliar = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNextLine()) {
                String cadena = sc.nextLine();
                String[] trozos = cadena.split(";");
                //crear articulo y añadirlo a auxiliar.
                try{
                    auxiliar.add(new Articulo((trozos[0]),(trozos[1]),Double.parseDouble(trozos[2]),Boolean.parseBoolean(trozos[3])));
                    GestionArticulos.aniadirCategoria(trozos[2]);
                }catch (CategoriaException e){
                    GestionArticulos.aniadirCategoria(trozos[2]);
                    auxiliar.add(new Articulo((trozos[0]),(trozos[1]),Double.parseDouble(trozos[2]),Boolean.parseBoolean(trozos[3])));
                }
            }
        } catch (FileNotFoundException | CategoriaException e) {
            System.out.println(Constantes.FALLORFICHERO);
        }
        return auxiliar;
    }
    public static void escribirFichero(ArrayList <Articulo> e){
        try (FileWriter fw = new FileWriter(FICHERO)) {
            e.forEach((articulo) -> {
                try {
                    fw.write(articulo.toStringFichero() + "\n");
                } catch (IOException ex) {
                    System.out.println(Constantes.FALLOWFICHERO);
                }
            });
        } catch (IOException e2) {
            System.out.println(Constantes.FALLOWFICHERO);
        }
    }
    public static Usuario leerFicheroBinario(String rutaArchivo) {
        Usuario usuario = null;
        try {
            FileInputStream archivo = new FileInputStream(rutaArchivo);
            ObjectInputStream in = new ObjectInputStream(archivo);
            usuario = (Usuario) in.readObject();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Constantes.FALLORFICHEROB);
        }
        return usuario;
    }

    public static void escribirFicheroBinario(String rutaArchivo, Usuario usuario) {
        try {
            FileOutputStream archivo = new FileOutputStream(rutaArchivo);
            ObjectOutputStream out = new ObjectOutputStream(archivo);
            out.writeObject(usuario);
            out.close();
        } catch (IOException e) {
            System.out.println(Constantes.FALLOWFICHEROB);
        }
    }
}
