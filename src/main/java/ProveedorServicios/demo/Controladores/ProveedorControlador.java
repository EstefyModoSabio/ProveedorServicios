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

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

   
    @Autowired
    private ProveedorServicios proveedorServicio;
    
    @GetMapping("/registrar") //localhost:8080/proveedor/registrar
    public String registrar(ModelMap modelo){
        
       List<Profesion> proveedores = Arrays.asList(Profesion.values());
        
        modelo.addAttribute("proveedores", proveedores);
        
        return "loginProveedor.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) String cuit,@RequestParam String nombre,@RequestParam String telefono, @RequestParam String email,@RequestParam String profesion, ModelMap modelo){
       
        try {
            proveedorServicio.crearProveedor(cuit, nombre, telefono, email, profesion);
            
            modelo.put("exito", "El proveedor se ha guardado con éxito");
             List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            modelo.addAttribute("proveedores", proveedores);
            return "proveedor_list";
            
        } catch (MiException ex) {
            
             //List<String> proveedores = Arrays.asList("Plomero", "Gasista", "Electricista", "Pintor", "Cerrajero", "Jardinero", "Piletero", "Albañil");
             //new ArrayList<MyEnum>(Arrays.asList(MyEnum.values()));
             
             List<Profesion> proveedores = Arrays.asList(Profesion.values());
             
             modelo.addAttribute("proveedores", proveedores);
             modelo.put("error", ex.getMessage());
            return "proveedor_form.html";
        }
        
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);
        return "listaProveedores.html";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("proveedor", proveedorServicio.getOne(id));
        List<Profesion> profesiones = Arrays.asList(Profesion.values());
             
        modelo.put("profesiones", profesiones);
        return "proveedor_editar.html";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, String cuit, String nombre, String email, String telefono, String profesion, ModelMap modelo){
        try {
            proveedorServicio.editarProveedor(id, cuit, nombre, email, telefono, profesion);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "proveedor_editar.html";
        }
        
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        proveedorServicio.eliminarProveedor(id);
        return "redirect:../lista";
    }
    
    @PostMapping("/busqueda/{profesion}")
    public String filtroBusqueda(@PathVariable String profesion, ModelMap modelo){
        List<Proveedor> filtro = proveedorServicio.filtroBusqueda(profesion);
        modelo.addAttribute("proveedores", filtro);
        
        return "redirect:../vistaProveedores";
    }
    
}