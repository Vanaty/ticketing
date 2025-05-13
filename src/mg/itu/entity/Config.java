package mg.itu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer reservationBeforeHours;
    Integer cancellationBeforeHours;

    public static Config preparerDefault() {
        Config c = new Config();
        c.setCancellationBeforeHours(0);
        c.setReservationBeforeHours(0);
        return c;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getReservationBeforeHours() {
        return reservationBeforeHours;
    }
    public void setReservationBeforeHours(Integer reservationBeforeHours) {
        this.reservationBeforeHours = reservationBeforeHours;
    }
    public Integer getCancellationBeforeHours() {
        return cancellationBeforeHours;
    }
    public void setCancellationBeforeHours(Integer cancellationBeforeHours) {
        this.cancellationBeforeHours = cancellationBeforeHours;
    }
}
