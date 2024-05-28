package NetDevops.BuenSabor.security.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public abstract class EntityManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
