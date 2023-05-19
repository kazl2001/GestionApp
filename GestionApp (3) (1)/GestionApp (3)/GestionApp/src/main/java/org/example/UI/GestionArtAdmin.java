package org.example.UI;

import org.example.Common.CategoriaException;
import org.example.Common.Comprobacion;
import org.example.Common.Constantes;
import org.example.Dao.DaoPedidosImplementacion;
import org.example.Service.GestionArticulos;
import org.example.Service.GestionPedidos;
import org.example.Service.GestionUsuarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Properties;

//parte de mostrar al usuario y la parte del admin
public class GestionArtAdmin {
    private static String pass = "3330";

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public static void identificacion() throws CategoriaException {
        int intentos = 3;
        String contra;
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.ADMIN1);
        do{
            contra = lector.nextLine();
            if (contra.equals(pass)){
                menuAdmin();
                intentos = 0;
            }else{
                intentos--;
                if (intentos == 0){
                    System.out.println(Constantes.ADMIN1ERROR);
                }else
                    System.out.println(Constantes.ADMIN2ERROR + (intentos) + Constantes.ADMIN3ERROR);
            }
        }while(intentos != 0);
    }
    public static void menuAdmin() {
        int opcion;
        boolean salir = false;
        Scanner lector = new Scanner(System.in);
        GestionArticulos gestionArticulos = null;
        GestionPedidos gestionPedidos = null;
        GestionUsuarios gestionUsuarios = null;
        try {
            gestionArticulos = new GestionArticulos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            gestionPedidos = new GestionPedidos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Constantes.ADMIN2);
        do {
            try {
                System.out.println("1. " + Constantes.ADMIN2A + "\n2. " + Constantes.ADMIN2B + "\n3. " + Constantes.ADMIN2C + "\n4. " + Constantes.ADMIN2D + "\n5. " + Constantes.ADMIN2E + "\n6. " + Constantes.ADMIN2F + "\n7. " + Constantes.ADMIN2G + "\n8. " + Constantes.ADMIN2H + "\n9. " + Constantes.ADMIN2I + "\n10. " + Constantes.ADMIN2J + "\n11. " + Constantes.ADMIN3J + "\n12. " + Constantes.OPCION4);
                opcion = lector.nextInt();
                switch (opcion){
                    case 1:
                        for (int i = 0; i < gestionArticulos.getArticulos().size(); i++) {
                            System.out.println(gestionArticulos.getArticulos().get(i).toString());
                        }
                        break;
                    case 2:
                        for (int i = 0; i < gestionArticulos.getArticulos().size(); i++) {
                            System.out.println(gestionArticulos.getArticulos(false).get(i).toString());
                        }
                        break;
                    case 3: //Sale null, preguntar.
                        System.out.println(Constantes.ADMIN3A);
                        lector.nextLine();
                        String categoriaListar = lector.nextLine().toUpperCase();
                        System.out.println(gestionArticulos.getArticulosCategoria(categoriaListar));
                        break;
                    case 4:
                        System.out.println(Constantes.ADMIN3B);
                        lector.nextLine();
                        String nombre = lector.nextLine();
                        System.out.println(Constantes.ADMIN3C);
                        String categoria = lector.nextLine();
                        System.out.println(Constantes.ADMIN3D);
                        double precio = lector.nextDouble();
                        try {
                            gestionArticulos.aniadirArticulo(nombre,categoria,precio,true);
                        } catch (CategoriaException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 5:
                        System.out.println(Constantes.ADMIN3E);
                        int id = lector.nextInt();
                        gestionArticulos.quitarArticulo(id);
                        break;
                    case 6:
                        System.out.println(Constantes.ADMIN3F);
                        int idEntificacion = lector.nextInt();
                        lector.nextLine();
                        boolean seguir = false;
                        while(!seguir) {
                            System.out.println(Constantes.ADMIN3A);
                            categoria = lector.nextLine();
                            try {
                                Comprobacion.categoriaOk(categoria);
                                gestionArticulos.modificarArticulo(idEntificacion, categoria);
                                seguir = true;
                            }catch(CategoriaException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        System.out.println(Constantes.EXITO);
                        break;
                    case 7:
                        System.out.println(Constantes.ADMIN3F);
                        int idArt = lector.nextInt();
                        System.out.println(Constantes.ADMIN3D);
                        double nuevoPrecio = lector.nextDouble();
                        gestionArticulos.modificarPrecioArt(idArt, nuevoPrecio);
                        System.out.println(Constantes.EXITO);
                        break;
                    case 8:
                        System.out.println(Constantes.ADMIN3F);
                        int idNuevo = lector.nextInt();
                        lector.nextLine();
                        System.out.println(Constantes.ADMIN3H);
                        String categoriaNew = lector.nextLine();
                        try {
                            gestionArticulos.modificarCategoriaArt(idNuevo, categoriaNew);
                        } catch (CategoriaException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Constantes.EXITO);
                        break;
                    case 9:
                        System.out.println(gestionArticulos.comprobarStockDisponibles(false));
                        break;
                    case 10:
                        System.out.println(Constantes.ADMIN3I);
                        int idStock = lector.nextInt();
                        System.out.println(gestionArticulos.comprobarStockArt(idStock));
                        break;
                    case 11:
                        GestionPedidosCliente gestionPedidosCliente = new GestionPedidosCliente();
                        DaoPedidosImplementacion daoPedidosImplementacion = new DaoPedidosImplementacion();
                        daoPedidosImplementacion.getPedidos(gestionPedidosCliente.pedidosList);
                        System.out.println(gestionPedidos.getPedidos(gestionPedidosCliente.pedidosList));
                        break;
                    case 12:
                        System.out.println(Constantes.SALIR);
                        salir = true;
                        break;
                    default:
                        System.out.println(Constantes.OPCION0);
                }
            }catch (InputMismatchException e){
                lector.next();
                System.out.println(Constantes.ADMIN4ERROR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while(salir == false);
    }
    public void cargarConfiguracion() {
        Properties propiedades = new Properties();
        FileInputStream entrada = null;

        try {
            entrada = new FileInputStream("config.properties");
            propiedades.load(entrada);

            String usuario = propiedades.getProperty("usuario");
            String pass2 = propiedades.getProperty("contraseña");
            this.pass=pass2;
        } catch (IOException e) {
            System.out.println(Constantes.FALLOWFICHEROB);
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    System.out.println(Constantes.FALLOWFICHERO);
                }
            }
        }
    }
}
