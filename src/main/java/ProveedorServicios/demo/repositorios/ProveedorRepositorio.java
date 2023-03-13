
package ProveedorServicios.demo.repositorios;

import ProveedorServicios.demo.entidades.Proveedor;
import ProveedorServicios.demo.enums.Profesion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
    @Query("SELECT p FROM Proveedor p WHERE p.profesion = :profesion")
    public List<Proveedor> buscarPorProfesion(@Param("profesion")Profesion profesion);
    
    
    @Query("SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    public List<Proveedor> buscarPorNombre(@Param("nombre")String nombre);
    
    @Query("SELECT p FROM Proveedor p WHERE p.cuit = :cuit")
    public Proveedor buscarPorCuit(@Param("cuit")String cuit);

}
