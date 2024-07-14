### Foro Hub API

Foro Hub API proporciona servicios para la plataforma For Hub, permitiendo la gestión de tópicos entre usuarios.
![image](https://github.com/user-attachments/assets/c5ae7acc-0511-42e0-85ac-c0b86d264194)
### Tecnologías Utilizadas

- **Java**
- **MySQL** como base de datos
- **Autenticación** con JWT y BCrypt
- **Swagger** para documentación de la API
- **Configuración** mediante archivos .yml
- **Insomnia o Postman** para probar la API

### URLs Importantes

- **Endpoint Autenticación:** `http://localhost:8080/login`
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html#/`
- **Endpoint Base:** `http://localhost:8080/topico`

### Configuración

#### Requisitos Previos

Asegúrate de tener instalados:

- **Java Development Kit (JDK) 21** o superior
- **MySQL Server**
- **IDE** (IntelliJ IDEA recomendado)
- **Insomnia o Postman**

#### Configuración de la Base de Datos

1. Crea una base de datos MySQL llamada `forohubdb`.
2. Configura las credenciales de acceso en `application.yml`.

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/forohub
    username: root
    password: tu_password
```

#### Ejecución del Proyecto

1. Importa el proyecto en tu IDE.
2. Ejecuta la aplicación desde tu IDE o mediante Maven.

### Acceso a la API

Para ver la documentación detallada de la API, accede a Swagger UI:

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html#/`

### Autenticación y Seguridad

La API utiliza **JWT** para la autenticación y **BCrypt** para el cifrado de contraseñas. Por defecto, puedes autenticarte con el email: `alfredo@gmail.com` y la contraseña: `123456`.

Asegúrate de incluir el token JWT en las cabeceras de tus peticiones protegidas.
