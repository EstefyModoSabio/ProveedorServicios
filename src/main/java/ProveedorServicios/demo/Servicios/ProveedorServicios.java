package ProveedorServicios.demo.servicios;

import ProveedorServicios.demo.Entidades.Imagen;
import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.ProveedorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ProveedorServicios.demo.Servicios.ImagenServicio;

@Service
public class ProveedorServicios {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;


    @Transactional
    public void crearProveedor(String cuit, String nombre, String telefono, String email,
            String profesion, String password, String password2, MultipartFile imagen, int honorarios, String presentacion) throws MiException {

        validar(cuit, nombre, email, telefono, profesion, password, password2, imagen, honorarios, presentacion);

        //instanciamos
        Proveedor proveedor = new Proveedor();
        //seteamos atributos
        proveedor.setCuit(cuit);
        proveedor.setNombre(nombre);
        proveedor.setEmail(email);
        proveedor.setTelefono(telefono);
        proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
        proveedor.setProfesion(Profesion.valueOf(profesion));

        proveedor.setRol(Rol.PROFESIONAL);

        Imagen foto = imagenServicio.guardarImagen(imagen);
        proveedor.setImagen(foto);
        proveedor.setHonorarios(honorarios);
        proveedor.setPresentacion(presentacion);

        proveedorRepositorio.save(proveedor);
    }


    public List<Proveedor> listarProveedores() {
        List<Proveedor> proveedores = proveedorRepositorio.findAll();

        return proveedores;
    }


    public List<Proveedor> filtroBusqueda(String profesion) throws MiException {
        try {
        List<Proveedor> proveedores = proveedorRepositorio.buscarPorProfesion(1);
        System.out.println(proveedores);
        return proveedores;
        } catch (Exception e) {
            throw new MiException("Enum de profesion no encontrado");
        }
    }

    @Transactional
    public void editarProveedor(String id, String cuit, String nombre, String email, String telefono, String profesion,
            String password, String password2, MultipartFile imagen, int honorarios, String presentacion) throws MiException {

        validar(id, nombre, email, telefono, profesion, password, password2, imagen, honorarios, presentacion);

        Optional<Proveedor> respuesta = proveedorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Proveedor proveedor = respuesta.get();

            proveedor.setCuit(cuit);
            proveedor.setNombre(nombre);
            proveedor.setEmail(email);
            proveedor.setTelefono(telefono);
            proveedor.setPassword(new BCryptPasswordEncoder().encode(password));

            proveedor.setProfesion(Profesion.valueOf(profesion));

            proveedor.setRol(Rol.PROFESIONAL);
            Imagen foto = imagenServicio.guardarImagen(imagen);
            proveedor.setImagen(foto);
            proveedor.setHonorarios(honorarios);
            proveedor.setPresentacion(presentacion);

            proveedorRepositorio.save(proveedor);

        }
    }

    private void validar(String cuit, String nombre, String email, String telefono, String profesion,
            String password, String password2, MultipartFile imagen, int honorarios, String presentacion) throws MiException {

        if (cuit.isEmpty() || cuit == null) {
            throw new MiException("Ups! El cuit no puede estar vacío");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo");
        }
        if (telefono.isEmpty()) {
            throw new MiException("El telefono no puede estar vacio");
        }
        if (email.isEmpty()) {
            throw new MiException("el email no puede estar vacío");
        }
        if (profesion == null || profesion.isEmpty()) {
            throw new MiException("debe ingresar una profesión");
        }

    }

    public void eliminarProveedor(String cuit) {

        proveedorRepositorio.deleteById(cuit);
    }

    public Proveedor getOne(String cuit) {
        return proveedorRepositorio.getOne(cuit);
    }

}
