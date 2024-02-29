package com.example.evaluacionJava.creacionUsuarios.rest;

import com.example.evaluacionJava.creacionUsuarios.entidades.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.evaluacionJava.creacionUsuarios.servicio.UsuarioServicio;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsuarioRest {

    private final UsuarioServicio usuarioServicio;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario registrado = usuarioServicio.registrarUsuario(usuario);
            return new ResponseEntity<>(registrado, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().equals("Formato de correo incorrecto") || e.getMessage().equals("Formato de clave incorrecto")) {
                return new ResponseEntity<>("Error al registrar usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (e.getMessage().equals("El correo ya registrado")) {
                return new ResponseEntity<>("Error al registrar usuario: " + e.getMessage(), HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>("Error al registrar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
