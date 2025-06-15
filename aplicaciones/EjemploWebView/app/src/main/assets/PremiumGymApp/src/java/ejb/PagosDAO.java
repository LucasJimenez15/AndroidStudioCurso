package ejb;

import entidades.Pagos;
import entidades.Socios;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Named(value = "pagdao")
public class PagosDAO {

    @PersistenceContext(unitName = "PremiumGymAppPU")
    EntityManager em;
    private Pagos pag;
    private List<Pagos> listapag;

    public Pagos getPag() {
        return pag;
    }

    public void setPag(Pagos pag) {
        this.pag = pag;
    }

    public List<Pagos> filtrarPagos(Date fechaInicio, Date fechaFin, Integer dni) {
        Query q = em.createQuery("SELECT p FROM Pagos p WHERE p.fechaPago >= :fechaInicio AND p.fechaPago <= :fechaFin AND p.dNIsocio.dNIsocio = :dni", Pagos.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .setParameter("dni", dni);
        List<Pagos> listapag = q.getResultList();
        return listapag;
    }

    public void agregar(Pagos x) {
        em.persist(x);
    }

    public Pagos modificar(Pagos x) {
        return em.merge(x);
    }

    public void borrar(Pagos x) {
        em.remove(em.merge(x));

    }
}
