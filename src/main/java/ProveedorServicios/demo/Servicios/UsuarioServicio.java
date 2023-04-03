package ProveedorServicios.demo.Servicios;

import ProveedorServicios.demo.Entidades.Imagen;
import ProveedorServicios.demo.Entidades.Usuario;
import ProveedorServicios.demo.Enums.Rol;
import ProveedorServicios.demo.Excepciones.MiException;
import ProveedorServicios.demo.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;
    
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
    public Usuario crearUsuario(String nombre, String email, String pass, String pass2, MultipartFile imagen,
                               String dni, String direccion, String telefono) throws MiException {

        validar(nombre, email, pass, pass2);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(pass));
        usuario.setRol(Rol.USUARIO);
        Imagen foto = imagenServicio.guardarImagen(imagen);
        usuario.setImagen(foto);
        usuario.setDni(dni);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuarioRepositorio.save(usuario);
        return usuario;
    }

    @Transactional
    public void editarUsuario(String id, String nombre, String email) {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);

            usuario.setEmail(email);

            usuarioRepositorio.save(usuario);
        }

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();

        return usuarios;
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepositorio.buscarPorNombre(nombre);
    }

    public Usuario getOne(String id) {

        return usuarioRepositorio.getOne(id);

    }

    public void eliminarUsuario(String id) {
        usuarioRepositorio.deleteById(id);
    }

    public void validar(String nombre, String email, String pass, String pass2) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("nombre nulo o invalido");
        }

        if (pass.isEmpty() || pass == null) {
            throw new MiException("password nulo o invalido");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("email nulo o invalido");
        }
        if (!pass2.equals(pass)) {
            throw new MiException("las contraseñas no coinciden");
        }
    }

}
