package ro.adela.proiect.model;

import javax.persistence.*;
import java.util.UUID;

    @Entity
    @Table(name="Reviews")
    public class Review {

        @Id
        @Column
        private UUID id;

        @Column(columnDefinition = "TEXT")
        private String review;

        @Column
        private int rating;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "restaurant_id", nullable = false)
        private Restaurant restaurant;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        public Review() {
        }

        public Review(UUID id, String review, int rating, Restaurant restaurant, User user) {
            this.id = id;
            this.review = review;
            this.rating = rating;
            this.restaurant = restaurant;
            this.user = user;
        }

        public int getRating() {
            return rating;
        }

        public UUID getId() {
            return id;
        }

        public String getReview() {
            return review;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public User getUser() {
            return user;
        }
    }
