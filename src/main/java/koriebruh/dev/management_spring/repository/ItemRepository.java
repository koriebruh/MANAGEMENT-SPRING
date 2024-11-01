package koriebruh.dev.management_spring.repository;

import koriebruh.dev.management_spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
