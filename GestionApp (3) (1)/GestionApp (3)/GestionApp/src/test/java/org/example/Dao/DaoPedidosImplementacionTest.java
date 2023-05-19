package org.example.Dao;

import org.example.Common.CategoriaException;
import org.example.Common.DestinoException;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class DaoPedidosImplementacionTest {

    @InjectMocks
    private DaoPedidosImplementacion daoPedidosImplementacionAux;

    @Mock
    private List<Articulo> articulos;
    private static List<Articulo> getArticulos() {
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            listaArticulos.add(new Articulo("Caja", "PCS", 10.00, true));
            listaArticulos.add(new Articulo("Lopo", "SOFTWARE", 20.00, true));
        } catch (CategoriaException e) {
            throw new RuntimeException(e);
        }
        return listaArticulos;
    }

    @Test
    @DisplayName("Ver carrito de pedidos")
    void verCarrito() {
        // Configurar los datos de prueba
        List<Articulo> listaArticulos = getArticulos();

        when(articulos.isEmpty()).thenReturn(false);
        when(articulos.size()).thenReturn(listaArticulos.size());
        when(articulos.get(0)).thenReturn(listaArticulos.get(0));
        when(articulos.get(1)).thenReturn(listaArticulos.get(1));

        // Llamar al método verCarrito()
        List<Articulo> resultado = daoPedidosImplementacionAux.verCarrito();

        // Verificar el resultado esperado
        assertEquals(listaArticulos.size(), resultado.size());
    }

    @ParameterizedTest
    @MethodSource("getArticulos")
    @DisplayName("Quitar artículo")
    void quitarArticulo(Articulo articulo) {
        // Configurar los datos de prueba en la lista de artículos
        List<Articulo> listaArticulos = getArticulos();
        when(articulos.removeIf(any())).thenReturn(true);

        // Llamar al método quitarArticulo() con el ID del artículo
        boolean resultado = daoPedidosImplementacionAux.quitarArticulo(articulo.getId());

        // Verificar que el artículo haya sido eliminado de la lista
        assertTrue(resultado);
        verify(articulos).removeIf(any());
    }

    @Test
    @DisplayName("Quitar todos los artículos")
    void quitarTodosArticulos() {
        // Configurar los datos de prueba en la lista de artículos
        List<Articulo> listaArticulos = getArticulos();
        when(articulos.removeIf(any())).thenReturn(true);

        // Llamar al método quitarTodosArticulos()
        boolean resultado = daoPedidosImplementacionAux.quitarTodosArticulos();

        // Verificar que la lista de artículos esté vacía
        assertTrue(resultado);
        verify(articulos).clear();
    }
}