/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "socios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socios.findAll", query = "SELECT s FROM Socios s")
    , @NamedQuery(name = "Socios.findByDNIsocio", query = "SELECT s FROM Socios s WHERE s.dNIsocio = :dNIsocio")
    , @NamedQuery(name = "Socios.findByNombreSocio", query = "SELECT s FROM Socios s WHERE s.nombreSocio = :nombreSocio")
    , @NamedQuery(name = "Socios.findByApellidoSocio", query = "SELECT s FROM Socios s WHERE s.apellidoSocio = :apellidoSocio")
    , @NamedQuery(name = "Socios.findByFechaNacSocio", query = "SELECT s FROM Socios s WHERE s.fechaNacSocio = :fechaNacSocio")
    , @NamedQuery(name = "Socios.findByEdadSocio", query = "SELECT s FROM Socios s WHERE s.edadSocio = :edadSocio")
    , @NamedQuery(name = "Socios.findByDireccionSocio", query = "SELECT s FROM Socios s WHERE s.direccionSocio = :direccionSocio")
    , @NamedQuery(name = "Socios.findByTelefonoSocio", query = "SELECT s FROM Socios s WHERE s.telefonoSocio = :telefonoSocio")
    , @NamedQuery(name = "Socios.findByEmailSocio", query = "SELECT s FROM Socios s WHERE s.emailSocio = :emailSocio")
    , @NamedQuery(name = "Socios.findByPesoSocio", query = "SELECT s FROM Socios s WHERE s.pesoSocio = :pesoSocio")
    , @NamedQuery(name = "Socios.findByAlturaSocio", query = "SELECT s FROM Socios s WHERE s.alturaSocio = :alturaSocio")
    , @NamedQuery(name = "Socios.findByContrasenaSocio", query = "SELECT s FROM Socios s WHERE s.contrasenaSocio = :contrasenaSocio")})
public class Socios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "DNIsocio", nullable = false)
    private Integer dNIsocio;
    //@Size(max = 15)
    @Column(name = "nombreSocio", length = 15)
    private String nombreSocio;
    //@Size(max = 15)
    @Column(name = "apellidoSocio", length = 15)
    private String apellidoSocio;
    @Column(name = "fechaNacSocio")
    @Temporal(TemporalType.DATE)
    private Date fechaNacSocio;
    @Column(name = "edadSocio")
    private Integer edadSocio;
    //@Size(max = 20)
    @Column(name = "direccionSocio", length = 20)
    private String direccionSocio;
    //@Size(max = 20)
    @Column(name = "telefonoSocio", length = 20)
    private String telefonoSocio;
    //@Size(max = 100)
    @Column(name = "emailSocio", length = 100)
    private String emailSocio;
    @Lob
    @Column(name = "fotoSocio")
    private byte[] fotoSocio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pesoSocio", precision = 12)
    private Float pesoSocio;
    @Column(name = "alturaSocio", precision = 12)
    private Float alturaSocio;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 20)
    @Column(name = "contrasenaSocio", nullable = false, length = 20)
    private String contrasenaSocio;
    @Lob
    @Column(name = "rutina")
    private byte[] rutina;

    public Socios() {
    }

    public Socios(Integer dNIsocio) {
        this.dNIsocio = dNIsocio;
    }

    public Socios(Integer dNIsocio, String contrasenaSocio) {
        this.dNIsocio = dNIsocio;
        this.contrasenaSocio = contrasenaSocio;
    }

    public Integer getDNIsocio() {
        return dNIsocio;
    }

    public void setDNIsocio(Integer dNIsocio) {
        this.dNIsocio = dNIsocio;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getApellidoSocio() {
        return apellidoSocio;
    }

    public void setApellidoSocio(String apellidoSocio) {
        this.apellidoSocio = apellidoSocio;
    }

    public Date getFechaNacSocio() {
        return fechaNacSocio;
    }

    public void setFechaNacSocio(Date fechaNacSocio) {
        this.fechaNacSocio = fechaNacSocio;
    }

    public Integer getEdadSocio() {
        return edadSocio;
    }

    public void setEdadSocio(Integer edadSocio) {
        this.edadSocio = edadSocio;
    }

    public String getDireccionSocio() {
        return direccionSocio;
    }

    public void setDireccionSocio(String direccionSocio) {
        this.direccionSocio = direccionSocio;
    }

    public String getTelefonoSocio() {
        return telefonoSocio;
    }

    public void setTelefonoSocio(String telefonoSocio) {
        this.telefonoSocio = telefonoSocio;
    }

    public String getEmailSocio() {
        return emailSocio;
    }

    public void setEmailSocio(String emailSocio) {
        this.emailSocio = emailSocio;
    }

    public byte[] getFotoSocio() {
        return fotoSocio;
    }

    public void setFotoSocio(byte[] fotoSocio) {
        this.fotoSocio = fotoSocio;
    }

    public Float getPesoSocio() {
        return pesoSocio;
    }

    public void setPesoSocio(Float pesoSocio) {
        this.pesoSocio = pesoSocio;
    }

    public Float getAlturaSocio() {
        return alturaSocio;
    }

    public void setAlturaSocio(Float alturaSocio) {
        this.alturaSocio = alturaSocio;
    }

    public String getContrasenaSocio() {
        return contrasenaSocio;
    }

    public void setContrasenaSocio(String contrasenaSocio) {
        this.contrasenaSocio = contrasenaSocio;
    }

    public byte[] getRutina() {
        return rutina;
    }

    public void setRutina(byte[] rutina) {
        this.rutina = rutina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dNIsocio != null ? dNIsocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socios)) {
            return false;
        }
        Socios other = (Socios) object;
        if ((this.dNIsocio == null && other.dNIsocio != null) || (this.dNIsocio != null && !this.dNIsocio.equals(other.dNIsocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Socios[ dNIsocio=" + dNIsocio + " ]";
    }
    
}
