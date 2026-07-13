# Sistema de Gestión de Menús, Acciones y Roles — Arquitectura MVC

## Objetivo
Administrar la estructura de seguridad de un sistema (Menús, Acciones y Roles),
persistiendo toda la información a través de un API REST externo que a su vez
guarda los datos en MongoDB. La aplicación **no** se conecta directamente a
MongoDB en ningún punto.

## Estructura del proyecto

```
laboratorioseguridad
├── Main.java              → punto de entrada
├── model/                 → Menu, Accion, Rol (POJOs, reflejan las colecciones de Mongo)
├── dao/                   → acceso al API REST, un DAO por entidad + utilidades comunes
├── controller/             → lógica de negocio y validaciones
└── view/                  → ConsoleView, menú interactivo por consola
```

## Capas y responsabilidades

**Modelo (`model`)**
Clases `Menu`, `Accion` y `Rol`: representan los datos de cada colección.
No tienen ninguna lógica, solo atributos, getters/setters.

**DAO (`dao`)**
- `ApiRestClient`: cliente HTTP genérico (usa `java.net.http.HttpClient`, nativo
  de Java 21) con métodos `get/post/put/delete`. Centraliza el manejo de errores:
  API no disponible, timeout, 404, 500, JSON inválido.
- `ApiResponse`: encapsula el resultado de cada llamada (éxito/error, código de
  estado, cuerpo o mensaje de error).
- `MongoJsonUtil`: resuelve las particularidades del API del profesor —las
  respuestas de consulta vienen envueltas en `"documentos"`, y los ids de Mongo
  llegan anidados como `{"$oid": "..."}` en vez de un string simple.
- `MenuDAO`, `AccionDAO`, `RolDAO`: cada uno construye las peticiones JSON
  (`coleccion`, `datos`, `filtro`) para su colección y las envía por medio de
  `ApiRestClient`. Ningún DAO conoce reglas de negocio; solo consume el API.

**Controlador (`controller`)**
`MenuController`, `AccionController`, `RolController`: validan los datos que
entran desde la vista (campos vacíos, id obligatorio para actualizar/eliminar),
llaman al DAO correspondiente y traducen la respuesta a un mensaje legible para
el usuario.

**Vista (`view`)**
`ConsoleView`: aplicación de consola con el menú principal:

```
===== SISTEMA DE SEGURIDAD =====
1. Administrar Menús
2. Administrar Acciones
3. Administrar Roles
4. Salir
```

Cada submenú ofrece Insertar / Consultar / Buscar / Actualizar / Eliminar /
Regresar. La vista solo lee entradas e imprime resultados; no contiene lógica
de negocio ni llamadas directas al API.

## Manejo de errores
`ApiRestClient` distingue y reporta:
- API no disponible / error de conexión
- Tiempo de espera agotado
- HTTP 404 (recurso no encontrado)
- HTTP 500 (error interno del servidor)
- JSON inválido en la respuesta

Los controladores agregan validación de datos de entrada (campos vacíos, id
obligatorio) antes de siquiera llamar al API.

## Tecnologías utilizadas
- Java 21 (`java.net.http.HttpClient` para las peticiones REST)
- Maven (gestión de dependencias y empaquetado con `maven-shade-plugin`)
- Gson (serialización/deserialización de JSON)
- API REST del profesor sobre MongoDB (`https://paginas-web-cr.com/Api/apis/mongodb.php`)

## Cómo ejecutar
```
mvn clean package
java -jar target/laboratorio-seguridad.jar
```
(También se puede correr directamente `Main.java` desde IntelliJ.)

## Nota sobre el formato de las respuestas del API
El parseo en `MongoJsonUtil` asume que las consultas devuelven
`{"documentos": [...]}` y que los ids vienen como `{"$oid": "..."}`, según lo ya
observado en prácticas anteriores. Si al probar contra el API real alguna
colección responde con un formato distinto, ajustar `MongoJsonUtil` es
suficiente — el resto de las capas no cambia.
