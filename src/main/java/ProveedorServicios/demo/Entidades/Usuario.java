
package ProveedorServicios.demo.Entidades;

import ProveedorServicios.demo.Enums.Rol;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "system_uuid")
    private String id;
    private String Dni;
    private String Nombre;
    private String Direccion;
    private int Telefono; 
    private String Password;
    private String Email;
    @OneToOne
    private Imagen Imagen;
    private Rol Rol;

    
    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        this.Telefono = telefono;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public Imagen getImagen() {
        return Imagen;
    }

    public void setImagen(Imagen imagen) {
        this.Imagen = imagen;
    }

    public Enum getRol() {
        return Rol;
    }

    public void setRol(Enum rol) {
        this.Rol= (Rol) rol;
    }

    
}
