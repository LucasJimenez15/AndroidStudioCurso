package ejb;

import entidades.Entrenadores;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Named(value = "entdao")
public class EntrenadoresDAO {

    @PersistenceContext(unitName = "PremiumGymAppPU")
    EntityManager em;
    private Entrenadores ent;

    public Entrenadores getEnt() {
        return ent;
    }

    public void setEnt(Entrenadores ent) {
        this.ent = ent;
    }

    public void agregar(Entrenadores x) {
        em.persist(x);
    }

    public Entrenadores modificar(Entrenadores x) {
        return em.merge(x);
    }

    public void borrar(Entrenadores x) {
        em.remove(em.merge(x));
    }

    public List<Entrenadores> listarTodos() {
        Query q = em.createNamedQuery("Entrenadores.findAll");
        return (List<Entrenadores>) q.getResultList();
    }

    public Entrenadores buscarPorDNIEntrenador(int dni) {
        try {
            Query q = em.createNamedQuery("Entrenadores.findByDNIEntrenador"); // Usa "DNIEntrenador" con "E" mayúscula
            q.setParameter("dNIEntrenador", dni);
            return (Entrenadores) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Entrenadores buscarPorEmail(String emailEntrenador) {
        try {
            return em.createQuery("SELECT e FROM Entrenadores e WHERE e.emailEntrenador = :emailEntrenador", Entrenadores.class)
                    .setParameter("emailEntrenador", emailEntrenador)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No encontró al entrenador
        }
    }
}
