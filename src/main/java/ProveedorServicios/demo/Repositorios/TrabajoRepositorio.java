
package ProveedorServicios.demo.Repositorios;

import ProveedorServicios.demo.Entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrabajoRepositorio extends JpaRepository<Trabajo, String>{
    
}
