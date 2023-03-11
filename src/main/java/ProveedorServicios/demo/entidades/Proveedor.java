
package ProveedorServicios.demo.entidades;

import ProveedorServicios.demo.enums.Profesion;
import ProveedorServicios.demo.enums.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Proveedor {
    @Id
    private String cuit;
    
    private String nombre;
    private String direccion;
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    private Profesion profesion;
    
    private float calificacion;
    private String email;
    private String pass;
    private String pass2;
    private Integer honorarios;
    @GeneratedValue(generator="uuid")
    private String id;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Proveedor() {
    }

    public Proveedor(String cuit, String nombre, String direccion, String telefono, Profesion profesion, String email, String pass, String pass2, Integer honorarios, Rol rol) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.profesion = profesion.CERRAJERO;
        this.email = email;
        this.pass = pass;
        this.pass2 = pass2;
        this.honorarios = honorarios;
        this.id = id;
        this.rol = rol;
    }

    public Proveedor(String cuit, String nombre, String telefono, String email, Profesion profesion) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.profesion = profesion;
    }
    
    

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public Integer getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(Integer honorarios) {
        this.honorarios = honorarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "cuit=" + cuit + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", profesion=" + profesion + ", calificacion=" + calificacion + ", email=" + email + ", pass=" + pass + ", pass2=" + pass2 + ", honorarios=" + honorarios + ", id=" + id + ", rol=" + rol + '}';
    }

   
   
    
}
