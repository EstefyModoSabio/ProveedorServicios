package ProveedorServicios.demo.Controladores;

import ProveedorServicios.demo.Entidades.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "usuario o contraseña incorrecto");
        }
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMINISTRADOR', 'ROLE_PROFESIONAL')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        switch (logueado.getRol().toString()) {
            case "ADMINISTRADOR":
                return "redirect:/admin/dashboard";
            case "USUARIO":
                return "home_usuario";
            default:
                return "home_proveedor";
        }
    }

    @GetMapping("/sobreNosotros")
    public String sobreNosotros(){
        return "SobreNosotros";
    }
    
    
}
