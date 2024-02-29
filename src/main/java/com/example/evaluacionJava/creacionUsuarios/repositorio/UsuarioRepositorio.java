package com.example.evaluacionJava.creacionUsuarios.repositorio;

import com.example.evaluacionJava.creacionUsuarios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
}
