package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.entidades.Proveedor;
import ProveedorServicios.demo.servicios.ProveedorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/buscar")
    public String resultadoBusqueda(ModelMap modelo, @RequestParam String busqueda) {
        try {
            List<Proveedor> proveedores = proveedorServicio.listarProveedoresPorOficio(busqueda.toUpperCase());
            modelo.addAttribute("proveedores", proveedores);
            return "proveedor_list.html";

        } catch (Exception e) {
            return "index.html";
        }

    }

}
