package NetDevops.BuenSabor.service.impl;

import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import NetDevops.BuenSabor.repository.IUsuarioClienteRepository;
import NetDevops.BuenSabor.repository.IUsuarioEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private IUsuarioEmpleadoRepository usuarioEmpleadoRepository;
    @Autowired
    private IUsuarioClienteRepository usuarioClienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEmpleado usuarioEmpleado = usuarioEmpleadoRepository.findUsuarioEmpleadoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El Usuario "+ username +", no fue encontrado"));



        return null;
    }
}
