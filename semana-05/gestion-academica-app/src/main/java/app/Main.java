package app;

import model.Estudiante;
import model.Curso;
import model.Matricula;
import model.Docente;
import service.EstudianteServicio;
import service.CursoServicio;
import service.MatriculaServicio;
import service.DocenteServicio;
import service.ReporteServicio;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static EstudianteServicio estudianteServicio = new EstudianteServicio();
    static CursoServicio cursoServicio = new CursoServicio();
    static DocenteServicio docenteServicio = new DocenteServicio();
    static MatriculaServicio matriculaServicio = new MatriculaServicio();
    static ReporteServicio reporteServicio = new ReporteServicio();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n========================================");
            System.out.println("   PLATAFORMA DE GESTIÓN ACADÉMICA");
            System.out.println("========================================");
            System.out.println("1. Gestionar Estudiantes");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Gestionar Docentes");
            System.out.println("4. Gestionar Matrículas");
            System.out.println("5. Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> menuEstudiantes();
                case 2 -> menuCursos();
                case 3 -> menuDocentes();
                case 4 -> menuMatriculas();
                case 5 -> menuReportes();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // =====================
    // MENÚ ESTUDIANTES
    // =====================
    static void menuEstudiantes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ESTUDIANTES ---");
            System.out.println("1. Insertar estudiante");
            System.out.println("2. Consultar todos");
            System.out.println("3. Consultar por ID");
            System.out.println("4. Actualizar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Apellido: "); String apellido = scanner.nextLine();
                    System.out.print("Correo: "); String correo = scanner.nextLine();
                    System.out.print("Teléfono: "); String telefono = scanner.nextLine();
                    Estudiante e = new Estudiante("", nombre, apellido, correo, telefono);
                    System.out.println(estudianteServicio.insertar(e));
                }
                case 2 -> {
                    List<Estudiante> lista = estudianteServicio.consultarTodos();
                    if (lista.isEmpty()) System.out.println("No hay estudiantes registrados.");
                    else lista.forEach(System.out::println);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("ID del estudiante: "); String id = scanner.nextLine();
                    System.out.println(estudianteServicio.consultarPorId(id));
                }
                case 4 -> {
                    scanner.nextLine();
                    System.out.print("ID del estudiante a actualizar: "); String id = scanner.nextLine();
                    System.out.print("Nuevo nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Nuevo apellido: "); String apellido = scanner.nextLine();
                    System.out.print("Nuevo correo: "); String correo = scanner.nextLine();
                    System.out.print("Nuevo teléfono: "); String telefono = scanner.nextLine();
                    Estudiante e = new Estudiante("", nombre, apellido, correo, telefono);
                    System.out.println(estudianteServicio.actualizar(id, e));
                }
                case 5 -> {
                    scanner.nextLine();
                    System.out.print("ID del estudiante a eliminar: "); String id = scanner.nextLine();
                    System.out.println(estudianteServicio.eliminar(id));
                }
            }
        } while (opcion != 0);
    }

    // =====================
    // MENÚ CURSOS
    // =====================
    static void menuCursos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CURSOS ---");
            System.out.println("1. Insertar curso");
            System.out.println("2. Consultar todos");
            System.out.println("3. Consultar por ID");
            System.out.println("4. Actualizar curso");
            System.out.println("5. Eliminar curso");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Nombre del curso: "); String nombre = scanner.nextLine();
                    System.out.print("Descripción: "); String descripcion = scanner.nextLine();
                    System.out.print("Créditos: "); int creditos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID del docente: "); String docenteId = scanner.nextLine();
                    Curso c = new Curso("", nombre, descripcion, creditos, docenteId);
                    System.out.println(cursoServicio.insertar(c));
                }
                case 2 -> {
                    List<Curso> lista = cursoServicio.consultarTodos();
                    if (lista.isEmpty()) System.out.println("No hay cursos registrados.");
                    else lista.forEach(System.out::println);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("ID del curso: "); String id = scanner.nextLine();
                    System.out.println(cursoServicio.consultarPorId(id));
                }
                case 4 -> {
                    scanner.nextLine();
                    System.out.print("ID del curso a actualizar: "); String id = scanner.nextLine();
                    System.out.print("Nuevo nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Nueva descripción: "); String descripcion = scanner.nextLine();
                    System.out.print("Nuevos créditos: "); int creditos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo ID de docente: "); String docenteId = scanner.nextLine();
                    Curso c = new Curso("", nombre, descripcion, creditos, docenteId);
                    System.out.println(cursoServicio.actualizar(id, c));
                }
                case 5 -> {
                    scanner.nextLine();
                    System.out.print("ID del curso a eliminar: "); String id = scanner.nextLine();
                    System.out.println(cursoServicio.eliminar(id));
                }
            }
        } while (opcion != 0);
    }

    // =====================
    // MENÚ DOCENTES
    // =====================
    static void menuDocentes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE DOCENTES ---");
            System.out.println("1. Insertar docente");
            System.out.println("2. Consultar todos");
            System.out.println("3. Consultar por ID");
            System.out.println("4. Actualizar docente");
            System.out.println("5. Eliminar docente");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Apellido: "); String apellido = scanner.nextLine();
                    System.out.print("Correo: "); String correo = scanner.nextLine();
                    System.out.print("Especialidad: "); String especialidad = scanner.nextLine();
                    Docente d = new Docente("", nombre, apellido, correo, especialidad);
                    System.out.println(docenteServicio.insertar(d));
                }
                case 2 -> {
                    List<Docente> lista = docenteServicio.consultarTodos();
                    if (lista.isEmpty()) System.out.println("No hay docentes registrados.");
                    else lista.forEach(System.out::println);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("ID del docente: "); String id = scanner.nextLine();
                    System.out.println(docenteServicio.consultarPorId(id));
                }
                case 4 -> {
                    scanner.nextLine();
                    System.out.print("ID del docente a actualizar: "); String id = scanner.nextLine();
                    System.out.print("Nuevo nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Nuevo apellido: "); String apellido = scanner.nextLine();
                    System.out.print("Nuevo correo: "); String correo = scanner.nextLine();
                    System.out.print("Nueva especialidad: "); String especialidad = scanner.nextLine();
                    Docente d = new Docente("", nombre, apellido, correo, especialidad);
                    System.out.println(docenteServicio.actualizar(id, d));
                }
                case 5 -> {
                    scanner.nextLine();
                    System.out.print("ID del docente a eliminar: "); String id = scanner.nextLine();
                    System.out.println(docenteServicio.eliminar(id));
                }
            }
        } while (opcion != 0);
    }

    // =====================
    // MENÚ MATRÍCULAS
    // =====================
    static void menuMatriculas() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE MATRÍCULAS ---");
            System.out.println("1. Insertar matrícula");
            System.out.println("2. Consultar todas");
            System.out.println("3. Consultar por ID");
            System.out.println("4. Cambiar estado");
            System.out.println("5. Eliminar matrícula");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("ID del estudiante: "); String estudianteId = scanner.nextLine();
                    System.out.print("ID del curso: "); String cursoId = scanner.nextLine();
                    System.out.print("Fecha (YYYY-MM-DD): "); String fecha = scanner.nextLine();
                    Matricula m = new Matricula("", estudianteId, cursoId, fecha, "ACTIVA");
                    System.out.println(matriculaServicio.insertar(m));
                }
                case 2 -> {
                    List<Matricula> lista = matriculaServicio.consultarTodos();
                    if (lista.isEmpty()) System.out.println("No hay matrículas registradas.");
                    else lista.forEach(System.out::println);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.print("ID de la matrícula: "); String id = scanner.nextLine();
                    System.out.println(matriculaServicio.consultarPorId(id));
                }
                case 4 -> {
                    scanner.nextLine();
                    System.out.print("ID de la matrícula: "); String id = scanner.nextLine();
                    System.out.println("Estados disponibles: ACTIVA, CANCELADA, FINALIZADA");
                    System.out.print("Nuevo estado: "); String estado = scanner.nextLine();
                    System.out.println(matriculaServicio.cambiarEstado(id, estado));
                }
                case 5 -> {
                    scanner.nextLine();
                    System.out.print("ID de la matrícula a eliminar: "); String id = scanner.nextLine();
                    System.out.println(matriculaServicio.eliminar(id));
                }
            }
        } while (opcion != 0);
    }

    // =====================
    // MENÚ REPORTES
    // =====================
    static void menuReportes() {
        int opcion;
        do {
            System.out.println("\n--- REPORTES ---");
            System.out.println("1. Reporte por estudiante");
            System.out.println("2. Reporte por curso");
            System.out.println("3. Estadísticas generales");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("ID del estudiante: "); String id = scanner.nextLine();
                    System.out.println(reporteServicio.generarReporteEstudiante(id));
                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.print("ID del curso: "); String id = scanner.nextLine();
                    System.out.println(reporteServicio.generarReporteCurso(id));
                }
                case 3 -> reporteServicio.consultarEstadisticas().forEach(System.out::println);
            }
        } while (opcion != 0);
    }
}