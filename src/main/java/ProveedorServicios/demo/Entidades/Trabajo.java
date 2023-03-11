
package ProveedorServicios.demo.Entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;


@Entity
public class Trabajo {
    
    @Id
    @GeneratedValue(generator = "system=uuid")
    private String id;
    private int NumeroPedido;
    private String Dni;
    private String Cuit;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date FechaBaja;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date FechaAlta;
    private boolean Activo;
    private String Reseña;

    public Trabajo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroPedido() {
        return NumeroPedido;
    }

    public void setNumeroPedido(int NumeroPedido) {
        this.NumeroPedido = NumeroPedido;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getCuit() {
        return Cuit;
    }

    public void setCuit(String Cuit) {
        this.Cuit = Cuit;
    }

    public Date getFechaBaja() {
        return FechaBaja;
    }

    public void setFechaBaja(Date FechaBaja) {
        this.FechaBaja = FechaBaja;
    }

    public Date getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(Date FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }

    public String getReseña() {
        return Reseña;
    }

    public void setReseña(String Reseña) {
        this.Reseña = Reseña;
    }
    
    
    
}
