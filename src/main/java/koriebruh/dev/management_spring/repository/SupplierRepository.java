package koriebruh.dev.management_spring.repository;

import koriebruh.dev.management_spring.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
