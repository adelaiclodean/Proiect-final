package ro.adela.proiect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adela.proiect.model.Restaurant;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRestaurantRepository extends JpaRepository<Restaurant, UUID> {

    List<Restaurant> findByNameContainingIgnoreCase (String name);

}
