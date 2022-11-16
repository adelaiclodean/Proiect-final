package ro.adela.proiect.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="Bookings")
public class Booking {
    @Id
    @Column
    private UUID id;
    @Column
    private int numberOfPeople;
    @Column
    private LocalDateTime BookingTime;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant Restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Booking() {
    }

    public Booking(UUID id, int numberOfPeople, LocalDateTime bookingTime, Restaurant restaurant, User user) {
        this.id = id;
        this.numberOfPeople = numberOfPeople;
        BookingTime = bookingTime;
        Restaurant = restaurant;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public LocalDateTime getBookingTime() {
        return BookingTime;
    }

    public Restaurant getRestaurant() {
        return Restaurant;
    }

    public User getUser() {
        return user;
    }
}
