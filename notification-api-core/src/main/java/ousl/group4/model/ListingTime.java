package ousl.group4.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "listing_time")
public class ListingTime implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private float time;
    private boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "time")
    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
