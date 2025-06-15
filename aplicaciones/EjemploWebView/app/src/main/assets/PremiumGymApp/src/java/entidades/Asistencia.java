package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia")
    , @NamedQuery(name = "Asistencia.findByFechaAsistencia", query = "SELECT a FROM Asistencia a WHERE a.fechaAsistencia = :fechaAsistencia")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "idAsistencia", nullable = false)
    private Integer idAsistencia;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechaAsistencia", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsistencia;
    
    @JoinColumn(name = "DNIsocio", referencedColumnName = "DNIsocio")
    @ManyToOne
    private Socios dNIsocio;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Integer idAsistencia, Date fechaAsistencia) {
        this.idAsistencia = idAsistencia;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Integer getDNIsocio() {
        return dNIsocio.getDNIsocio();
    }

    public void setDNIsocio(Socios dNIsocio) {
        this.dNIsocio = dNIsocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
