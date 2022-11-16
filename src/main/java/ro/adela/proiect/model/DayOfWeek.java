package ro.adela.proiect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DayOfWeek")
public class DayOfWeek {
    @Id
    @Column
    int dayId;
    @Column
    String dayName;

    public int getDayId() {
        return dayId;
    }

    public String getDayName() {
        return dayName;
    }
}
