
package ProveedorServicios.demo.Repositorios;

import ProveedorServicios.demo.Entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {
    
}
