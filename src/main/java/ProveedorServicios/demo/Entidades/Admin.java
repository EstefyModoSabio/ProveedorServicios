
package ProveedorServicios.demo.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Admin extends Usuario{
    
     @Id
    @GeneratedValue(generator = "system=uuid")
    private String id;
     private String Barrio;
    
}
