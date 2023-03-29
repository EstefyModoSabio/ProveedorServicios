
package ProveedorServicios.demo.Repositorios;

import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Enums.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
     @Query("SELECT p FROM Proveedor p WHERE p.profesion = :profesion")
    public Proveedor buscarPorProfesion(@Param("profesion")Profesion profesion);


    @Query("SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    public Proveedor buscarPorNombre(@Param("nombre")String nombre);

    @Query("SELECT p FROM Proveedor p WHERE p.cuit = :cuit")
    public Proveedor buscarPorCuit(@Param("cuit")String cuit);

}
