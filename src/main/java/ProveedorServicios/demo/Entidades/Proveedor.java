
package ProveedorServicios.demo.Entidades;

import ProveedorServicios.demo.Enums.Profesion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;



@Entity
public class Proveedor extends Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Profesion profesion;
    private float calificacion;
    private int honorarios;
    private String cuit;

    public Proveedor() {
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(int honorarios) {
        this.honorarios = honorarios;
    }

    
   
    
    
    
}
