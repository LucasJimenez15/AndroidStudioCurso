package managedBeans;

import ejb.EntrenadoresDAO;
import ejb.SociosDAO;
import entidades.Entrenadores;
import entidades.Socios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@Named("registroBean") // Cambiado a CDI
@RequestScoped
public class RegistroBean {

    @EJB
    private SociosDAO servicio;
    @EJB
    private EntrenadoresDAO servicioent;
    private Entrenadores ent;
    private Socios soc;  
    private Part uploadedFile; // Para recibir el archivo de imagen

    // Constructor
    public RegistroBean() {
    }

    @PostConstruct
    public void init() {
        this.soc = new Socios(); // Inicializa el objeto Socios
        this.ent = new Entrenadores();
    }
    


    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    // Método para convertir el InputStream a un array de bytes
    private byte[] convertirABytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024]; // Buffer temporal

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
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

    public void setSoc(Socios soc) {
        this.soc = soc;
    }
    
    public String guardarEntrenador() {

        if (servicioent.buscarPorDNIEntrenador(ent.getDNIEntrenador()) == null) {
            if (servicioent.buscarPorEmail(ent.getEmailEntrenador()) == null) {
                servicioent.agregar(ent);
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Los datos se guardaron con exito");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "El correo ingresado ya fue registrado", "Vuelva a intentarlo");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);

            }

        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "El DNI ingresado ya fue registrado", "Vuelva a intentarlo");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        return null;
    }

       public String guardarSocio() {
        if (servicio.buscarPorDNIsocio(soc.getDNIsocio()) == null) {
            if (servicio.buscarPorEmail(soc.getEmailSocio()) == null) {
                try {

                    if (uploadedFile != null) {
                        // Convertir el archivo a un array de bytes
                        InputStream inputStream = uploadedFile.getInputStream();
                        byte[] fotoBytes = convertirABytes(inputStream);
                        soc.setFotoSocio(fotoBytes);
                    }
                    servicio.agregar(soc);
                    FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Los datos se guardaron con exito");
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                    return "/index?faces-redirect=true"; // Redirige después de guardar
                } catch (IOException e) {
                    e.printStackTrace();
                    return null; // O maneja el error como prefieras
                }
            } else {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "El correo ingresado ya fue registrado", "Vuelva a intentarlo");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);

            }

        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "El DNI ingresado ya fue registrado", "Vuelva a intentarlo");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        return null;

    }

}
