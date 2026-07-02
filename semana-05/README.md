# Semana 05 — Caso de Estudio 1: Plataforma de Componentes para Gestión Académica Distribuida

Diseño e implementación de una plataforma basada en componentes reutilizables para
la gestión académica de una institución educativa (estudiantes, cursos, matrículas,
docentes y reportes), aplicando principios de diseño de componentes, publicación de
interfaces y análisis de granularidad.

## Estructura

- `gestion-academica-componentes/` — proyecto **proveedor**. Contiene el modelo de
  datos, las interfaces publicadas y los servicios que implementan el CRUD de cada
  entidad, consumiendo la API de MongoDB del curso. Se empaqueta como un `.jar`
  reutilizable mediante `maven-shade-plugin`.
- `gestion-academica-app/` — proyecto **consumidor**. Menú interactivo por consola
  que importa el `.jar` anterior y ejercita sus servicios.
- `Caso de Estudio 1 - Gestión Académica.pdf` — documento con el análisis del
  problema, diagrama de clases, interfaces publicadas, análisis de granularidad y
  conclusiones.
- `Diagrama de Componentes.png` — diagrama de la arquitectura completa (componentes
  consumidor/proveedor, interfaces publicadas y dependencias hacia MongoDB).

## Cómo compilar y ejecutar

1. Compilar el proveedor y generar el `.jar`:

   cd gestion-academica-componentes
   mvn clean package
2. El `.jar` resultante queda en `target/componentes-1.0.jar`.
3. Abrir `gestion-academica-app` y confirmar que el `.jar` esté agregado como
   dependencia del proyecto.
4. Ejecutar `Main.java` para interactuar con el menú.
