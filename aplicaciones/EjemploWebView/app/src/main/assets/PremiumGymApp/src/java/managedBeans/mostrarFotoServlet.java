package managedBeans;

import ejb.SociosDAO;
import entidades.Socios;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/mostrarFoto")
public class mostrarFotoServlet extends HttpServlet {

    @Inject
    private SociosDAO sociosDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        if (dni != null) {
            try {
                Integer dniInt = Integer.valueOf(dni);
                Socios socio = sociosDAO.buscarPorDNIsocio(dniInt);

                if (socio != null && socio.getFotoSocio() != null) {
                    String contentType = getContentType(socio.getFotoSocio());
                    response.setContentType(contentType);
                    OutputStream out = response.getOutputStream();
                    out.write(socio.getFotoSocio());
                    out.flush();
                    out.close();
                } else {
                    // Manejar el caso donde no hay foto
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DNI inválido.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "DNI no proporcionado.");
        }
    }

    // Método para determinar el tipo de contenido basado en la extensión
    private String getContentType(byte[] imageBytes) {

        if (imageBytes.length > 0) {
            if (imageBytes[0] == (byte) 0xFF && imageBytes[1] == (byte) 0xD8) {
                return "image/jpeg"; // JPG o JPEG
            } else if (imageBytes[0] == (byte) 0x89 && imageBytes[1] == (byte) 0x50) {
                return "image/png"; // PNG
            }
        }
        return "application/octet-stream"; // Tipo por defecto
    }

}
