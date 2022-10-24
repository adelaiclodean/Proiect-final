package ro.adela.proiect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="RestaurantTypes")
public class RestaurantType {

    @Id
    @Column
    private UUID id;

    @Column
    private String name;



    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
