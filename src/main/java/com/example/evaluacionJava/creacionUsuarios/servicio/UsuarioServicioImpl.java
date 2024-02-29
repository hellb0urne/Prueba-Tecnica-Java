package com.example.evaluacionJava.creacionUsuarios.servicio;


import com.example.evaluacionJava.creacionUsuarios.entidades.Usuario;
import com.example.evaluacionJava.creacionUsuarios.repositorio.UsuarioRepositorio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Data
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioValidacionServicio usuarioValidacionServicio;
    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        if (!usuarioValidacionServicio.validarFormatoCorreo(usuario.getEmail())) {
            throw new RuntimeException("Formato de correo incorrecto");
        }

        if (!usuarioValidacionServicio.validarFormatoClave(usuario.getPassword())) {
            throw new RuntimeException("Formato de clave incorrecto");
        }

        if (usuarioRepositorio.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya registrado");
        }

        usuario.setCreated(new Date());
        usuario.setModified(new Date());
        usuario.setLastLogin(usuario.getCreated());
        usuario.setToken(generateToken(usuario));
        usuario.setActive(true);

        usuarioRepositorio.save(usuario);
        return usuario;

    }

    private String generateToken(Usuario usuario) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 90000)) // 10 días
                .signWith(key)
                .compact();
    }


}
