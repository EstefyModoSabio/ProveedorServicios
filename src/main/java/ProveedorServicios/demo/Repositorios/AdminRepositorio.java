package ProveedorServicios.demo.Repositorios;

import ProveedorServicios.demo.Entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepositorio extends JpaRepository<Admin,String>{
    
    
    
}
