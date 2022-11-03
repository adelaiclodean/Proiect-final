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
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    private String mapLocationSrc;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_restaurant_type",
            joinColumns = @JoinColumn(name="restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_type_id")
    )
    private List<RestaurantType> restaurantTypes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<WorkingHours> workingHours;

    public Restaurant() {
    }

    public Restaurant(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImageName() {
        return imageName;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMapLocationSrc() {
        return mapLocationSrc;
    }

    public List<RestaurantType> getRestaurantTypes() {
        return restaurantTypes;
    }

    public List<WorkingHours> getWorkingHours() {
        return workingHours;
    }
}
