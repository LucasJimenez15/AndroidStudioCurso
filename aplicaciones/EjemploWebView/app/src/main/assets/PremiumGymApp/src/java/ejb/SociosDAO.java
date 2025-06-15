package ejb;

import entidades.Socios;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Named(value = "sdao")
public class SociosDAO {

    @PersistenceContext(unitName = "PremiumGymAppPU")
    EntityManager em;
    private Socios socio;
    private Integer dni;

    public Socios getSocio() {
        return socio;
    }

    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void agregar(Socios x) {
        em.persist(x);
    }

    public Socios modificar(Socios x) {
        return em.merge(x);
    }

    public void borrar(Socios x) {
        em.remove(em.merge(x));
    }

    public List<Socios> listarTodos() {
        Query q = em.createNamedQuery("Socios.findAll");
        return (List<Socios>) q.getResultList();
    }

    public String buscarPorDNIsocioMantener(int dni) {
         try {
        Query q = em.createNamedQuery("Socios.findByDNIsocio");
        q.setParameter("dNIsocio", dni);
        this.socio = (Socios) q.getSingleResult();
        } catch (Exception e) {
             // Manejo de error: Socio no encontrado
             return "web/vistasEntrenadores/ConsultarPerfilSocioEntrenador";
        }
        return null;
    }

    public Socios buscarPorDNIsocio(Integer dni) {
        try {
            Query q = em.createNamedQuery("Socios.findByDNIsocio");
            q.setParameter("dNIsocio", dni);
            return (Socios) q.getSingleResult();
        } catch (Exception e) {
           // Manejo de error: Socio no encontrado
           
            return null;
        }
    }
    
    

    public Socios buscarPorEmail(String emailSocio) {
        try {
            return em.createQuery("SELECT s FROM Socios s WHERE s.emailSocio = :emailSocio", Socios.class)
                    .setParameter("emailSocio", emailSocio)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No encontró al socio
        }
    }

}
