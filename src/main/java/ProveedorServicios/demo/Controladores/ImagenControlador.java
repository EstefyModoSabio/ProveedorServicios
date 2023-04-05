package ProveedorServicios.demo.Controladores;

import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Entidades.Trabajo;
import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Servicios.TrabajoServicio;
import ProveedorServicios.demo.Servicios.UsuarioServicio;
import ProveedorServicios.demo.servicios.ProveedorServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    private ProveedorServicios proveedorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private TrabajoServicio trabajoServicio;

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<byte[]> proveedorImagen(@PathVariable String id) {
        Proveedor proveedor = proveedorServicio.getOne(id);
        byte[] imagen = proveedor.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<byte[]> usuarioImagen(@PathVariable String id) {
        Usuario usuario = usuarioServicio.getOne(id);
        System.err.println(usuario.getNombre());
        byte[] imagen = usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    @GetMapping("/trabajo/{id}")
    public ResponseEntity<byte[]> trabajoImagen(@PathVariable String id) {
        Trabajo trabajo = trabajoServicio.getOne(id);
        byte[] imagen = trabajo.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

}
