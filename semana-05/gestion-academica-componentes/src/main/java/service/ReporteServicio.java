package service;

import interfaces.IReporteServicio;
import util.MongoDBUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReporteServicio implements IReporteServicio {

    private static final String COLECCION_ESTUDIANTES = "estudiantesIsaMurillo";
    private static final String COLECCION_CURSOS = "cursosIsaMurillo";
    private static final String COLECCION_MATRICULAS = "matriculasIsaMurillo";

    @Override
    public String generarReporteEstudiante(String estudianteId) {
        // Busca el estudiante filtrando en Java
        JSONArray todosEstudiantes = obtenerDocumentos(COLECCION_ESTUDIANTES);
        JSONObject estudiante = buscarPorId(todosEstudiantes, estudianteId);

        if (estudiante == null) return "Estudiante no encontrado.";

        JSONArray matriculas = obtenerDocumentos(COLECCION_MATRICULAS);

        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE ESTUDIANTE ===\n");
        reporte.append("Nombre: ").append(estudiante.optString("nombre"))
                .append(" ").append(estudiante.optString("apellido")).append("\n");
        reporte.append("Correo: ").append(estudiante.optString("correo")).append("\n");
        reporte.append("\nCursos matriculados:\n");

        int total = 0;
        for (int i = 0; i < matriculas.length(); i++) {
            JSONObject m = matriculas.getJSONObject(i);
            if (m.optString("estudianteId").equals(estudianteId)) {
                reporte.append("  - Curso ID: ").append(m.optString("cursoId"))
                        .append(" | Estado: ").append(m.optString("estado")).append("\n");
                total++;
            }
        }
        reporte.append("\nTotal de matrículas: ").append(total);
        return reporte.toString();
    }

    @Override
    public String generarReporteCurso(String cursoId) {
        // Busca el curso filtrando en Java
        JSONArray todosCursos = obtenerDocumentos(COLECCION_CURSOS);
        JSONObject curso = buscarPorId(todosCursos, cursoId);

        if (curso == null) return "Curso no encontrado.";

        JSONArray matriculas = obtenerDocumentos(COLECCION_MATRICULAS);

        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE CURSO ===\n");
        reporte.append("Curso: ").append(curso.optString("nombre")).append("\n");
        reporte.append("Descripción: ").append(curso.optString("descripcion")).append("\n");
        reporte.append("Créditos: ").append(curso.optInt("creditos")).append("\n");
        reporte.append("\nEstudiantes matriculados:\n");

        int activas = 0, canceladas = 0, finalizadas = 0;
        for (int i = 0; i < matriculas.length(); i++) {
            JSONObject m = matriculas.getJSONObject(i);
            if (m.optString("cursoId").equals(cursoId)) {
                reporte.append("  - Estudiante ID: ").append(m.optString("estudianteId"))
                        .append(" | Estado: ").append(m.optString("estado")).append("\n");
                switch (m.optString("estado")) {
                    case "ACTIVA": activas++; break;
                    case "CANCELADA": canceladas++; break;
                    case "FINALIZADA": finalizadas++; break;
                }
            }
        }
        reporte.append("\nResumen: ")
                .append(activas).append(" activas, ")
                .append(canceladas).append(" canceladas, ")
                .append(finalizadas).append(" finalizadas.");
        return reporte.toString();
    }

    @Override
    public List<String> consultarEstadisticas() {
        List<String> estadisticas = new ArrayList<>();

        JSONArray estudiantes = obtenerDocumentos(COLECCION_ESTUDIANTES);
        estadisticas.add("Total de estudiantes: " + estudiantes.length());

        JSONArray cursos = obtenerDocumentos(COLECCION_CURSOS);
        estadisticas.add("Total de cursos: " + cursos.length());

        JSONArray matriculas = obtenerDocumentos(COLECCION_MATRICULAS);
        int activas = 0, canceladas = 0, finalizadas = 0;
        for (int i = 0; i < matriculas.length(); i++) {
            switch (matriculas.getJSONObject(i).optString("estado")) {
                case "ACTIVA": activas++; break;
                case "CANCELADA": canceladas++; break;
                case "FINALIZADA": finalizadas++; break;
            }
        }
        estadisticas.add("Matrículas activas: " + activas);
        estadisticas.add("Matrículas canceladas: " + canceladas);
        estadisticas.add("Matrículas finalizadas: " + finalizadas);

        return estadisticas;
    }

    // =====================
    // MÉTODOS PRIVADOS DE APOYO
    // =====================

    // Obtiene todos los documentos de una colección
    private JSONArray obtenerDocumentos(String coleccion) {
        String respuesta = MongoDBUtil.consultarTodos(coleccion);
        JSONObject json = new JSONObject(respuesta);
        return json.getJSONArray("documentos");
    }

    // Busca un documento por su $oid dentro de un JSONArray
    private JSONObject buscarPorId(JSONArray array, String id) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject doc = array.getJSONObject(i);
            String oid = doc.getJSONObject("_id").optString("$oid");
            if (oid.equals(id)) return doc;
        }
        return null;
    }
}