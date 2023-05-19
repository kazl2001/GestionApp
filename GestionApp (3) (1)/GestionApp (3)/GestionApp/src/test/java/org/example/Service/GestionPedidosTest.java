package org.example.Service;

import org.example.Dao.DaoArticulosImplementacion;

import org.example.Dao.DaoPedidosImplementacion;
import org.example.Service.GestionPedidos;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Common.CategoriaException;
import org.example.Common.DestinoException;
import org.example.Domain.Articulo;
import org.example.Domain.Pedido;
import org.junit.jupiter.api.Test;
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
class GestionPedidosTest {
    @InjectMocks
    GestionPedidos gestionPedidosAux;

    @Mock
    DaoPedidosImplementacion daoPedidosImplementacion;

    @Test
    @Order(1)
    @DisplayName("Listar articulos carrito")
    void verCarrito() {
        // Configurar los datos de prueba
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            listaArticulos.add(new Articulo("Caja", "PCS", 10.00, true));
            listaArticulos.add(new Articulo("Lopo", "SOFTWARE", 20.00, true));
        } catch (CategoriaException e) {
            throw new RuntimeException(e);
        }

        when(daoPedidosImplementacion.verCarrito()).thenReturn(listaArticulos);

        // Llamar al método verCarrito()
        List<Articulo> resultado = null;
        try {
            resultado = gestionPedidosAux.verCarrito();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        // Verificar el resultado esperado
        assertThat(resultado).isEqualTo(listaArticulos);
        assertEquals(listaArticulos.size(), resultado.size());
        assertEquals(listaArticulos.get(0), resultado.get(0));
        assertEquals(listaArticulos.get(1), resultado.get(1));
    }

    @Nested
    class eliminar {
        @Test
        @Order(3)
        @DisplayName("Quitar un artículo")
        void quitarArticulo() {
            // Configurar los datos de prueba en la lista de artículos
            //given
            Articulo articulo = null;
            try {
                articulo = new Articulo("Caja", "PCS", 10.00, true);
            } catch (CategoriaException e) {
                throw new RuntimeException(e);
            }
            articulo.setId(1);
            //when
            when(daoPedidosImplementacion.quitarArticulo(articulo.getId())).thenReturn(true);

            // Llamar al método quitarArticulo() con el artículo
            boolean resultado = gestionPedidosAux.quitarArticulo(articulo.getId());

            // Verificar que el artículo haya sido eliminado
            assertTrue(resultado);
            verify(daoPedidosImplementacion).quitarArticulo(articulo.getId());
        }

        @Test
        //    @Order(2)
        @DisplayName("Quitar todos los artículos")
        void quitarTodosArticulos() {
            when(daoPedidosImplementacion.quitarTodosArticulos()).thenReturn(true);

            // Llamar al método quitarTodosArticulos()
            boolean resultado = gestionPedidosAux.quitarTodosArticulos();

            // Verificar que todos los artículos hayan sido eliminados
            assertTrue(resultado);
            verify(daoPedidosImplementacion).quitarTodosArticulos();
        }
    }
}