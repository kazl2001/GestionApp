package org.example.Service;

import lombok.SneakyThrows;


import lombok.extern.log4j.Log4j2;
import org.example.Dao.DaoArticulosImplementacion;
import org.example.Service.GestionArticulos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Common.Categoria;
import org.example.Common.CategoriaException;
import org.example.Domain.Articulo;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Log4j2
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestionArticulosTest {
    private static final Logger logger = Logger.getLogger(String.valueOf(GestionArticulosTest.class));
    @InjectMocks
    GestionArticulos gestionArticulos;

    @Mock
    DaoArticulosImplementacion daoArticulosImplementacion;
    @Test
    @Order(2)
    void aniadirCategoria() {
    }

    @Test
    @Order(3)
    void getListaArticulos() throws CategoriaException {
        //Given GWT ó AAA Arrange Act Assert
        List<Articulo> articulos = new ArrayList<>();
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
        //Mostramos los resultados
        //articulos.stream().forEach(System.out::println);

        //When
        when(daoArticulosImplementacion.getArticulos()).thenReturn(articulos);
        List<Articulo> respuesta = gestionArticulos.getArticulos();

        //Then
        assertThat(respuesta).isEqualTo(articulos);

    }
    @Order(1)
    @Test
       void isEmptyAlojamientosList() {
        //when
        boolean respuesta = gestionArticulos.listaVacia();

        //then
        assertThat(respuesta).isTrue();

    }

    @DisplayName("Probando excepciones")
    @Nested
    class Excepciones {
        @SneakyThrows
        @Test
        @Order(4)
        void aniadirArticulo() {
            //given
            Articulo articuloAux = new Articulo("Casa", "INSTRUMENTOS", 20.00, true);

            //when
            when(daoArticulosImplementacion.aniadirArticulo(articuloAux));
            boolean respuesta;
            try {
                respuesta = gestionArticulos.aniadirArticulo("Casa2", "INSTRUMENTOS", 20.00, true);
                logger.info("Usuario creado");
            } catch (CategoriaException e) {
                logger.warning("Error al añadir el articulo, categoria inválida");
                throw new RuntimeException(e);

            }

            //then
            assertThat(respuesta).isTrue();

        }

        @Test
        @Order(5)
        void aniadirArticuloError() {
            //given
            Articulo articuloAux = null;
            try {
                articuloAux = new Articulo("Casa", "INSTRUMENTOS", 20.00, true);
            } catch (CategoriaException e) {
                throw new RuntimeException(e);
            }


            //when
            boolean respuesta=false;
            try {
                respuesta = gestionArticulos.aniadirArticulo(articuloAux);
                logger.info("Usuario creado");
            } catch (CategoriaException e) {
                assertThat(e.getMessage()).isEqualToIgnoringCase("La categoria debe estar entre 1 y 5");
                assertThat(respuesta).isFalse();
                logger.warning("El articulo tiene categoria inválida");
                throw new RuntimeException(e);
            }

            //then
            verify(daoArticulosImplementacion,times(0)).aniadirArticulo(articuloAux); //comprobar que no se ejecuta (0)

        }




    }

    @Test
    @Order(6)
    void quitarArticulo() {
        // Datos de prueba
        int idAux = 1;

        // Configurar el comportamiento simulado del DAO
        when(daoArticulosImplementacion.quitarArticulo(idAux)).thenReturn(true);

        // Llamar al método bajo prueba
        boolean resultado = gestionArticulos.quitarArticulo(idAux);

        // Verificar que el método del DAO se llamó correctamente
        verify(daoArticulosImplementacion, times(1)).quitarArticulo(idAux);

        // Verificar el resultado
        assertTrue(resultado);
    }

    @Test
    @Order(7)
    void modificarArticulo(){
        // Datos de prueba
        int idAux = 1;
        String nuevaCategoria = "PCS";

        // Configurar el comportamiento simulado del DAO
        try {
            when(daoArticulosImplementacion.modificarArticulo(idAux, nuevaCategoria)).thenReturn(true);
        } catch (CategoriaException e) {
            logger.warning("El articulo tiene categoria inválida");
            throw new RuntimeException(e);
        }

        // when
        boolean resultado = false;
        try {
            resultado = gestionArticulos.modificarArticulo(idAux, nuevaCategoria);
            logger.info("Articulo modificado");
        } catch (Exception e) {
            logger.warning("Error al modificar el articulo");
            throw new RuntimeException(e);
        }

        // then
        try {
            verify(daoArticulosImplementacion, times(1)).modificarArticulo(idAux, nuevaCategoria);
        } catch (CategoriaException e) {
            logger.warning("Error al modificar el articulo");
            throw new RuntimeException(e);
        }
        assertThat(resultado).isTrue();
    }

    @Test
    @Order(8)
    void modificarPrecioArt() {
        // Datos de prueba
        int idAux = 1;
        double nuevoPrecio = 19.99;

        // Configurar el comportamiento simulado del DAO
        when(daoArticulosImplementacion.modificarPrecioArt(idAux , nuevoPrecio)).thenReturn(true);

        // when
        boolean resultado = false;
        try {
            resultado = gestionArticulos.modificarPrecioArt(idAux, nuevoPrecio);
            logger.info("Precio del artículo modificado");
        } catch (Exception e) {
            logger.warning("Error al modificar el precio del artículo");
            throw new RuntimeException(e);
        }

        // then
        verify(daoArticulosImplementacion, times(1)).modificarPrecioArt(idAux, nuevoPrecio);
        assertThat(resultado).isTrue();
    }

    @Test
    @Order(9)
    void comprobarStockArt() {
        // Datos de prueba
        int idAux = 3;
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            listaArticulos.add(new Articulo("Palo", "PCS", 12.00, true));
            listaArticulos.add(new Articulo("Suwi", "SONIDO", 10.00, true));
        } catch (CategoriaException e) {
            logger.warning("El articulo tiene categoria inválida");
            throw new RuntimeException(e);
        }

        // Configurar el comportamiento simulado del DAO
        when(daoArticulosImplementacion.comprobarStockArt(idAux)).thenReturn(listaArticulos);

        // when
        List<Articulo> resultado = gestionArticulos.comprobarStockArt(idAux);
        logger.info("Stock de artículo comprobado");

        // then
        verify(daoArticulosImplementacion, times(1)).comprobarStockArt(idAux);
        assertThat(resultado).isEqualTo(listaArticulos);
    }

    @Test
    @Order(10)
    void comprobarStockDisponibles() {
        // Datos de prueba
        boolean disponibilidad = true;
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            listaArticulos.add(new Articulo("Artículoa", "PCS", 10.0, true));
            listaArticulos.add(new Articulo("Artículox", "PCS", 20.0, true));
        } catch (CategoriaException e) {
            logger.warning("El articulo tiene categoria inválida");
            throw new RuntimeException(e);
        }


        // Configurar el comportamiento simulado del DAO
        when(daoArticulosImplementacion.comprobarStockDisponibles(disponibilidad)).thenReturn(listaArticulos);

        // when
        List<Articulo> resultado = gestionArticulos.comprobarStockDisponibles(disponibilidad);
        logger.info("Stock de artículos disponibles comprobado");

        // then
        verify(daoArticulosImplementacion, times(1)).comprobarStockDisponibles(disponibilidad);
        assertThat(resultado).isEqualTo(listaArticulos);
    }

    @Test
    @Order(11)
    void getArticulosPrecio() {
        // Datos de prueba
        double precioBuscado = 10.0;
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            listaArticulos.add(new Articulo("Artículo 1", "SOFTWARE", 10.0, true));
            listaArticulos.add(new Articulo("Artículo 2", "DJ", 20.0, true));
        } catch (CategoriaException e) {
            logger.warning("El articulo tiene categoria inválida");
            throw new RuntimeException(e);
        }


        // Configurar el comportamiento simulado del DAO
        when(daoArticulosImplementacion.getArticulosPrecio(precioBuscado)).thenReturn(listaArticulos);

        // when
        List<Articulo> resultado = gestionArticulos.getArticulosPrecio(precioBuscado);
        logger.info("Obtenidos artículos con precio " + precioBuscado);

        // then
        verify(daoArticulosImplementacion, times(1)).getArticulosPrecio(precioBuscado);
        assertThat(resultado).isEqualTo(listaArticulos);

    }
}