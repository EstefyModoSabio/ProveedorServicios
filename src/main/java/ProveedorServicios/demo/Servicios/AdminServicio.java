package ProveedorServicios.demo.Servicios;

import ProveedorServicios.demo.Entidades.Admin;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.AdminRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicio {

    @Autowired
    private AdminRepositorio adminRepo;

    public void crearAdmin(String Barrio, String dni, String nombre, String direccion,
            String telefono, String password, String email, Rol rol) throws MiException {

        Admin admin = new Admin();

        validar(Barrio, dni, nombre, direccion, telefono, password, email, rol);

        admin.setBarrio(Barrio);
        admin.setDireccion(direccion);
        admin.setDni(dni);
        admin.setEmail(email);
        admin.setNombre(nombre);
        admin.setPassword(password);
        admin.setRol(rol);
        admin.setTelefono(telefono);

        adminRepo.save(admin);
    }

    public void editarAdmin(String id, String Barrio, String dni, String nombre, String direccion,
            String telefono, String password, String email, Rol rol) throws MiException {
        
        Optional<Admin> respuesta = adminRepo.findById(id);
        
        if (respuesta.isPresent()) {
            Admin admin = respuesta.get();
            admin.setBarrio(Barrio);
            admin.setDireccion(direccion);
            admin.setDni(dni);
            admin.setEmail(email);
            admin.setNombre(nombre);
            admin.setPassword(password);
            admin.setRol(rol);
            admin.setTelefono(telefono);

            adminRepo.save(admin);
        }else{
            throw new MiException("administrador no encontrado");
        }

    }

    public void eliminarAdmin(String id) throws MiException {
        Admin admin = getOne(id);
        if (admin != null) {
            adminRepo.deleteById(id);
        } else {
            throw new MiException("administrador no encontrado");
        }

    }

    public List<Admin> listar() {
        return adminRepo.findAll();
    }

    public Admin getOne(String id) {
        return adminRepo.getOne(id);
    }

    public void validar(String Barrio, String dni, String nombre, String direccion,
            String telefono, String password, String email, Rol rol) throws MiException {

        if (Barrio.isEmpty()) {
            throw new MiException("barrio nulo o incorrecto");
        }
        if (dni.isEmpty()) {
            throw new MiException("dni nulo o incorrecto");
        }
        if (nombre.isEmpty()) {
            throw new MiException("nombre nulo o incorrecto");
        }
        if (direccion.isEmpty()) {
            throw new MiException("direccion nulo o incorrecto");
        }
        if (telefono.isEmpty()) {
            throw new MiException("telefono nulo o incorrecto");
        }
        if (password.isEmpty()) {
            throw new MiException("password nulo o incorrecto");
        }
        if (email.isEmpty()) {
            throw new MiException("email nulo o incorrecto");
        }
        if (rol == null) {
            throw new MiException("rol nulo o incorrecto");
        }

    }

    
}
