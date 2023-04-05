package ProveedorServicios.demo.Entidades;

import ProveedorServicios.demo.Enums.Profesion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
public class Proveedor extends Usuario {

    @Column
    @Enumerated(value = EnumType.STRING)
    private Profesion profesion;
    private float calificacion;
    private int honorarios;
    private String cuit;
    private String presentacion;

    public Proveedor() {
    }

    
    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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
