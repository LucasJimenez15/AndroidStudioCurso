/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.SociosDAO;
import entidades.Socios;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */


@SessionScoped
@Named(value = "rutBean")
public class RutinaBean implements Serializable {

    public void descargarRutina(Socios servicio) throws IOException {
        
        if (servicio != null && servicio.getRutina() != null) {
            byte[] rutinaPdf = servicio.getRutina();  // Obtenemos el archivo PDF desde la base de datos

            // Configuramos la respuesta HTTP para que el archivo se descargue
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"Rutina_" + servicio.getDNIsocio() + ".pdf\"");

            // Escribir el archivo en el flujo de salida
            OutputStream output = response.getOutputStream();
            output.write(rutinaPdf);
            output.flush();

            // Finalizar el proceso de descarga
            facesContext.responseComplete();
        } else {
            // Manejar el caso en el que no se encuentra el archivo o el socio
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se encontró la rutina para este socio."));
        }
    }
    
    public void visualizarRutina(Socios servicio) throws IOException {
    // Obtén el objeto Socios desde la base de datos
  
    
    if (servicio != null && servicio.getRutina() != null) {
        byte[] rutinaPdf = servicio.getRutina();  // Obtenemos el archivo PDF desde la base de datos
        
        // Configuramos la respuesta HTTP para que el archivo se muestre en el navegador
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"Rutina_" + servicio.getDNIsocio() + ".pdf\"");
        
        // Escribir el archivo en el flujo de salida
        OutputStream output = response.getOutputStream();
        output.write(rutinaPdf);
        output.flush();
        
        // Finalizar el proceso de visualización
        facesContext.responseComplete();
    } else {
        // Manejar el caso en el que no se encuentra el archivo o el socio
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se encontró la rutina para este socio."));
    }
}
}
