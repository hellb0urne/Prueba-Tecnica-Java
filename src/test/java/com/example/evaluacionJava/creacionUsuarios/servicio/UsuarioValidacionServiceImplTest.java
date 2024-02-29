package com.example.evaluacionJava.creacionUsuarios.servicio;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioValidacionServiceImplTest {

    @InjectMocks
    private UsuarioValidacionServicioImpl usuarioValidacionService;

    @Mock
    private UsuarioValidacionServicioImpl usuarioValidacionServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioValidacionService.setEmailRegex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        usuarioValidacionService.setPasswordRegex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }

    @Test
    public void testValidarFormatoCorreo_Valido() {
        assertTrue(usuarioValidacionService.validarFormatoCorreo("usuario@example.com"));
    }

    @Test
    public void testValidarFormatoCorreo_Invalido() {
        assertFalse(usuarioValidacionService.validarFormatoCorreo("usuario.invalido@com"));
    }

    @Test
    public void testValidarFormatoClave_Valido() {
        assertTrue(usuarioValidacionService.validarFormatoClave("Abcd1234"));
    }

    @Test
    public void testValidarFormatoClave_Invalido() {
        assertFalse(usuarioValidacionService.validarFormatoClave("claveInvalida"));
    }

    @Test
    public void testValidarFormatoCorreo_CasoLimite() {
        // Prueba con cadena vacía
        assertFalse(usuarioValidacionService.validarFormatoCorreo(""));
        // Agrega más casos límite según sea necesario
    }

    @Test
    public void testValidarFormatoClave_CasoLimite() {
        // Prueba con cadena vacía
        assertFalse(usuarioValidacionService.validarFormatoClave(""));
        // Agrega más casos límite según sea necesario
    }


}
