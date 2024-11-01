package koriebruh.dev.management_spring.repository;

import koriebruh.dev.management_spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
