package managedBeans;

import ejb.AdminDAO;
import ejb.EntrenadoresDAO;
import ejb.SociosDAO;
import entidades.Admin;
import entidades.Entrenadores;
import entidades.Socios;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class InicioSesionBean{

    private String email;
    private String contraseña;
    private String tipoUsuario = "socio"; // Para manejar el tipo de usuario (socio, entrenador, admin.)

    @EJB
    private SociosDAO socioService;
    @EJB
    private EntrenadoresDAO entrenadorService;
    @EJB
    private AdminDAO adminService;

    public String iniciarSesion() {
        if ("socio".equals(tipoUsuario)) {
            Socios socio = socioService.buscarPorEmail(email);

            if (socio != null && socio.getContrasenaSocio().equals(contraseña)) {
                // Iniciar sesión exitoso
                socioService.setSocio(socio);
                return "/web/vistasSocio/InicioSocio?faces-redirect=true"; // Redirige a la página principal del socio m
            } else {
                // Error en la autenticación
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email o contraseña incorrectos", null));
                return null; // Mantiene en la misma página
            }
        } else if ("entrenador".equals(tipoUsuario)) {
            Entrenadores entrenador = entrenadorService.buscarPorEmail(email);

            if (entrenador != null && entrenador.getContrasenaEntrenador().equals(contraseña)) {
                // Iniciar sesión exitoso
                entrenadorService.setEnt(entrenador);
                return "/web/vistasEntrenadores/InicioEntrenador?faces-redirect=true"; // Redirige a la página principal del socio m
            } else {
                // Error en la autenticación
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email o contraseña incorrectos", null));
                return null; // Mantiene en la misma página
            }
        } else if ("administrador".equals(tipoUsuario)) {
            Admin adm = adminService.buscarPorEmail(email);

            if (adm != null && adm.getContrasena().equals(contraseña)) {
                // Iniciar sesión exitoso
                return "/web/vistasAdmin/InicioAdmin?faces-redirect=true"; // Redirige a la página principal del socio m
            } else {
                // Error en la autenticación
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email o contraseña incorrectos", null));
                return null; // Mantiene en la misma página
            }

        }
        return null;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
