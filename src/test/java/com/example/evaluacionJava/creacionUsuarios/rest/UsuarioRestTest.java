package com.example.evaluacionJava.creacionUsuarios.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.evaluacionJava.creacionUsuarios.entidades.Usuario;
import com.example.evaluacionJava.creacionUsuarios.servicio.UsuarioServicio;

class UsuarioRestTest {

    @Mock
    private UsuarioServicio usuarioServicio;

    @InjectMocks
    private UsuarioRest usuarioRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuarioExitoso() {
        // Configurar el comportamiento esperado para el servicio
        when(usuarioServicio.registrarUsuario(any())).thenReturn(new Usuario());

        // Llamar al método que se está probando
        ResponseEntity<Object> response = usuarioRest.registrarUsuario(new Usuario());

        // Verificar que se devuelve un código 201
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Verificar que el cuerpo de la respuesta no es nulo
        assertNotNull(response.getBody());
    }

    @Test
    void testRegistrarUsuarioConError() {
        // Configurar el comportamiento esperado para el servicio
        when(usuarioServicio.registrarUsuario(any())).thenThrow(new RuntimeException("Error al registrar usuario"));

        // Llamar al método que se está probando
        ResponseEntity<Object> response = usuarioRest.registrarUsuario(new Usuario());

        // Verificar que se devuelve un código 500
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        // Verificar que el cuerpo de la respuesta contiene el mensaje de error
        assertEquals("Error al registrar usuario: Error al registrar usuario", response.getBody());
    }
}