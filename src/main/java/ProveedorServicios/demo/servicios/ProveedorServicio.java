
package ProveedorServicios.demo.servicios;

import ProveedorServicios.demo.entidades.Proveedor;
import ProveedorServicios.demo.enums.Profesion;
import ProveedorServicios.demo.enums.Rol;
import ProveedorServicios.demo.excepciones.MiException;
import ProveedorServicios.demo.repositorios.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProveedorServicio {
    //instanciamos libroRepositorio para poder conectarnos con la DB
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
    
    @Transactional
    public void crearProveedor(String cuit, String nombre, String telefono,String email, String profesion)throws MiException{
        
        
        validar(cuit, nombre, email, telefono, profesion);
        
        //instanciamos
        Proveedor proveedor = new Proveedor();
        //seteamos atributos
        proveedor.setCuit(cuit);
        proveedor.setNombre(nombre);
        proveedor.setEmail(email);
        proveedor.setTelefono(telefono);        
        proveedor.setProfesion(Profesion.valueOf(profesion));
        
        proveedorRepositorio.save(proveedor);
        
    }
    
    //método para listar todos los proveedores
    public List<Proveedor> listarProveedores(){
        List<Proveedor> proveedores = new ArrayList();
        proveedores = proveedorRepositorio.findAll();
        
        return proveedores;
    }
    
    @Transactional
    public void editarProveedor(String cuit, String direccion, Integer honorarios, Rol rol){
        
       
       Optional<Proveedor> respuesta = proveedorRepositorio.findById(cuit);
       
       if(respuesta.isPresent()){
           Proveedor proveedor = respuesta.get();
           proveedor.setDireccion(direccion);
           proveedor.setHonorarios(honorarios);
           proveedor.setRol(rol);
           
           proveedorRepositorio.save(proveedor);
           
       }
    }
    
    private void validar(String cuit, String nombre, String email, String telefono, String profesion)throws MiException{
        if(cuit.isEmpty() || cuit == null){
            throw new MiException("el cuit no puede ser nulo");
        }
         if(nombre.isEmpty() || nombre == null){
            throw new MiException("el nombre no puede ser nulo");
        }
         if(telefono.isEmpty()){
             throw new MiException("El telefono no puede estar vacio");
         }
           if(email.isEmpty()){
            throw new MiException("el email no puede estar vacío");
        }
            if(profesion == null || profesion.isEmpty()){
            throw new MiException("debe ingresar una profesión");
        }
    }
}
