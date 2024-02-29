package com.example.evaluacionJava.creacionUsuarios.servicio;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
public class UsuarioValidacionServicioImpl implements UsuarioValidacionServicio {

    @Value("${usuario.email.regex}")
    private String emailRegex;

    @Value("${usuario.password.regex}")
    private String passwordRegex;

    @Override
    public boolean validarFormatoCorreo(String correo) {
        return correo.matches(emailRegex);
    }

    @Override
    public boolean validarFormatoClave(String clave) {
        return clave.matches(passwordRegex);
    }
}
