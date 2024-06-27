package NetDevops.BuenSabor.entities;

import NetDevops.BuenSabor.enums.FormaPago;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@Data
@Audited
public class Factura extends Base{

    private LocalDate fechaFacturacion;
    private Integer mpPaymentId;
    private Integer mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private Double totalVenta;

}
