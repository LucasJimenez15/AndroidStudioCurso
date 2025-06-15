package managedBeans;

import ejb.EntrenadoresDAO;
import ejb.SociosDAO;
import entidades.Entrenadores;
import entidades.Socios;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "modificaBeanAdmin")
@ViewScoped
public class ModificaBeanAdmin implements Serializable {

    @EJB
    private SociosDAO servicio;

    @EJB
    private EntrenadoresDAO servicioent;

    private Entrenadores ent;
    private Socios soc;
    private Integer dni;
    private Integer dnient;

    public void asignarSocio() {
        this.soc = servicio.buscarPorDNIsocio(dni);
    }

    public void asignarEntrenador() {
        this.ent = servicioent.buscarPorDNIEntrenador(dnient);
    }

    @PostConstruct
    public void ModificaBean() {
        this.soc = new Socios();
        this.ent = new Entrenadores();
    }

    public Integer getDnient() {
        return dnient;
    }

    public void setDnient(Integer dnient) {
        this.dnient = dnient;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Entrenadores getEnt() {
        return ent;
    }

    public void setEnt(Entrenadores ent) {
        this.ent = ent;
    }

    public Socios getSoc() {
        return soc;
    }

    public String eliminarEntrenadorAdmin() {
        if (ent != null) {
            servicioent.borrar(ent);
        }
        return "/web/vistasAdmin/InicioAdmin";
    }

    public String actualizarEntrenadorAdmin() {
        if (this.ent != null) {
            this.ent = servicioent.modificar(ent);
        }
        return "/web/vistasAdmin/InicioAdmin";
    }

    public String eliminarSocioAdmin() {
        if (this.soc != null) {
            servicio.borrar(soc);
        }
        return "/web/vistasAdmin/InicioAdmin";
    }

    public String actualizarSocioAdmin() {
        if (this.soc != null) {
            this.soc = servicio.modificar(soc);
        }
        return "/web/vistasAdmin/InicioAdmin";
    }

}
