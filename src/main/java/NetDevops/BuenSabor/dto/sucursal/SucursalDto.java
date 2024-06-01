package NetDevops.BuenSabor.dto.sucursal;

import NetDevops.BuenSabor.dto.BaseDto;
import NetDevops.BuenSabor.entities.Empresa;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SucursalDto extends BaseDto {

    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private String calle;
    private String numero;
    private String cp;
    private String piso;
    private String nroDepto;
    private String localidad;
    private String provincia;
    private String Pais;
    private Empresa empresa;
    private String imagen;

}
