package org.example.UI;

import org.example.Common.CategoriaException;
import org.example.Common.Constantes;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.UI.GestionArtAdmin.identificacion;

public class GestionApp {
    public static void panel(){ //Menú principal.
        boolean salir = false;
        int opcion = 0;
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.MENU);
        do {
            try{
                System.out.println("1. " + Constantes.OPCION1 + "\n2. " + Constantes.OPCION2 + "\n3. " + Constantes.OPCION4);
                opcion = lector.nextInt();
                if (opcion == 1){
                    try {
                        GestionPedidosCliente gestionPedidosCliente = new GestionPedidosCliente();
                        gestionPedidosCliente.menuCliente();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if (opcion == 2) {
                    identificacion();
                } else if (opcion == 3) {
                    System.out.println(Constantes.SALIR);
                    salir = true;
                }else
                    System.out.println(Constantes.OPCION0);
            }catch (InputMismatchException es){
                lector.next();
                System.out.println(Constantes.OPCION0);
            }catch (CategoriaException es){
                System.out.println(Constantes.OPCION0);
            }
        }while (salir != true);
    }
}
