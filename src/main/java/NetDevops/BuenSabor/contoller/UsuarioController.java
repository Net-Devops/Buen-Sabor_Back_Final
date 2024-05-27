package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.entities.Cliente;
import NetDevops.BuenSabor.entities.Empleado;
import NetDevops.BuenSabor.entities.UsuarioCliente;
import NetDevops.BuenSabor.entities.UsuarioEmpleado;
import NetDevops.BuenSabor.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/clientes")
    public ResponseEntity<?> listarClientes() {
        try {
            return ResponseEntity.ok().body(usuarioService.TraerUsuariosClientes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleados() {
        try {
            return ResponseEntity.ok().body(usuarioService.TraerUsuariosEmpleados());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(usuarioService.buscarPorIdCliente(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> buscarEmpleadoPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(usuarioService.buscarPorIdEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cliente")
    public ResponseEntity<?> crearCliente(@RequestBody UsuarioCliente usuarioCliente) {
        try {
            return ResponseEntity.ok().body(usuarioService.crearUsuarioCliente(usuarioCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/empleado")
    public ResponseEntity<?> crearEmpleado(@RequestBody UsuarioEmpleado usuarioEmpleado) {
        try {
            return ResponseEntity.ok().body(usuarioService.crearUsuarioEmpleado(usuarioEmpleado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody UsuarioCliente usuarioCliente) {
        try {
            return ResponseEntity.ok().body(usuarioService.actualizarUsuarioCliente(id, usuarioCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id, @RequestBody UsuarioEmpleado usuarioEmpleado) {
        try {
            return ResponseEntity.ok().body(usuarioService.actualizarUsuarioEmpleado(id, usuarioEmpleado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(usuarioService.eliminarUsuarioCliente(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(usuarioService.eliminarUsuarioEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
