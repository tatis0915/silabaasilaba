package com.silaba_a_silaba;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReportesTest {
    @Test
    public void testReportesConstructor() {
        Reportes reportes = new Reportes(1);
        assertNotNull(reportes);
        // Verificar que usuarioId se asigna correctamente
        try {
            java.lang.reflect.Field field = Reportes.class.getDeclaredField("usuarioId");
            field.setAccessible(true);
            int valor = field.getInt(reportes);
            assertEquals(1, valor);
        } catch (Exception e) {
            fail("No se pudo acceder al campo usuarioId: " + e.getMessage());
        }
    }

    @Test
    public void testNombreEstudianteAsignado() {
        Reportes reportes = new Reportes(1);
        try {
            java.lang.reflect.Field field = Reportes.class.getDeclaredField("nombreEstudiante");
            field.setAccessible(true);
            Object nombre = field.get(reportes);
            assertNotNull(nombre);
        } catch (Exception e) {
            fail("No se pudo acceder al campo nombreEstudiante: " + e.getMessage());
        }
    }

    @Test
    public void testIdentificacionAsignada() {
        Reportes reportes = new Reportes(1);
        try {
            java.lang.reflect.Field field = Reportes.class.getDeclaredField("identificacion");
            field.setAccessible(true);
            Object identificacion = field.get(reportes);
            assertNotNull(identificacion);
        } catch (Exception e) {
            fail("No se pudo acceder al campo identificacion: " + e.getMessage());
        }
    }
}
