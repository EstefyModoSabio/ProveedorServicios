
package ProveedorServicios.demo.Repositorios;

import ProveedorServicios.demo.Entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
}
