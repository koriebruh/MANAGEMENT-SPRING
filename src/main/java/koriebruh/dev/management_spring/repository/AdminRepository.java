package koriebruh.dev.management_spring.repository;

import koriebruh.dev.management_spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByUsername(String username);

    boolean existsByUsername(String username);
}
