
package ProveedorServicios.demo.Servicios;


import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService {
    
     @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }

    @Transactional
    public Usuario crearUsuario(String dni, String nombre, String direccion, String telefono,
            String password, String email, Rol rol) throws MiException {

        validar(dni, nombre, direccion, telefono, password, email, rol);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setDireccion(direccion);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(rol);

        usuarioRepositorio.save(usuario);
        return usuario;
    }
    
    public void validar(String dni, String nombre, String direccion, String telefono,
            String password, String email, Rol rol) throws MiException {

        if (dni.isEmpty()) {
            throw new MiException("dni nulo o invalido");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("nombre nulo o invalido");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("direccion nulo o invalido");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("telefono nulo o invalido");
        }
        if (password.isEmpty() || password == null) {
            throw new MiException("password nulo o invalido");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("email nulo o invalido");
        }
        if (rol == null || rol == null) {
            throw new MiException("rol nulo o invalido");
        }
    }
    
    
    
}
