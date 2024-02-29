package com.example.evaluacionJava.creacionUsuarios.servicio;

public interface UsuarioValidacionServicio {
    boolean validarFormatoCorreo(String correo);
    boolean validarFormatoClave(String clave);
}
