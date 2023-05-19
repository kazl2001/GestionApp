package org.example.Dao;

import org.example.Common.Categoria;
import org.example.Common.CategoriaException;
import org.example.Domain.Articulo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class DaoArticulosImplementacionTest {
    @InjectMocks
    private DaoArticulosImplementacion daoArticulos;
    @Mock
    Articulos articulosAux = new Articulos(new ArrayList<>());
    private Articulos listaAux;

    @Test
    @Order(1)
    void aniadirArticulo() throws CategoriaException {
       // Datos de prueba
        String nombre = "Prueba";
        String categoria = "SOFTWARE";
        double precio = 10.0;
        boolean disponibilidad = true;

        // Crear el artículo de prueba
        Articulo articuloPrueba = new Articulo(nombre, categoria, precio, disponibilidad);
        articuloPrueba.setId(2);

        // Crear la lista de artículos y agregar el artículo de prueba
        List<Articulo> listaArticulos = new ArrayList<>();

        // Configurar el comportamiento simulado del mock
        when(articulosAux.getListaArticulos()).thenReturn(listaArticulos);

        // Crear la instancia de la clase bajo prueba y setear el mock
        DaoArticulosImplementacion daoArticulos = new DaoArticulosImplementacion(articulosAux);

        // Llamar al método bajo prueba
        boolean resultado = daoArticulos.aniadirArticulo(nombre, categoria, precio, disponibilidad);

        // Verificar que el método se llamó correctamente
        verify(articulosAux, times(1)).getListaArticulos();

        // Verificar el resultado
        assertTrue(resultado);
        assertEquals(1, listaArticulos.size());
    }

    @Test
    @Order(3)
    void quitarArticuloId() throws CategoriaException {
        // Datos de prueba
        int idPrueba = 1; //metiendo id en parámetro

        // Crear el artículo de prueba
        Articulo articulo1 = new Articulo("Prueba2", "PCS", 3.0, true);
        articulo1.setId(idPrueba);

        // Crear la lista de artículos y agregar el artículo de prueba
        List<Articulo> listaArticulos = new ArrayList<>();
        listaArticulos.add(articulo1);

        // Crear el mock de la clase Articulos
        Articulos articulosMock = mock(Articulos.class);

        // Configurar el comportamiento simulado del mock
        when(articulosMock.getListaArticulos()).thenReturn(listaArticulos);

        // Crear la instancia de la clase bajo prueba y setear el mock
        DaoArticulosImplementacion daoArticulos = new DaoArticulosImplementacion(articulosMock);

        // Llamar al método bajo prueba
        boolean resultado = daoArticulos.quitarArticulo(idPrueba);

        // Verificar que el método se llamó correctamente
        verify(articulosMock, times(1)).getListaArticulos();

        // Verificar el resultado
        assertTrue(resultado);
        assertTrue(listaArticulos.isEmpty());
    }
    @Nested
    class quitarArticuloDeUna{
        @Test
        @Order(2)
        void quitarArticuloDirecto() {
            // Crear lista vacai
            List<Articulo> articulos = new ArrayList<>();
            Articulo articulo = null;
            try {
                articulo = new Articulo("Popito", "SONIDO", 55.0,  true);
            } catch (CategoriaException e) {
                throw new RuntimeException(e);
            }
            articulos.add(articulo);

            // Mockear el comportamiento del método getListaArticulos()
            when(listaAux.getListaArticulos()).thenReturn(articulos);

            // LLamar al metodo a probar
            boolean result = daoArticulos.quitarArticulo(articulo);

            // Verificar que se llama al método removeIf() en la lista
            verify(listaAux.getListaArticulos()).removeIf(any());

            // Asegurar el resultado
            assertTrue(result);
        }

        @Test
        @Order(4)
        void quitarArticuloNoDirecto() throws CategoriaException {
            // Crear una lista vacía
            List<Articulo> articulos = new  ArrayList<>();

            // Mockear el comportamiento del método getListaArticulos()
            when(listaAux.getListaArticulos()).thenReturn(articulos);

            // Crear un artículo que no existe en la lista
            Articulo articulo = new Articulo("FueraLista", "PCS", 10.0, true);
            articulo.setId(1);

            // Llamar al metodo a probar
            boolean result =  daoArticulos.quitarArticulo(articulo);

            // Verificar que no se llame al método removeIf() en la lista
            verify(listaAux.getListaArticulos()).removeIf(a -> a.equals(articulo));

            // Asegurar que el artículo no fue eliminado
            assertFalse(result);


        }
    }



    @Test
    @Order(6)
    void modificarPrecioArt() throws CategoriaException {
        // Crear lista de prueba con un artículo existente
        List<Articulo> articulos = new ArrayList<>();
        Articulo articuloExistente = new Articulo("Musicote",  "SONIDO", 70.0, true);
        articuloExistente.setId(1);
        articulos.add(articuloExistente);


        // Mockear el comportamiento del método getListaArticulos()
        when(listaAux.getListaArticulos()).thenReturn(articulos);

        // Llamar al método a probar
        boolean result = daoArticulos.modificarPrecioArt(1, 60.0);

        // Verificar que se llame al método setId() en el artículo existente
        verify(articuloExistente).setPrecio(60.0);

        // Asegurar el resultado
        assertTrue(result);
    }
    @Nested
    class Actualizacion{
        @Test
        @Order(5)
        void modificarCategoriaArticuloExistente() throws CategoriaException {
            // Crear lista de prueba con un artículo existente
            List<Articulo> articulos = new ArrayList<>();
            Articulo articuloExistente = new Articulo("Pala", "INSTRUMENTOS", 9.0, true);
            articuloExistente.setId(1);
            articulos.add(articuloExistente);

            // Mockear el comportamiento del método getListaArticulos()
            when(listaAux.getListaArticulos()).thenReturn(articulos);

            // Llamar al método a probar
            boolean result = daoArticulos.modificarCategoriaArt(1, "SOPORTES");

            // Verificar que se llame al método setCategoria() en el artículo existente
            verify(articuloExistente).setCategoria("SOPORTES");

            // Asegurar el resultado
            assertTrue(result);
        }

        @Test
        @Order(7)
        void modificarCategoriaArticuloNoExistente() throws CategoriaException {
            // Crear lista de prueba sin el artículo buscado
            List<Articulo> articulos = new ArrayList<>();
            Articulo articuloNoExistente = new Articulo("Popito", "SONIDO", 55.0, true);
            articuloNoExistente.setId(1);
            articulos.add(articuloNoExistente);

            // Mockear el comportamiento del método getListaArticulos()
            when(listaAux.getListaArticulos()).thenReturn(articulos);

            // Llamar al método a probar
            boolean result = daoArticulos.modificarCategoriaArt(2, "ILUMINACION");

            // Verificar que no se llame al método setCategoria() en ningún artículo
            verify(articuloNoExistente, never()).setCategoria(anyString());


            // Asegurar el resultado
            assertFalse(result);
        }
    }

    @Test
    @Order(8)
    void comprobarStockArt() throws CategoriaException {
        // Datos de prueba
        int idPrueba = 1;

        // Crear una lista de artículos de prueba
        List<Articulo> listaArticulos = new ArrayList<>();
        Articulo articulo1 = new Articulo("Prueba", "PCS", 10.0, true);
        articulo1.setId(1);
        Articulo articulo2 = new Articulo("Prueba2", "DJ", 20.0, false);
        articulo2.setId(2);
        listaArticulos.add(articulo1);
        listaArticulos.add(articulo2);

        // Configurar el comportamiento simulado del mock
        when(articulosAux.getListaArticulos()).thenReturn(listaArticulos);

        // Llamar al método
        List<Articulo> resultado = daoArticulos.comprobarStockArt(idPrueba);

        // Verificar que el método se llamó correctamente
        verify(articulosAux, times(1)).getListaArticulos();

        // Verificar el resultado
        assertEquals(1, resultado.size());
        assertEquals(articulo1, resultado.get(0));
    }

    @Test
    @Order(9)
    void comprobarStockDisponibles() throws CategoriaException {
        // Datos de prueba
        boolean disponibilidad = true;

        // Crear una lista de artículos de prueba
        List<Articulo> listaArticulos = new ArrayList<>();
        Articulo articulo1 = new Articulo("Mesa", "DJ", 15.0, true);
        articulo1.setId(1);
        Articulo articulo2 = new Articulo("Cable", "CABLES", 60.0, false);
        articulo2.setId(2);
        listaArticulos.add(articulo1);
        listaArticulos.add(articulo2);

        // Configurar el comportamiento simulado del mock
        when(articulosAux.getListaArticulos()).thenReturn(listaArticulos);

        // Llamar al método bajo prueba
        List<Articulo> resultado = daoArticulos.comprobarStockDisponibles(disponibilidad);

        // Verificar que el método se llamó correctamente
        verify(articulosAux, times(1)).getListaArticulos();

        // Verificar el resultado
        assertEquals(1, resultado.size());
        assertEquals(articulo1, resultado.get(0));
    }

    @Test
    @Order(10)
    void getArticulos() throws CategoriaException {
        //given
        List<Articulo> lista = new ArrayList<>();
        lista.add(new Articulo("Cable RGB", String.valueOf(Categoria.CABLES),3.5 ,false));
        lista.add(new Articulo("Behringer Xenyx 802S", String.valueOf(Categoria.DJ), 89.00  ,true));
        lista.add(new Articulo("TORRE RGB", String.valueOf(Categoria.PCS), 2 ,true));
        lista.add(new Articulo("ALTAVOCES SONY",String.valueOf(Categoria.SONIDO), 1,true));
        lista.add(new Articulo("MICROFONO WORTEX 2000",String.valueOf(Categoria.SONIDO),2,true));

        //when
        when(daoArticulos.getArticulos()).thenReturn(lista);

        List<Articulo> result = daoArticulos.getArticulos();

        //then;
        assertAll(
                () -> assertThat(result).isEqualTo(lista),
                () -> assertThat(result).isNotNull()
        );
    }

    @Test
    @Order(11)
    void getArticulosDisponiblidad() throws CategoriaException {
        // Given
        boolean disponibilidad = true;

        // Crear una lista de artículos de prueba
        List<Articulo> lista = new ArrayList<>();
        lista.add(new Articulo("Cable RGB", String.valueOf(Categoria.CABLES), 3.5, true));
        lista.add(new Articulo("Behringer Xenyx 802S", String.valueOf(Categoria.DJ), 89.00, true));
        lista.add(new Articulo("ALTAVOCES SONY", String.valueOf(Categoria.SONIDO), 1, true));

        // Configurar el comportamiento simulado del mock
        when(daoArticulos.getArticulosDisponiblidad(disponibilidad)).thenReturn(lista);

        // When
        List<Articulo> result = daoArticulos.getArticulosDisponiblidad(disponibilidad);

        // Then
        assertAll(
                () -> assertThat(result).isEqualTo(lista),
                () -> assertThat(result).isNotNull()
        );
    }

    @Test
    @Order(12)
    void getArticulosPrecio() throws CategoriaException {
        // Given
        double precioBuscado = 10.0;

        // Crear una lista de artículos de prueba
        List<Articulo> lista = new ArrayList<>();
        lista.add(new Articulo("Cable RGB", String.valueOf(Categoria.CABLES), 10.00, false));
        lista.add(new Articulo("Behringer Xenyx 802S", String.valueOf(Categoria.DJ), 10.00, true));
        lista.add(new Articulo("ALTAVOCES SONY", String.valueOf(Categoria.SONIDO), 10.00, true));

        // Configurar el comportamiento simulado del mock
        when(daoArticulos.getArticulosPrecio(precioBuscado)).thenReturn(lista);

        // When
        List<Articulo> result = daoArticulos.getArticulosPrecio(precioBuscado);

        // Then
        assertAll(
                () -> assertThat(result).isEqualTo(lista),
                () -> assertThat(result).isNotNull()
        );
    }
}