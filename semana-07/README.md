# Semana 07 — ORMs, Maven, Spring y Laboratorio de Registro de Usuarios

## Teoría cubierta

Esta semana se cubrieron los fundamentos técnicos necesarios para el desarrollo
de APIs con persistencia en Java:

- **ORMs y Hibernate**: mapeo objeto-relacional, diferencias frente a JDBC puro,
  estrategias de carga de objetos (Eager vs Lazy) y JPA como estándar de
  anotaciones (`@Entity`, `@Id`, `@OneToMany`, `@ManyToOne`, etc.).
- **Maven**: gestión de dependencias y ciclo de vida de build (`mvn clean`,
  `mvn package`), estructura estándar de proyectos (`src/main/java`,
  `src/main/resources`, `src/test`) y el archivo `pom.xml` como Project Object
  Model.
- **Spring**: framework de inversión de control/inyección de dependencias,
  concepto de contenedor de beans y por qué prevalece la convención sobre la
  configuración.
- **El proceso de desarrollo de software con componentes**: fases del ciclo
  (requerimientos, especificación, adquisición, diseño, construcción,
  integración, pruebas, despliegue) bajo un enfoque iterativo-incremental.

## Laboratorio práctico: Registro de Usuarios Basado en Componentes

Aplicación de los conceptos anteriores en un sistema funcional de dos
proyectos que se comunican por HTTP:

- `registro-usuarios-api/` — backend en **Spring Boot**, con persistencia en
  **MySQL** vía Spring Data JPA (patrón Repository/DAO), validación con Bean
  Validation, y CRUD completo expuesto por `UsuarioController`. Se integra
  automáticamente con un servicio externo de envío de correos al registrar un
  usuario.
- `registro-usuarios-app/` — cliente de escritorio en **Java Swing**, con
  componentes visuales reutilizables (`PanelFormulario`, `CampoTexto`,
  `CampoPassword`, `BotonRegistrar`, `EtiquetaTitulo`) y su propio validador
  del lado cliente, consumiendo el API mediante OkHttp.
- `Documento_Registro_Usuarios_Componentes.docx` — documento de diseño con el
  análisis completo: arquitectura, mapeo de cada componente a los requisitos
  del enunciado, evidencia de pruebas e instrucciones de ejecución.

## Cómo ejecutar

1. Levantar `registro-usuarios-api` (requiere MySQL corriendo localmente con
   una base de datos `registro_usuarios`).
2. Ejecutar `registro-usuarios-app` con el API ya corriendo en el puerto 8080.

Instrucciones detalladas en el documento Word adjunto.
