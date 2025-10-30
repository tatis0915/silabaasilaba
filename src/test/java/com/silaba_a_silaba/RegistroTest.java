package com.silaba_a_silaba;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistroTest {
    @Test
    public void testConstructor() {
        Registro registro = new Registro();
        assertNotNull(registro);
    }
}
