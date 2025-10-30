package com.silaba_a_silaba;

import org.junit.Test;
import static org.junit.Assert.*;

public class Nivel1Test {
    @Test
    public void testNivel1Constructor() {
        Nivel1 nivel1 = new Nivel1(1);
        assertNotNull(nivel1);
        // Verificar que usuarioId se asigna correctamente
        try {
            java.lang.reflect.Field field = Nivel1.class.getDeclaredField("usuarioId");
            field.setAccessible(true);
            int valor = field.getInt(nivel1);
            assertEquals(1, valor);
        } catch (Exception e) {
            fail("No se pudo acceder al campo usuarioId: " + e.getMessage());
        }
    }

    @Test
    public void testDiccionarioInicializado() {
        Nivel1 nivel1 = new Nivel1(1);
        try {
            java.lang.reflect.Field field = Nivel1.class.getDeclaredField("palabrasYSilabas");
            field.setAccessible(true);
            java.util.Map<?,?> diccionario = (java.util.Map<?,?>) field.get(nivel1);
            assertNotNull(diccionario);
            assertTrue(diccionario.size() > 0);
        } catch (Exception e) {
            fail("No se pudo acceder al diccionario: " + e.getMessage());
        }
    }

    @Test
    public void testPalabrasDelNivelInicializadas() {
        Nivel1 nivel1 = new Nivel1(1);
        try {
            java.lang.reflect.Field field = Nivel1.class.getDeclaredField("palabrasDelNivel");
            field.setAccessible(true);
            java.util.List<?> palabras = (java.util.List<?>) field.get(nivel1);
            assertNotNull(palabras);
            assertTrue(palabras.size() > 0);
        } catch (Exception e) {
            fail("No se pudo acceder a la lista de palabras: " + e.getMessage());
        }
    }
}
