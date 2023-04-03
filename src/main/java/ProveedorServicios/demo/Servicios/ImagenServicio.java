

package ProveedorServicios.demo.Servicios;

import ProveedorServicios.demo.Entidades.Imagen;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {
    
    @Autowired
    ImagenRepositorio imagenRepositorio;
    
    @Transactional
    public Imagen guardarImagen (MultipartFile archivo){
        
        if (archivo != null){
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                
                return imagenRepositorio.save(imagen);
            } catch (IOException e) {
                System.err.println("Error en cargar la imagen");  
            }
        }
        return null;
    }
    
    @Transactional
    public Imagen actualizarImagen (MultipartFile archivo, String idImagen) throws MiException{
        
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null){
                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                    
                    if (respuesta.isPresent()){
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                
                return imagenRepositorio.save(imagen);
                
            } catch (IOException e) {
                System.err.println(e.getMessage()); 
            }
        }
        return null;
    }
}
