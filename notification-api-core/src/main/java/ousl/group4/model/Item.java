package ousl.group4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String itemId;
    private User user;
    private String name;
    private String smsSearchName;
    private String description;
    private String condition;
    private String imagePath;
    private Date createdOn;
    private boolean status;
    private List<Listing> listings = new ArrayList<Listing>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "item_id")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sms_search_name")
    public String getSmsSearchName() {
        return smsSearchName;
    }

    public void setSmsSearchName(String smsSearchName) {
        this.smsSearchName = smsSearchName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "condition_id")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "created_on")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

}
