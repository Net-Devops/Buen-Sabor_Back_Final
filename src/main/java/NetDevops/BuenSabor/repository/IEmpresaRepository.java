package NetDevops.BuenSabor.repository;

import NetDevops.BuenSabor.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
    boolean existsByCuil(Long cuil);
}
