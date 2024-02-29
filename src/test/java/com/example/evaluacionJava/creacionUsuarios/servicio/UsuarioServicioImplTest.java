package com.example.evaluacionJava.creacionUsuarios.servicio;

import com.example.evaluacionJava.creacionUsuarios.entidades.Usuario;
import com.example.evaluacionJava.creacionUsuarios.repositorio.UsuarioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServicioImplTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private UsuarioValidacionServicio usuarioValidacionServicio;

    @InjectMocks
    private UsuarioServicioImpl usuarioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuarioExitoso() {
        // Configurar el comportamiento esperado para las validaciones
        when(usuarioValidacionServicio.validarFormatoCorreo(any())).thenReturn(true);
        when(usuarioValidacionServicio.validarFormatoClave(any())).thenReturn(true);

        // Configurar el comportamiento esperado para el repositorio
        when(usuarioRepositorio.findByEmail(any())).thenReturn(Optional.empty());
        when(usuarioRepositorio.save(any())).thenReturn(null);

        // Crear un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        // Llamar al método que se está probando
        Usuario result = usuarioServicio.registrarUsuario(usuario);

        // Verificar que el método save del repositorio se llamó con el usuario correcto
        verify(usuarioRepositorio, times(1)).save(eq(usuario));

        // Verificar que el token se generó
        assertNotNull(result.getToken());
    }

    @Test
    void testRegistrarUsuarioConCorreoInvalido() {
        // Configurar el comportamiento esperado para las validaciones
        when(usuarioValidacionServicio.validarFormatoCorreo(any())).thenReturn(false);

        // Crear un usuario de prueba con correo inválido
        Usuario usuario = new Usuario();
        usuario.setEmail("correoInvalido");
        usuario.setPassword("password");

        // Verificar que se lanza una excepción con el mensaje correcto
        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioServicio.registrarUsuario(usuario));
        assertEquals("Formato de correo incorrecto", exception.getMessage());

        // Verificar que el método save del repositorio no se llamó
        verify(usuarioRepositorio, never()).save(any());
    }

    @Test
    void testRegistrarUsuarioConClaveInvalida() {
        // Configurar el comportamiento esperado para las validaciones
        when(usuarioValidacionServicio.validarFormatoCorreo(any())).thenReturn(true);
        when(usuarioValidacionServicio.validarFormatoClave(any())).thenReturn(false);

        // Crear un usuario de prueba con clave inválida
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("claveInvalida");

        // Verificar que se lanza una excepción con el mensaje correcto
        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioServicio.registrarUsuario(usuario));
        assertEquals("Formato de clave incorrecto", exception.getMessage());

        // Verificar que el método save del repositorio no se llamó
        verify(usuarioRepositorio, never()).save(any());
    }

    @Test
    void testRegistrarUsuarioConCorreoExistente() {
        // Configurar el comportamiento esperado para las validaciones
        when(usuarioValidacionServicio.validarFormatoCorreo(any())).thenReturn(true);
        when(usuarioValidacionServicio.validarFormatoClave(any())).thenReturn(true);

        // Configurar el comportamiento esperado para el repositorio
        when(usuarioRepositorio.findByEmail(any())).thenReturn(Optional.of(new Usuario()));

        // Crear un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");

        // Verificar que se lanza una excepción con el mensaje correcto
        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioServicio.registrarUsuario(usuario));
        assertEquals("El correo ya registrado", exception.getMessage());

        // Verificar que el método save del repositorio no se llamó
        verify(usuarioRepositorio, never()).save(any());
    }

}