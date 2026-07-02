package interfaces;

import java.util.List;

public interface IReporteServicio {
    String generarReporteEstudiante(String estudianteId);
    String generarReporteCurso(String cursoId);
    List<String> consultarEstadisticas();
}