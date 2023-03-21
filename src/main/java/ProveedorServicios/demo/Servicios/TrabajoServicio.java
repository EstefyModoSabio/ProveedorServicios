package ProveedorServicios.demo.Servicios;

import ProveedorServicios.demo.Entidades.Trabajo;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.TrabajoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajoServicio {

    @Autowired
    private TrabajoRepositorio trabajoRepo;

    public void crearTrabajo(int NumeroPedido, String Dni, String Cuit,
            Date FechaBaja, Date FechaAlta, boolean Activo, String Reseña) {

        Trabajo trabajo = new Trabajo();

        trabajo.setActivo(Activo);
        trabajo.setCuit(Cuit);
        trabajo.setDni(Dni);
        trabajo.setFechaAlta(FechaAlta);
        trabajo.setFechaBaja(FechaBaja);
        trabajo.setNumeroPedido(NumeroPedido);
        trabajo.setReseña(Reseña);

        trabajoRepo.save(trabajo);
    }

    public void editarTrabajo(String id, int NumeroPedido, String Dni, String Cuit,
            Date FechaBaja, Date FechaAlta, boolean Activo, String Reseña) throws MiException {

        Optional<Trabajo> respuesta = trabajoRepo.findById(id);

        if (respuesta.isPresent()) {

            Trabajo trabajo = respuesta.get();
            trabajo.setActivo(Activo);
            trabajo.setCuit(Cuit);
            trabajo.setDni(Dni);
            trabajo.setFechaAlta(FechaAlta);
            trabajo.setFechaBaja(FechaBaja);
            trabajo.setNumeroPedido(NumeroPedido);
            trabajo.setReseña(Reseña);

            trabajoRepo.save(trabajo);
        } else {
            throw new MiException("trabajo no encontrado");
        }

    }

    public void eliminar(String id) {
        Trabajo trabajo = getOne(id);
        trabajoRepo.delete(trabajo);
    }

    public Trabajo getOne(String id) {
        return trabajoRepo.getOne(id);
    }

    public List<Trabajo> listar() {
        return trabajoRepo.findAll();
    }

    public void validar(int NumeroPedido, String Dni, String Cuit,
            Date FechaBaja, Date FechaAlta, boolean Activo, String Reseña) throws MiException {

        if (Dni.isEmpty()) {
            throw new MiException("Dni nulo o incorrecto");
        }
        if (Cuit.isEmpty()) {
            throw new MiException("Cuit nulo o incorrecto");
        }
        if (FechaBaja == null) {
            throw new MiException("FechaBaja nulo o incorrecto");
        }
        if (FechaAlta == null) {
            throw new MiException("FechaAlta nulo o incorrecto");
        }
        if (Reseña.isEmpty()) {
            throw new MiException("Reseña nulo o incorrecto");
        }
    }

}
