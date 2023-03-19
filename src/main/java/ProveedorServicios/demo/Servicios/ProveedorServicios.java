/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProveedorServicios.demo.servicios;

import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProveedorServicios {
    //instanciamos proveedorRepositorio para poder conectarnos con la DB
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Transactional
    public void crearProveedor(String cuit, String nombre, String telefono,String email, Profesion profesion)throws MiException{


        validar(cuit, nombre, email, telefono, profesion);

        //instanciamos
        Proveedor proveedor = new Proveedor();
        //seteamos atributos
        proveedor.setId(cuit);
        proveedor.setNombre(nombre);
        proveedor.setEmail(email);
        proveedor.setTelefono(telefono);
        proveedor.setProfesion(profesion);

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

    public void eliminarProveedor(String id) {

        proveedorRepositorio.deleteById(id);
    }

    public Proveedor getOne(String id) {
        return proveedorRepositorio.getOne(id);
    }



    private void validar(String cuit, String nombre, String email, String telefono, Profesion profesion)throws MiException{
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
            if(profesion == null){
            throw new MiException("debe ingresar una profesión");
        }
    }
}
