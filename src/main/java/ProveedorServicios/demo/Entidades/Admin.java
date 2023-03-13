
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }
     
     
    
}
