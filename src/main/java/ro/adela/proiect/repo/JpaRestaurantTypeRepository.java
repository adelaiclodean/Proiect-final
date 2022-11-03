package ro.adela.proiect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adela.proiect.model.RestaurantType;

import java.util.UUID;

@Repository
public interface JpaRestaurantTypeRepository extends JpaRepository<RestaurantType, UUID> {

}
