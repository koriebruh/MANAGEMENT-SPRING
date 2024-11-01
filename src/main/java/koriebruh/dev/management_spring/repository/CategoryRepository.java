package koriebruh.dev.management_spring.repository;

import koriebruh.dev.management_spring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
