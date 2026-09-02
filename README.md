# LogiTrack - Sistema de Gestión y Auditoría de Bodegas

## Descripción del Proyecto
LogiTrack es un sistema backend centralizado desarrollado en Spring Boot para la empresa LogiTrack S.A. Este sistema permite administrar múltiples bodegas, gestionar productos y llevar un control estricto de los movimientos de inventario (entradas, salidas y transferencias). 

El sistema reemplaza el uso de hojas de cálculo manuales implementando:
- Trazabilidad y auditoría automática de cambios en la base de datos (EntityListeners).
- Control de accesos y seguridad mediante autenticación JWT.
- Documentación automatizada de la API Rest.

## Tecnologías Utilizadas
- **Java / Spring Boot** (Web, Data JPA, Security)
- **MySQL** (Base de datos relacional)
- **JWT (JSON Web Tokens)** (Autenticación sin estado)
- **Swagger / OpenAPI 3** (Documentación de la API)
- **Maven** (Gestor de dependencias - Wrapper v3.3.4)
- **Lombok** (Reducción de código repetitivo)

## Estructura del Proyecto
```text
src/main/java/com/explicacionD1/projectD1Campuslands/
 ├─ audit/       # Listeners para auditoría automática
 ├─ auth/        # Controladores y DTOs para Login/Registro
 ├─ config/      # Configuraciones de Seguridad, JWT y OpenAPI
 ├─ controller/  # Endpoints REST expuestos al cliente
 ├─ exception/   # Manejo global de errores (@ControllerAdvice)
 ├─ model/       # Entidades JPA (Bodega, Producto, Movimiento, Auditoria)
 ├─ repository/  # Interfaces JPA para acceso a datos
 └─ service/     # Lógica de negocio y transacciones
