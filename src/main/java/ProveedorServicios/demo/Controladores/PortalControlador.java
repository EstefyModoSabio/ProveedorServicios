package ProveedorServicios.demo.Controladores;

import ProveedorServicios.demo.Entidades.Proveedor;
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
        return "loginUsuario";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_ADMINISTRADOR', 'ROLE_PROFESIONAL')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        
        if (logueado.getRol().toString().equals("ADMINISTRADOR")) {
            return "admin/dashboard";
        } else {
            return "index";
        }
    }

    @GetMapping("/sobreNosotros")
    public String sobreNosotros() {
        return "SobreNosotros";
    }

}
