package ro.adela.proiect.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Restaurants")
public class Restaurant {

    @Id
    @Column
    private UUID id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String imageName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_restaurant_type",
            joinColumns = @JoinColumn(name="restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_type_id")
    )
    private List<RestaurantType> restaurantTypes;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

}
