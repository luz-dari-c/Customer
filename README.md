# Sistema de Gestión de Clientes

## Descripción

Aplicación web desarrollada con Spring Boot que permite registrar, consultar, editar y eliminar clientes.

## Requisitos

- Java JDK 21
- MySQL
- Maven
- IntelliJ IDEA (opcional)

## Base de datos

Crear una base de datos llamada:

`pruebaTecnica`

Configurar las credenciales en el archivo `application.properties`.

## Ejecución

1. Abrir el proyecto.
2. Ejecutar `PruebaTecnicaApplication`.
3. Abrir:

http://localhost:8080/login

## Credenciales

**Usuario:** administrador

**Contraseña:** admi123

## Funcionalidades

- Login
- Registrar clientes
- Consultar clientes
- Editar clientes
- Eliminar clientes
- Exportar clientes a Excel

## Validaciones

- Campos obligatorios.
- Teléfono solo números.
- Nombres y apellidos solo letras.
- Correo electrónico válido.
- Validación de edad según el tipo de identificación.
- Fecha de nacimiento no mayor a la fecha actual.

---

**Desarrollado por:** Luz Dari Crespo
