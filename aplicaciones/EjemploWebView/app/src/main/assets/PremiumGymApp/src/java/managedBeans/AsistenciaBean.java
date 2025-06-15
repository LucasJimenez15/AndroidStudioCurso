package managedBeans;

import ejb.AsistenciaDAO;
import ejb.SociosDAO;
import entidades.Asistencia;
import entidades.Socios;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

@Named(value = "asistenciaBean")
@ViewScoped
public class AsistenciaBean implements Serializable {

    @EJB
    private AsistenciaDAO asiServicio;
    private LocalDate fechaActual;
    private boolean botonDesactivado;
    private Socios socio;  // Representa el socio actual
    private List<Asistencia> listaasist;
    private Date fechainicio;
    private Date fechafin;
    private int dni;
    @EJB
    private SociosDAO sdao;

    @PostConstruct
    public void init() {
        fechaActual = LocalDate.now();  // Obtiene la fecha actual

        if (socio != null) {
            // Verifica si ya existe una asistencia para el socio en el día de hoy
            boolean asistenciaHoy = asiServicio.verificarAsistenciaHoy(socio);
            botonDesactivado = asistenciaHoy;  // Desactiva el botón si ya existe asistencia para hoy
        } else {
            botonDesactivado = false;  // Si no hay socio, el botón no está desactivado
        }
    }

    public List<Asistencia> getListaasist() {
        return listaasist;
    }

    public void setListaasist(List<Asistencia> listaasist) {
        this.listaasist = listaasist;
    }

    // Método para registrar asistencia
    public void registrarAsistencia(Socios nuevoSocio) {
        this.socio = nuevoSocio;  // Actualiza el socio actual

        // Verificar si ya existe una asistencia para el socio en la fecha actual
        boolean yaRegistrado = asiServicio.verificarAsistenciaHoy(socio);

        if (!yaRegistrado) {  // Si no existe una asistencia para el día de hoy
            // Buscar si ya está registrado el socio con el DNI en la base de datos
            Socios socioExistente = sdao.buscarPorDNIsocio(socio.getDNIsocio());

            if (socioExistente != null) {
                // El socio ya existe, registrar la asistencia
                Date fechaAsistencia = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
                asiServicio.registrarAsistencia(socio, fechaAsistencia);
                botonDesactivado = true;  // Desactiva el botón después de registrar la asistencia
            } else {
                // Si el socio no existe en la base de datos, muestra un mensaje de error o realiza alguna acción
                System.out.println("El socio no está registrado en la base de datos.");
            }
        }
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    
    

    public String consultarasistencia() {
        listaasist = asiServicio.verasistencia(dni, fechainicio, fechafin);
        return null;
    }

    public boolean isBotonDesactivado() {
        return botonDesactivado;
    }

    public void setBotonDesactivado(boolean botonDesactivado) {
        this.botonDesactivado = botonDesactivado;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }
}
