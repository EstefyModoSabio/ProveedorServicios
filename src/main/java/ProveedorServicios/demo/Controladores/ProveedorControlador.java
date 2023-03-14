/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProveedorServicios.demo.controladores;

import ProveedorServicios.demo.Enums.Profesion;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.servicios.ProveedorServicios;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicios proveedorServicio;

    @GetMapping("/registrar") //localhost:8080/proveedor/registrar
    public String registrar(){
        return "proveedor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String cuit,@RequestParam String nombre,@RequestParam String telefono, @RequestParam String email,@RequestParam Profesion profesion){

        try {
            proveedorServicio.crearProveedor(cuit, nombre, telefono, email, profesion);
        } catch (MiException ex) {
            Logger.getLogger(ProveedorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "proveedor_form.html";
        }

        return "index.html";
    }
}