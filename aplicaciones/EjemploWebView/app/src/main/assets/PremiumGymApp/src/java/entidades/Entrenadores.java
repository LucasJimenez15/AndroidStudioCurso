/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "entrenadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrenadores.findAll", query = "SELECT e FROM Entrenadores e")
    , @NamedQuery(name = "Entrenadores.findByDNIEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.dNIEntrenador = :dNIEntrenador")
    , @NamedQuery(name = "Entrenadores.findByNombreEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.nombreEntrenador = :nombreEntrenador")
    , @NamedQuery(name = "Entrenadores.findByApellidoEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.apellidoEntrenador = :apellidoEntrenador")
    , @NamedQuery(name = "Entrenadores.findByFechaNacEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.fechaNacEntrenador = :fechaNacEntrenador")
    , @NamedQuery(name = "Entrenadores.findByEdadEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.edadEntrenador = :edadEntrenador")
    , @NamedQuery(name = "Entrenadores.findByDireccionEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.direccionEntrenador = :direccionEntrenador")
    , @NamedQuery(name = "Entrenadores.findByTelefonoEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.telefonoEntrenador = :telefonoEntrenador")
    , @NamedQuery(name = "Entrenadores.findByEmailEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.emailEntrenador = :emailEntrenador")
    , @NamedQuery(name = "Entrenadores.findByContrasenaEntrenador", query = "SELECT e FROM Entrenadores e WHERE e.contrasenaEntrenador = :contrasenaEntrenador")})
public class Entrenadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "DNIEntrenador", nullable = false, length = 15)
    private Integer dNIEntrenador;
    //@Size(max = 15)
    @Column(name = "nombreEntrenador", length = 15)
    private String nombreEntrenador;
    //@Size(max = 15)
    @Column(name = "apellidoEntrenador", length = 15)
    private String apellidoEntrenador;
    @Column(name = "fechaNacEntrenador")
    @Temporal(TemporalType.DATE)
    private Date fechaNacEntrenador;
    @Column(name = "edadEntrenador")
    private Integer edadEntrenador;
    //@Size(max = 20)
    @Column(name = "direccionEntrenador", length = 20)
    private String direccionEntrenador;
    @Column(name = "telefonoEntrenador",length = 15)
    private Long telefonoEntrenador;
    //@Size(max = 20)
    @Column(name = "emailEntrenador", length = 20)
    private String emailEntrenador;
    @Lob
    @Column(name = "fotoEntrenador")
    private byte[] fotoEntrenador;
    //@Size(max = 20)
    @Column(name = "contrasenaEntrenador", length = 20)
    private String contrasenaEntrenador;
 

    public Entrenadores() {
    }

    public Entrenadores(Integer dNIEntrenador) {
        this.dNIEntrenador = dNIEntrenador;
    }

    public Integer getDNIEntrenador() {
        return dNIEntrenador;
    }

    public void setDNIEntrenador(Integer dNIEntrenador) {
        this.dNIEntrenador = dNIEntrenador;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

    public String getApellidoEntrenador() {
        return apellidoEntrenador;
    }

    public void setApellidoEntrenador(String apellidoEntrenador) {
        this.apellidoEntrenador = apellidoEntrenador;
    }

    public Date getFechaNacEntrenador() {
        return fechaNacEntrenador;
    }

    public void setFechaNacEntrenador(Date fechaNacEntrenador) {
        this.fechaNacEntrenador = fechaNacEntrenador;
    }

    public Integer getEdadEntrenador() {
        return edadEntrenador;
    }

    public void setEdadEntrenador(Integer edadEntrenador) {
        this.edadEntrenador = edadEntrenador;
    }

    public String getDireccionEntrenador() {
        return direccionEntrenador;
    }

    public void setDireccionEntrenador(String direccionEntrenador) {
        this.direccionEntrenador = direccionEntrenador;
    }

    public Long getTelefonoEntrenador() {
        return telefonoEntrenador;
    }

    public void setTelefonoEntrenador(Long telefonoEntrenador) {
        this.telefonoEntrenador = telefonoEntrenador;
    }

    public String getEmailEntrenador() {
        return emailEntrenador;
    }

    public void setEmailEntrenador(String emailEntrenador) {
        this.emailEntrenador = emailEntrenador;
    }

    public byte[] getFotoEntrenador() {
        return fotoEntrenador;
    }

    public void setFotoEntrenador(byte[] fotoEntrenador) {
        this.fotoEntrenador = fotoEntrenador;
    }

    public String getContrasenaEntrenador() {
        return contrasenaEntrenador;
    }

    public void setContrasenaEntrenador(String contrasenaEntrenador) {
        this.contrasenaEntrenador = contrasenaEntrenador;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dNIEntrenador != null ? dNIEntrenador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrenadores)) {
            return false;
        }
        Entrenadores other = (Entrenadores) object;
        if ((this.dNIEntrenador == null && other.dNIEntrenador != null) || (this.dNIEntrenador != null && !this.dNIEntrenador.equals(other.dNIEntrenador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entrenadores[ dNIEntrenador=" + dNIEntrenador + " ]";
    }
    
}
