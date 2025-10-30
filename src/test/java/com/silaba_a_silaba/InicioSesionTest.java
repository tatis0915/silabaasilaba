package com.silaba_a_silaba;

import org.junit.Test;
import static org.junit.Assert.*;

public class InicioSesionTest {
    @Test
    public void testConstructor() {
        InicioSesion inicio = new InicioSesion();
        assertNotNull(inicio);
    }
}
