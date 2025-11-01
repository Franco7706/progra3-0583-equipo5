package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.MonedasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.TiposDeCambioDAOImpl;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TiposDeCambioDAOTest {

    protected static TiposDeCambioDAO dao;
    protected static TiposDeCambioDTO testTipoCambio;
    private static Integer generatedId;

    protected static MonedasDAO monedasDAO;
    protected static MonedasDTO monedaOrigen;
    protected static MonedasDTO monedaDestino;

    @BeforeAll
    static void init() {
        monedasDAO = new MonedasDAOImpl();

        // Crear moneda origen
        monedaOrigen = new MonedasDTO(0, "MonedaOrigenTC", "MOT", "O$");
        monedaOrigen.setMoneda_id(monedasDAO.insertar(monedaOrigen));

        // Crear moneda destino
        monedaDestino = new MonedasDTO(0, "MonedaDestinoTC", "MDT", "D$");
        monedaDestino.setMoneda_id(monedasDAO.insertar(monedaDestino));
    }

    @AfterAll
    static void cleanup() {
        try { monedasDAO.eliminar(monedaOrigen); } catch (Exception ignored) {}
        try { monedasDAO.eliminar(monedaDestino); } catch (Exception ignored) {}
    }

    @BeforeEach
    void setUp() {
        dao = new TiposDeCambioDAOImpl();

        testTipoCambio = new TiposDeCambioDTO();
        testTipoCambio.setFecha(new Date(2025,11,1,15,9));
        testTipoCambio.setTasa_de_cambio(BigDecimal.valueOf(3.50));
        testTipoCambio.setMoneda_origen(monedaOrigen);
        testTipoCambio.setMoneda_destino(monedaDestino);
    }

    @AfterEach
    void tearDown() {
        if (generatedId != null && generatedId > 0) {
            try {
                testTipoCambio.setTipo_cambio_id(generatedId);
                dao.eliminar(testTipoCambio);
            } catch (Exception ignored) {}
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar un tipo de cambio y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testTipoCambio);

        assertNotNull(nuevoId, "El ID generado no debe ser null");
        assertTrue(nuevoId > 0, "El ID generado debe ser mayor que 0");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener un tipo de cambio por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testTipoCambio);

        TiposDeCambioDTO tipoCambio = dao.obtenerPorId(generatedId);

        assertNotNull(tipoCambio, "El tipo de cambio obtenido no debe ser null");
        assertEquals(generatedId, tipoCambio.getTipo_cambio_id(), "El ID del tipo de cambio no coincide");
        assertEquals(monedaOrigen.getMoneda_id(), tipoCambio.getMoneda_origen().getMoneda_id(), "Moneda origen incorrecta");
        assertEquals(monedaDestino.getMoneda_id(), tipoCambio.getMoneda_destino().getMoneda_id(), "Moneda destino incorrecta");

        assertNull(dao.obtenerPorId(-1), "Debe devolver null para un ID inexistente");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar un tipo de cambio existente")
    void testModificar() {
        generatedId = dao.insertar(testTipoCambio);

        testTipoCambio.setTipo_cambio_id(generatedId);
        testTipoCambio.setTasa_de_cambio(BigDecimal.valueOf(4.00));

        int resultado = dao.modificar(testTipoCambio);
        assertTrue(resultado > 0, "Modificar debe afectar al menos una fila");

        TiposDeCambioDTO modificado = dao.obtenerPorId(generatedId);
        assertNotNull(modificado, "El tipo de cambio modificado no debe ser null");
        assertTrue(BigDecimal.valueOf(4.00).compareTo(modificado.getTasa_de_cambio())==0, "La tasa de cambio debe actualizarse");
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todos los tipos de cambio")
    void testListarTodos() {
        generatedId = dao.insertar(testTipoCambio);

        ArrayList<TiposDeCambioDTO> lista = (ArrayList<TiposDeCambioDTO>) dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser null");
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
        assertTrue(lista.stream().anyMatch(tc -> Objects.equals(tc.getTipo_cambio_id(), generatedId)),
                "La lista debe contener el tipo de cambio insertado");
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar un tipo de cambio")
    void testEliminar() {
        generatedId = dao.insertar(testTipoCambio);
        testTipoCambio.setTipo_cambio_id(generatedId);

        int resultado = dao.eliminar(testTipoCambio);
        assertTrue(resultado > 0, "Eliminar debe afectar al menos una fila");

        assertNull(dao.obtenerPorId(generatedId), "Después de eliminar, obtenerPorId debe devolver null");
        generatedId = 0; // Para evitar que tearDown intente borrar otra vez
    }
}
