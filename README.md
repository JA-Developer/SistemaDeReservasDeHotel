# Sistema de Gestión de Hotel

## 1. Descripción General del Sistema

Este sistema simula la operación de un hotel, permitiendo gestionar usuarios, pagos, reservas, habitaciones y huéspedes. Utiliza MySQL como base de datos y está diseñado con interfaces gráficas visuales creadas mediante el editor visual de NetBeans. El sistema incluye un módulo de autenticación con contraseñas encriptadas, asegurando un acceso seguro para los usuarios.

## 2. Diseño de la Base de Datos (Modelado Relacional)

### Tabla: Descripción

| **Tabla**      | **Descripción**                                    |
|----------------|----------------------------------------------------|
| **huespedes**  | Información de los clientes del hotel.             |
| **habitaciones**| Datos de las habitaciones.                        |
| **reservas**   | Registro de las reservas realizadas por los huéspedes. |
| **pagos**      | Información sobre los pagos de las reservas.      |
| **empleados**  | Datos del personal del hotel.                     |
| **usuarios**   | Datos de los usuarios que utilizan el sistema.    |

## 3. Inicio de Sesión

### 3.1. Componentes de la Pantalla de Login

- **Campo de Nombre de Usuario**  
  *Descripción:* Es un campo donde el usuario debe ingresar su nombre de usuario. El usuario predeterminado es “Admin”.  
  *Ubicación:* En la parte superior de la ventana.  
  *Acción:* El usuario escribe su nombre de usuario. Este dato es necesario para la validación de las credenciales.

- **Campo de Contraseña**  
  *Descripción:* Es un campo donde el usuario debe ingresar su contraseña. La contraseña predeterminada es “Admin”.  
  *Ubicación:* Justo debajo del campo de nombre de usuario.  
  *Acción:* El usuario escribe su contraseña. Esta no se muestra en texto claro, sino como puntos o asteriscos.

- **Botón de Iniciar Sesión**  
  *Descripción:* Este botón permite al usuario enviar las credenciales ingresadas para validarlas en el sistema.  
  *Ubicación:* Debajo del campo de contraseña.  
  *Acción:* El usuario presiona este botón para intentar iniciar sesión.

### 3.2. Posibles Respuestas

- **Credenciales Correctas:**  
  El usuario accederá a la aplicación y la ventana de inicio de sesión se cerrará automáticamente.

- **Credenciales Incorrectas:**  
  Aparecerá un mensaje de error indicando que el nombre de usuario o la contraseña son incorrectos.

### 3.3. Consideraciones de Seguridad

- **Contraseña Cifrada:**  
  Se utiliza un algoritmo de hash MD5 para cifrar las contraseñas, garantizando que la contraseña no sea visible ni almacenada en texto claro.

## 4. Módulos del Sistema

### 4.1. Menú de Módulos

El menú principal cuenta con botones para acceder a las funcionalidades:

- **Habitaciones:** Gestión de las habitaciones del hotel (agregar, editar, eliminar).
- **Huéspedes:** Gestión de la información de los huéspedes (agregar, editar, eliminar).
- **Pagos:** Registro de pagos realizados por los huéspedes.
- **Reservas:** Gestión de las reservas (ver, editar, crear, cancelar).
- **Usuarios:** Administración de las cuentas de los usuarios del sistema (agregar, editar, eliminar).

### 4.2. Tabla: Habitaciones

- **Listar:** Consulta de todas las habitaciones con detalles como número de camas y tarifa por hora.
- **Agregar:** Registra nuevas habitaciones con información como el número de camas y tarifa por hora.
- **Editar:** Modificación de los detalles de una habitación.
- **Eliminar:** Elimina una habitación de la base de datos.

### 4.3. Tabla: Huéspedes

- **Listar:** Consulta de todos los huéspedes con nombre y DNI.
- **Agregar:** Registro de un nuevo huésped.
- **Editar:** Modificación de los datos de un huésped.
- **Eliminar:** Elimina un huésped de la base de datos.

### 4.4. Tabla: Pagos

- **Listar:** Consulta de todos los pagos realizados por los huéspedes.
- **Agregar:** Registro de nuevos pagos.
- **Editar:** Modificación de los detalles de un pago.
- **Eliminar:** Elimina un pago registrado erróneamente.

### 4.5. Tabla: Reservas

- **Listar:** Consulta de todas las reservas realizadas en el hotel.
- **Agregar:** Crea una nueva reserva.
- **Editar:** Modificación de una reserva existente.
- **Eliminar:** Elimina una reserva.

### 4.6. Tabla: Usuarios

- **Listar:** Consulta de todos los usuarios del sistema.
- **Agregar:** Registra nuevos usuarios en el sistema.
- **Editar:** Modificación de los datos de un usuario.
- **Eliminar:** Elimina un usuario del sistema.

## 5. Cómo Ejecutar el Sistema

### 5.1. Requisitos Previos

Antes de ejecutar el sistema, asegúrate de tener los siguientes requisitos:

- **Java:** JDK debe estar instalado.
- **Servidor MySQL:** Debe tener MySQL Server instalado y la base de datos `hotel_reservas` creada.
- **Librerías Java:** Asegúrate de tener las siguientes librerías:
  - HikariCP-6.2.1.jar
  - jcalendar-1.4.jar
  - mysql-connector-j-9.2.0.jar
  - slf4j-api-2.0.17.jar
  - slf4j-simple-2.0.17.jar

### 5.2. MySQL Usuario y Contraseña

- **Usuario:** root
- **Contraseña:** Vacía

### 5.3. Pasos para Ejecutar el Sistema

#### 5.3.1. Instalar MySQL y Crear la Base de Datos

- Instala MySQL y crea la base de datos `hotel_reservas` ejecutando el archivo SQL proporcionado.

#### 5.3.2. Configurar el Proyecto

- Asegúrate de que el proyecto esté correctamente configurado con las librerías necesarias.

#### 5.3.3. Modificar la Configuración de la Base de Datos

- Revisa el archivo de configuración de la base de datos (`ConnectionDB.class`) y verifica que la conexión JDBC y las credenciales coincidan con tu instalación local de MySQL.

#### 5.3.4. Compilar el Proyecto

- Compila el proyecto utilizando el IDE NetBeans.

#### 5.3.5. Ejecutar el Sistema

- Una vez compilado, ejecuta el sistema. El punto de entrada es el `JFrameLogin`, donde podrás iniciar sesión.

#### 5.3.6. Inicio de Sesión en el Sistema

- Inicia sesión con el usuario y contraseña predeterminados:  
  - **Usuario:** Admin  
  - **Contraseña:** Admin