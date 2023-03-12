
package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.entidades.Proveedor;
import ProveedorServicios.demo.enums.Profesion;
import ProveedorServicios.demo.enums.Rol;
import ProveedorServicios.demo.excepciones.MiException;
import ProveedorServicios.demo.servicios.ProveedorServicio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    @Autowired
    private ProveedorServicio proveedorServicio;
    
    @GetMapping("/registrar") //localhost:8080/proveedor/registrar
    public String registrar(ModelMap modelo){
        List<String> proveedores = Arrays.asList("Plomero", "Gasista", "Electricista", "Pintor", "Cerrajero", "Jardinero");
        
        
        modelo.addAttribute("proveedores", proveedores);
        
        return "proveedor_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) String cuit,@RequestParam String nombre,@RequestParam String telefono, @RequestParam String email,@RequestParam String profesion, ModelMap modelo){
       
        try {
            proveedorServicio.crearProveedor(cuit, nombre, telefono, email, profesion);
            
            modelo.put("exito", "El proveedor se ha guardado con éxito");
            return "index.html";
            
        } catch (MiException ex) {
            
             List<String> proveedores = Arrays.asList("Plomero", "Gasista", "Electricista", "Pintor", "Cerrajero", "Jardinero");
             modelo.addAttribute("proveedores", proveedores);
             modelo.put("error", ex.getMessage());
            return "proveedor_form.html";
        }
        
        
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "proveedor_list.html";
    }
    
    @GetMapping("/editar/{cuit}")
    public String editar(@PathVariable String cuit, ModelMap modelo){
        
        modelo.put("proveedor", proveedorServicio.getOne(cuit));
        
        
        List<String> proveedores = Arrays.asList("Plomero", "Gasista", "Electricista", "Pintor", "Cerrajero", "Jardinero");
        modelo.put("proveedores", proveedores);
        return "proveedor_editar.html";
    }
    
    @PostMapping("/editar/{cuit}")
    public String editar(@PathVariable String cuit, String nombre, String email, String telefono, String profesion, ModelMap modelo){
        try {
            proveedorServicio.editarProveedor(cuit, nombre, email, telefono, profesion);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "proveedor_editar.html";
        }
        
    }
    
    
    
    
}
