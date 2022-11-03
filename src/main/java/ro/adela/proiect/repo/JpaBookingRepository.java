package ro.adela.proiect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.adela.proiect.model.Booking;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaBookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser_Username(String username);
}
