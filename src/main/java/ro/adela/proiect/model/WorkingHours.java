package ro.adela.proiect.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="WorkingHours")
public class WorkingHours {
    @Id
    @Column
    private UUID id;

    @Column
    private String startHour;

    @Column
    private String endHour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "day_of_week_id", nullable = false)
    private DayOfWeek dayOfWeek;

    public UUID getId() {
        return id;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
