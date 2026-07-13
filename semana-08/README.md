# Semana 08 — Sistema de Gestión de Menús, Acciones y Roles

Laboratorio de consumo de un API REST externo (persistencia en MongoDB) usando
Java, Maven y el patrón **MVC + DAO**. La aplicación administra tres entidades
—Menús, Acciones y Roles— sin conectarse en ningún momento directamente a la
base de datos: toda la persistencia pasa por el API REST del profesor.

## Contenido de esta carpeta

| Archivo / carpeta | Descripción |
|---|---|
| [`laboratorio-seguridad/`](./laboratorio-seguridad) | Proyecto Java (Maven) con el código fuente completo |
| [`Diagrama_de_clases.png`](./Diagrama_de_clases.png) | Diagrama de clases de la arquitectura |
| [`Documento_Arquitectura_y_Evidencias.pdf`](./Documento_Arquitectura_y_Evidencias.pdf) | Explicación de la arquitectura + evidencias de funcionamiento (capturas contra el API real) |

## Arquitectura

```
laboratorioseguridad
├── Main.java              → punto de entrada
├── model/                 → Menu, Accion, Rol
├── dao/                   → acceso al API REST (ApiRestClient, ApiResponse, MongoJsonUtil, *DAO)
├── controller/             → validaciones y lógica de negocio
└── view/                  → ConsoleView, menú interactivo por consola
```

Las tres entidades siguen exactamente el mismo patrón de capas; solo cambian
los campos propios de cada una. El diagrama de clases y el documento de
arquitectura detallan el porqué de cada capa.

## Tecnologías

- Java 21 (`java.net.http.HttpClient`)
- Maven
- Gson
- API REST externo del profesor sobre MongoDB (`paginas-web-cr.com`)

## Cómo ejecutar

```bash
cd laboratorio-seguridad
mvn clean package
java -jar target/laboratorio-seguridad.jar
```

También se puede correr directamente `Main.java` desde IntelliJ.

## Funcionalidad comprobada

- ✅ Inserción de registros
- ✅ Consulta general
- ✅ Consulta por filtros
- ✅ Actualización
- ✅ Eliminación
- ✅ Manejo de errores cuando el API no responde
- ✅ Manejo de respuestas vacías
- ✅ Validación de datos antes de enviar la petición

Todas probadas contra el API real; evidencia completa en el documento PDF de
esta carpeta.

> **Nota:** las colecciones (`menus`, `acciones`, `roles`) son compartidas
> entre todos los estudiantes del curso, ya que usan el mismo API/MongoDB del
> profesor.
