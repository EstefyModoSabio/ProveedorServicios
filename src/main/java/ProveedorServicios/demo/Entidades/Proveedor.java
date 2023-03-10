
package ProveedorServicios.demo.Entidades;

import ProveedorServicios.demo.Enums.Profesion;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Proveedor extends Usuario {
    
    @Id
    @GeneratedValue(generator = "system=uuid")
    private String id;
    private Profesion Profesion;
    private float Calificacion;
    private int Honorarios;
    @OneToOne
    private Imagen imagen;
    
}
