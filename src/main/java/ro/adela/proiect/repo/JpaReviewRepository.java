package ro.adela.proiect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.adela.proiect.model.Review;

import java.util.UUID;

public interface JpaReviewRepository extends JpaRepository<Review, UUID> {

}
