package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.CategoriaException;
import org.example.Domain.Articulo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Articulos {
    private  List<Articulo> articulos;

    public Articulos (List<Articulo> articulos){
        this.articulos = articulos;
    }
    public Articulos() throws IOException {
        this.articulos = new ArrayList<Articulo>();
        articulos.forEach(articulo -> System.out.println(articulo.toString()));
        try {
            articulos.add(new Articulo("Cable RGB", String.valueOf(Categoria.CABLES),3.5 ,false));
            articulos.add(new Articulo("Behringer Xenyx 802S", String.valueOf(Categoria.DJ), 89.00  ,true));
            articulos.add(new Articulo("TORRE RGB", String.valueOf(Categoria.PCS), 2 ,true));
            articulos.add(new Articulo("ALTAVOCES SONY",String.valueOf(Categoria.SONIDO), 1,true));
            articulos.add(new Articulo("MICROFONO WORTEX 2000",String.valueOf(Categoria.SONIDO),2,true));
            articulos.add(new Articulo("TECLADO MILON",String.valueOf(Categoria.INSTRUMENTOS), 2,false));
            articulos.add(new Articulo("RAM 8GB CORSAIR",String.valueOf(Categoria.SOFTWARE), 1,true));
            articulos.add(new Articulo("SOPORTE TECLADO",String.valueOf(Categoria.SOPORTES),1,false));
            articulos.add(new Articulo("XILOFONO MARC LOCS",String.valueOf(Categoria.INSTRUMENTOS),1,false));
            articulos.add(new Articulo("CABLE ALIMENTACION 2m",String.valueOf(Categoria.CABLES),2,false));
            articulos.add(new Articulo("Yamaha b1 PE",String.valueOf(Categoria.INSTRUMENTOS),30,true));
            articulos.add(new Articulo("Millenium Focus 22 Drum Set Black",String.valueOf(Categoria.INSTRUMENTOS),50,true));
            articulos.add(new Articulo("Native Instruments Traktor S4 MK3", String.valueOf(Categoria.DJ),80,false));
            articulos.add(new Articulo("Steinberg Cubase Pro 12",String.valueOf(Categoria.SOFTWARE),55.50,true));
            articulos.add(new Articulo("Avid Pro Tools Studio Perpetual",String.valueOf(Categoria.SOFTWARE),58,false));
            articulos.add(new Articulo("pro snake TPY 2015 KPP",String.valueOf(Categoria.CABLES),4.99,true));
            articulos.add(new Articulo("Millenium BS-2010",String.valueOf(Categoria.SOPORTES),20.80,true));
            articulos.add(new Articulo("Cordoba F7 Flamenco Iberia",String.valueOf(Categoria.INSTRUMENTOS),50.99,true));
            articulos.add(new Articulo("Yamaha V10 SG 4/4 OV",String.valueOf(Categoria.INSTRUMENTOS), 100.99,true));
            articulos.add(new Articulo("Behringer FBQ1502HD Ultragraph Pro",String.valueOf(Categoria.SONIDO),10,false));
        }catch(CategoriaException e){
            System.out.println(e.getMessage());
        }
        //articulos = DaoFicheros.leerFichero();
    }
    public List<Articulo> getListaArticulos() {
        return articulos;
    }
    public void setListaArticulos(){
        this.articulos.clear();
        this.articulos.addAll(articulos);
    }

    @Override
    public String toString() {
        return "Articulos{" +
                "articulos=" + articulos +
                '}';
    }
}
