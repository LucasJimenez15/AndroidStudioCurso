package ejb;

import entidades.Asistencia;
import entidades.Socios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

@Stateless
public class AsistenciaDAO {
    
    @PersistenceContext(unitName = "PremiumGymAppPU")
    private EntityManager em;

    // Método para registrar asistencia
    public void registrarAsistencia(Socios socio, Date fechaAsistencia) {
        Asistencia asistencia = new Asistencia();
        asistencia.setDNIsocio(socio);
        asistencia.setFechaAsistencia(fechaAsistencia);
        em.persist(asistencia);
    }

    // Verificar si el socio ya tiene asistencia registrada para hoy
    public boolean verificarAsistenciaHoy(Socios socio) {

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Convertir a tipo Date para usarlo en la consulta
        Date fechaActualDate = java.sql.Date.valueOf(fechaActual);

        // Consulta JPQL para verificar si existe una asistencia para el socio en el día actual
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(a) FROM Asistencia a WHERE a.dNIsocio = :socio AND FUNCTION('DATE', a.fechaAsistencia) = :fecha", Long.class)
                    .setParameter("socio", socio)
                    .setParameter("fecha", fechaActualDate) // Solo la fecha (sin la parte de la hora)
                    .getSingleResult();

            return count > 0;  // Si count > 0, significa que ya tiene asistencia registrada
        } catch (Exception e) {
            return false;  // Si ocurre algún error o no hay registros, retorna falso
        }
    }
    
    public List<Asistencia> verasistencia(int dni,Date fechainicio,Date fechafin){
        Query q = em.createQuery("SELECT a FROM Asistencia a WHERE a.fechaAsistencia >= :fechaInicio AND a.fechaAsistencia <= :fechaFin AND a.dNIsocio.dNIsocio = :dni", Asistencia.class)
                .setParameter("fechaInicio", fechainicio)
                .setParameter("fechaFin", fechafin)
                .setParameter("dni", dni);
        List<Asistencia> listaasist = q.getResultList();
        return listaasist;
    }
}
