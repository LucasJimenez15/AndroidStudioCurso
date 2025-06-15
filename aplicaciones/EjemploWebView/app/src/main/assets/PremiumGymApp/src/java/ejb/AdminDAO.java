/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entidades.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
@Stateless
@Named(value="admindao")
public class AdminDAO {

    @PersistenceContext(unitName = "PremiumGymAppPU")
    EntityManager em;

    public void agregar(Admin x) {
        em.persist(x);
    }

    public Admin modificar(Admin x) {
        return em.merge(x);
    }

    public void borrar(Admin x) {
        em.remove(em.merge(x));
    }

    public List<Admin> listarTodos() {
        Query q = em.createNamedQuery("Admin.findAll");
        return (List<Admin>) q.getResultList();
    }
    
     public Admin buscarPorEmail(String emailAdmin) {
        try {
            return em.createQuery("SELECT a FROM Admin a WHERE a.email = :emailAdmin", Admin.class)
                    .setParameter("emailAdmin", emailAdmin)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No encontró al admin
        }
    }
}
