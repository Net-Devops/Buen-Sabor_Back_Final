package NetDevops.BuenSabor.contoller;

import NetDevops.BuenSabor.service.util.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticaService estadisticaService;


}