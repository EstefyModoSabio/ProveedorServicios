package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.servicios.ProveedorServicios;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicios proveedorServicio;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "usuario o contraseña incorrecto");
        }
        List<Profesion> proveedores = Arrays.asList(Profesion.values());

        modelo.addAttribute("proveedores", proveedores);

        return "loginProveedor";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMINISTRADOR', 'ROLE_PROFESIONAL')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        switch (logueado.getRol().toString()) {
            case "ADMINISTRADOR":
                return "admin/dashboard";
            case "USUARIO":
                return "inicio";
            case "PROFESIONAL":
                return "/proveedor/inicio";
        }
        return "index";
    }

    @GetMapping("/registrar") //localhost:8080/proveedor/registrar
    public String registrar(ModelMap modelo) {

        List<Profesion> proveedores = Arrays.asList(Profesion.values());

        modelo.addAttribute("proveedores", proveedores);

        return "loginProveedor";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String cuit, @RequestParam String nombre, @RequestParam String telefono, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, @RequestParam int honorarios, @RequestParam String profesion, @RequestParam MultipartFile imagen, @RequestParam String presentacion, ModelMap modelo) {

        try {
            proveedorServicio.crearProveedor(cuit, nombre, telefono, email, profesion, password, password2, imagen, honorarios, presentacion);

            modelo.put("exito", "El proveedor se ha guardado con éxito");
            List<Proveedor> proveedores = proveedorServicio.listarProveedores();
            modelo.addAttribute("proveedores", proveedores);
            return "redirect:/inicio";

        } catch (MiException ex) {

            List<Profesion> proveedores = Arrays.asList(Profesion.values());

            modelo.addAttribute("proveedores", proveedores);
            modelo.put("error", ex.getMessage());
            return "loginProveedor";
        }

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Proveedor> proveedores = proveedorServicio.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);

        List<Profesion> proveedoresFiltro = Arrays.asList(Profesion.values());
        modelo.addAttribute("proveedoresFiltro", proveedoresFiltro);

        return "listaProveedores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) {

        modelo.put("proveedor", proveedorServicio.getOne(id));
        List<Profesion> profesiones = Arrays.asList(Profesion.values());

        modelo.put("profesiones", profesiones);
        return "proveedor_editar.html";
    }

    @PostMapping("/editado/{id}")
    public String editar(@PathVariable String id, String cuit, String nombre, String email, String telefono,
            String profesion, MultipartFile imagen, int honoraios, ModelMap modelo) {
        try {
            proveedorServicio.editarProveedor(id, cuit, nombre, email, telefono, profesion, profesion, profesion, imagen, 0, profesion);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "proveedor_editar.html";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        proveedorServicio.eliminarProveedor(id);
        return "redirect:../lista";
    }

    
    
    @GetMapping("/busqueda/{profesion}")
   // @RequestMapping(value = "/{profesion}", method = RequestMethod.GET)
    public String filtroBusqueda(@PathVariable Profesion profesion, ModelMap modelo) throws MiException {
        System.err.println(profesion);
        
        List<Proveedor> proveedor = proveedorServicio.filtroBusqueda(profesion.toString());
        modelo.addAttribute("proveedor", proveedor);

        return "ProveedoresPersonalizado";
    }

    
    
    @GetMapping("/{id}")
    public String verProveedor(@PathVariable String id, ModelMap modelo) {
        Proveedor proveedor = proveedorServicio.getOne(id);
        modelo.addAttribute("proveedor", proveedor);
        return "proveedor";
    }

}
