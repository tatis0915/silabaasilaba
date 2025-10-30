package com.silaba_a_silaba;

import org.junit.Test;
import static org.junit.Assert.*;

public class OpcionesTest {
    @Test
    public void testOpcionesConstructor() {
        Opciones opciones = new Opciones(1);
        assertNotNull(opciones);
        // Verificar que usuarioId se asigna correctamente
        // Usando reflexión porque usuarioId es privado
        try {
            java.lang.reflect.Field field = Opciones.class.getDeclaredField("usuarioId");
            field.setAccessible(true);
            int valor = field.getInt(opciones);
            assertEquals(1, valor);
        } catch (Exception e) {
            fail("No se pudo acceder al campo usuarioId: " + e.getMessage());
        }
    }
}
