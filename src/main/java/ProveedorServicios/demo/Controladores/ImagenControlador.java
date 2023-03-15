package ProveedorServicios.demo.Controladores;

import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/imagen")
public class ImagenControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping ("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario (@PathVariable String id){
        
        Usuario usuario = usuarioServicio.getOne(id);
        
        byte[] imagen = usuario.getImagen().getContenido();
        
    }
    
}
