package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.servicios.ProveedorServicios;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
   @Autowired
   ProveedorServicios proveedorServicio;
    
    
    @GetMapping("/dashboard")
    public String panel(ModelMap modelo){
        
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "admin";
    }
    
     @PostMapping("/buscar")
    public String resultadoBusqueda(ModelMap modelo, @RequestParam String busqueda) {
        try {
//            List<Proveedor> proveedores = proveedorServicio.listarProveedoresPorOficio(busqueda.toUpperCase());
//            modelo.addAttribute("proveedores", proveedores);
            return "proveedor_list.html";

        } catch (Exception e) {
            return "index.html";
        }

    }
    
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("proveedor", proveedorServicio.getOne(id));
        List<Profesion> profesiones = Arrays.asList(Profesion.values());
        modelo.put("profesiones", profesiones);
        return "proveedor_editar.html";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(String id, String cuit, String nombre, String email, String telefono, String profesion,
            MultipartFile imagen, int honoraios, ModelMap modelo){
        try {
            proveedorServicio.editarProveedor (id, cuit, nombre, email, telefono, profesion, profesion, profesion, imagen, 0, profesion);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "proveedor_editar.html";
        }
        
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        proveedorServicio.eliminarProveedor(id);
         List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "admin";
    }
    
    
    
    
    
    
    
}
