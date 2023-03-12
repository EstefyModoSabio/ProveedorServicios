
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
        if(profesion.toUpperCase().equals("PLOMERO")){
            proveedor.setProfesion(Profesion.PLOMERO);
        }
        if(profesion.toUpperCase().equals("GASISTA")){
            proveedor.setProfesion(Profesion.GASISTA);
        }
        if(profesion.toUpperCase().equals("ELECTRICISTA")){
            proveedor.setProfesion(Profesion.ELECTRICISTA);
        }
        if(profesion.toUpperCase().equals("CERRAJERO")){
            proveedor.setProfesion(Profesion.CERRAJERO);
        }
        if(profesion.toUpperCase().equals("JARDINERO")){
            proveedor.setProfesion(Profesion.JARDINERO);
        }
        if(profesion.toUpperCase().equals("PINTOR")){
            proveedor.setProfesion(Profesion.PINTOR);
        }
        proveedorRepositorio.save(proveedor);
        
    }
    
    //método para listar todos los proveedores
    public List<Proveedor> listarProveedores(){
        List<Proveedor> proveedores = new ArrayList();
        proveedores = proveedorRepositorio.findAll();
        
        return proveedores;
    }
    
    public List<Proveedor> listarProveedoresPorNombre(String nombre){
        List<Proveedor> proveedores = new ArrayList();
        
        proveedores = proveedorRepositorio.buscarPorNombre(nombre);
        
        return proveedores;
    }
    
    public List<Proveedor> listarProveedoresPorOficio(String profesion){
        List<Proveedor> proveedores = new ArrayList();
        
        proveedores = proveedorRepositorio.buscarPorProfesion(Profesion.valueOf(profesion));
        
        return proveedores;
    }
    
    @Transactional
    public void editarProveedor(String cuit, String nombre, String email, String telefono, String profesion) throws MiException{
        
       validar(cuit, nombre, email, telefono, profesion);
       
       Optional<Proveedor> respuesta = proveedorRepositorio.findById(cuit);
       
       if(respuesta.isPresent()){
           Proveedor proveedor = respuesta.get();
           
           
           proveedor.setNombre(nombre);
           proveedor.setTelefono(telefono);
           proveedor.setEmail(email);
           if(profesion.toUpperCase().equals("PLOMERO")){
            proveedor.setProfesion(Profesion.PLOMERO);
        }
        if(profesion.toUpperCase().equals("GASISTA")){
            proveedor.setProfesion(Profesion.GASISTA);
        }
        if(profesion.toUpperCase().equals("ELECTRICISTA")){
            proveedor.setProfesion(Profesion.ELECTRICISTA);
        }
        if(profesion.toUpperCase().equals("CERRAJERO")){
            proveedor.setProfesion(Profesion.CERRAJERO);
        }
        if(profesion.toUpperCase().equals("JARDINERO")){
            proveedor.setProfesion(Profesion.JARDINERO);
        }
        if(profesion.toUpperCase().equals("PINTOR")){
            proveedor.setProfesion(Profesion.PINTOR);
        }
           
           proveedorRepositorio.save(proveedor);
           
       }
    }
    
    private void validar(String cuit, String nombre, String email, String telefono, String profesion)throws MiException{
        if(cuit.isEmpty() || cuit == null){
            throw new MiException("Ups! El cuit no puede estar vacío");
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
    
    
    public void eliminarProveedor(String cuit){
        
       proveedorRepositorio.deleteById(cuit);
    }
    
    public Proveedor getOne(String cuit){
        return proveedorRepositorio.getOne(cuit);
    }
}
