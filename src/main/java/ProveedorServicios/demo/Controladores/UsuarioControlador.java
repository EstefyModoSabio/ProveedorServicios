package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.Servicios.UsuarioServicio;
import ProveedorServicios.demo.Entidades.Proveedor;
import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.servicios.ProveedorServicios;
import java.util.ArrayList;
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
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private ProveedorServicios proveedorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

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

    @GetMapping("/registrar") //localhost:8080/usuario/registrar
    public String registrar(ModelMap modelo) {
        return "registro_usuario";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String pass, 
           MultipartFile imagen ,@RequestParam String pass2,@RequestParam String dni,
           @RequestParam String direccion,@RequestParam String telefono, ModelMap modelo) {

        try {
            usuarioServicio.crearUsuario(nombre, email, pass, pass2, imagen, dni, direccion,telefono);

            modelo.put("exito", "El usuario se ha guardado con éxito");
            return "redirect:../index.html";

        } catch (MiException ex) {

            //List<String> proveedores = Arrays.asList("Plomero", "Gasista", "Electricista", "Pintor", "Cerrajero", "Jardinero", "Piletero", "Albañil");
            //new ArrayList<MyEnum>(Arrays.asList(MyEnum.values()));
            modelo.put("error", ex.getMessage());
            return "registro_usuario";
        }

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));

        return "../index";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable String id, String nombre, String email, ModelMap modelo) {
        usuarioServicio.editarUsuario(id, nombre, email);
        return "redirect:../lista";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:../lista";
    }

}
