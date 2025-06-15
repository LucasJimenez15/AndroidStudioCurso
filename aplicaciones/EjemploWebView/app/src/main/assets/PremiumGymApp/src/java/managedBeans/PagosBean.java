package managedBeans;

import ejb.PagosDAO;
import ejb.SociosDAO;
import entidades.Pagos;
import entidades.Socios;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.*;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "pagBean")
@ViewScoped
public class PagosBean implements Serializable {

    @EJB
    private SociosDAO serviciosocio;
    @EJB
    private PagosDAO serviciopag;
    private int dni;
    private Pagos pag;
    private int idBorrarPago;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Pagos> listapago;

    public PagosBean() {
        // Inicializar el objeto pag para evitar null pointer exception
        this.pag = new Pagos();
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Pagos getPag() {
        return pag;
    }

    public void setPag(Pagos pag) {
        this.pag = pag;
    }

    public int getIdBorrarPago() {
        return idBorrarPago;
    }

    public void setIdBorrarPago(int idBorrarPago) {
        this.idBorrarPago = idBorrarPago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Pagos> getListapago() {
        return listapago;
    }

    public void setListapago(List<Pagos> listapago) {
        this.listapago = listapago;
    }

    public String verPagos(int dni) {
        this.dni = dni;
        this.listapago = serviciopag.filtrarPagos(fechaInicio, fechaFin, dni);
        return null;
    }

    public String registrarPago() {
        try {
            Socios socio = serviciosocio.buscarPorDNIsocio(dni);
        if (socio != null) {
            pag.setDNIsocio(socio);
            serviciopag.agregar(pag);  // Persistir el pago en la base de datos
            return "/web/vistasAdmin/InicioAdmin";  // Página de confirmación o redirección
        }
        } catch (Exception e) {
            // Manejo de error: Socio no encontrado
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No se encontró un socio con el DNI proporcionado.", "a"));
            return null;
        }
        return null;
    }

    public String borrarPago(Pagos pag) {
        this.pag = pag;
        serviciopag.borrar(pag);
        return "/web/vistasAdmin/InicioAdmin";
    }

}
