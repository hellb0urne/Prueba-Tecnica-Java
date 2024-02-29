# Proyecto Evaluación Java - README

## Roberto Delgado

### Información General
- **Java:** 1.8
- **Spring Boot:** 2.5.5

### Descarga del Proyecto
Puedes descargar el proyecto desde GitHub utilizando el siguiente enlace:
[Evaluación Java en GitHub](https://github.com/hellb0urne/Prueba-Tecnica-Java.git)

### Ejecución de la Aplicación con Consola H2
1. Clona o descarga el proyecto desde GitHub.
2. Abre el proyecto en tu IDE favorito.
3. Ejecuta la aplicación.
4. Accede a [http://localhost:8080](http://localhost:8080) para verificar que la aplicación está en funcionamiento.

### Consola H2
- URL: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)
- Credenciales:
    - **Usuario:** roberto
    - **Contraseña:** 777

### Swagger
Para acceder a Swagger y probar los endpoints, abre el siguiente enlace en tu navegador después de ejecutar la aplicación:
[http://localhost:8080/swagger-ui.html/index.html](http://localhost:8080/swagger-ui.html/index.html)

### Postman o Swagger para Pruebas de Integración
Puedes realizar pruebas de integración utilizando tanto Swagger como Postman. Aquí te proporciono ejemplos para ambas herramientas:

1. **Registro de Usuario Exitoso (HTTP 201):**
   - **Swagger y Postman:**
      - Utiliza el siguiente JSON:
     ```json
     {
       "name": "NombreUsuario",
       "email": "usuario@dominio.com",
       "password": "ContraseñaSegura123",
       "phones": [
         {
           "number": "123456789",
           "citycode": "01",
           "countrycode": "+1"
         }
       ]
     }
     ```

2. **Correo ya Registrado (HTTP 409 - Conflicto):**
   - **Swagger y Postman:**
      - Utiliza el siguiente JSON:
     ```json
     {
       "name": "OtroUsuario",
       "email": "usuario@dominio.com",
       "password": "OtraContraseña456",
       "phones": [
         {
           "number": "987654321",
           "citycode": "02",
           "countrycode": "+1"
         }
       ]
     }
     ```

3. **Formato de Correo Incorrecto (HTTP 400):**
   - **Swagger y Postman:**
      - Utiliza el siguiente JSON:
     ```json
     {
       "name": "UsuarioIncorrecto",
       "email": "correoIncorrecto",
       "password": "OtraContraseña456",
       "phones": [
         {
           "number": "123456789",
           "citycode": "03",
           "countrycode": "+1"
         }
       ]
     }
     ```

4. **Formato incorrecto de Clave (HTTP 400):**
   - **Swagger y Postman:**
      - Utiliza el siguiente JSON:
     ```json
     {
       "name": "ClaveIncorrecta",
       "email": "clave@dominio.com",
       "password": "clave123",
       "phones": [
         {
           "number": "987654321",
           "citycode": "04",
           "countrycode": "+1"
         }
       ]
     }
     ```


### Diagrama UML
A continuación, se presenta el diagrama UML de la solución:

+----------------------+
| Usuario |
+----------------------+
| -id: UUID |
| -name: String |
| -email: String |
| -password: String |
| -phones: List<Telefono>|
| -created: Date |
| -modified: Date |
| -lastLogin: Date |
| -token: String |
| -isActive: boolean |
+----------------------+
| +getters/setters |
+----------------------+

+----------------------+
| Telefono |
+----------------------+
| -id: Long |
| -number: String |
| -citycode: String |
| -countrycode: String |
+----------------------+
| +getters/setters |
+----------------------+

+--------------------------+
| UsuarioRepositorio |
+--------------------------+
| -findById(UUID): Optional<Usuario> |
| -findByEmail(String): Optional<Usuario> |
| -save(Usuario): Usuario |
+--------------------------+

+--------------------------+
| UsuarioServicio |
+--------------------------+
| -usuarioRepositorio: UsuarioRepositorio |
| -usuarioValidacionServicio: UsuarioValidacionServicio |
| -jwtSecret: String |
+--------------------------+
| +registrarUsuario(Usuario): Usuario |
| -generateToken(Usuario): String |
+--------------------------+

+--------------------------+
| UsuarioValidacionServicio|
+--------------------------+
| -emailRegex: String |
| -passwordRegex: String |
+--------------------------+
| +validarFormatoCorreo(String): boolean |
| +validarFormatoClave(String): boolean |

¡Estos son los detalles necesarios para descargar, ejecutar, probar y entender tu proyecto! Si encuentras algún problema, asegúrate de revisar la salida de la consola y los informes de prueba para obtener más detalles.




