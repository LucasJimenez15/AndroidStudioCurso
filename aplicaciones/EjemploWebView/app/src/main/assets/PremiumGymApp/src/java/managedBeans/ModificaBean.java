
package managedBeans;

import ejb.SociosDAO;
import entidades.Socios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Part;

@Named(value = "modificaBean")
@SessionScoped
public class ModificaBean implements Serializable {
    
   
    @EJB
    private SociosDAO servicio;
    private Socios soc;
    private Part pdfFile;
    private Integer dni;
    private Part uploadedFile; // Para recibir el archivo de imagen
    
     public String subirPDF() {
        if (pdfFile != null) {
            try (InputStream input = pdfFile.getInputStream();
                 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                
                byte[] data = new byte[1024];
                int bytesRead;
                
                // Lee los datos en bloques de 1 KB
                while ((bytesRead = input.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                
                buffer.flush();
                byte[] pdfData = buffer.toByteArray();

                Socios socio = servicio.buscarPorDNIsocio(servicio.getSocio().getDNIsocio()); // Ajusta según tu lógica de búsqueda
                if (socio != null) {
                    socio.setRutina(pdfData);
                    servicio.modificar(socio); // Actualiza el socio en la base de datos
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/web/vistasEntrenadores/VerPerfilEntrenador?faces-redirect=true ";
    }

    // Getter y Setter para pdfFile
    public Part getPdfFile() {
        return pdfFile;
    }
    
    public void asignarSocio(){
        soc = servicio.buscarPorDNIsocio(dni);
    }
    
    public void setPdfFile(Part pdfFile) {
        this.pdfFile = pdfFile;
    }

    @PostConstruct
    public void ModificaBean(){
    this.soc = new Socios();
}
    
    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Socios getSoc() {
        return soc;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void setSDAO(){
        servicio.setSocio(soc);
    }

    public void buscarSocios(Integer dni) {
        this.soc = this.servicio.buscarPorDNIsocio(dni);
    }

    public String actualizarSocio() {
        
         try {
            if (uploadedFile != null) {
                // Convertir el archivo a un array de bytes
                InputStream inputStream = uploadedFile.getInputStream();
                byte[] fotoBytes = convertirABytes(inputStream);
                soc.setFotoSocio(fotoBytes);
            }
            this.servicio.modificar(this.soc);
            setSDAO();
            return "/web/vistasSocio/VerPerfilSocio?faces-redirect=true ";
             // Redirige después de guardar
        } catch (IOException e) {
            e.printStackTrace();
            return null; // O maneja el error como prefieras
        }
        
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
    
    
    public String eliminarCuenta(Integer dni){
        servicio.borrar(servicio.buscarPorDNIsocio(dni));
        return "/index?faces-redirect=true ";
    }
    
}
