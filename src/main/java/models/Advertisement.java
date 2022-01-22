package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author ArvikV
 * @version 1.0
 * @since 21.01.2022
 */
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "sold")
    private Boolean sold;
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Photo> photoList = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brands_id", insertable = false, updatable = false)
    private Brand brands;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bodies_id", insertable = false, updatable = false)
    private Body bodies;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private User users;

    private boolean photo;

    public Advertisement() {
    }

    public Advertisement(String description, Boolean sold, Date created,
                         User users, boolean photo) {
        this.description = description;
        this.sold = sold;
        this.created = new Date(System.currentTimeMillis());
        this.users = users;
        this.photo = photo;
    }

    public static Advertisement of(String description, Boolean sold, boolean photo,
                                   Date created, Body bodies, Brand brands, User users) {
            Advertisement advertisement = new Advertisement();
            advertisement.description = description;
            advertisement.sold = sold;
            advertisement.photo = photo;
            advertisement.created = new Date(System.currentTimeMillis());
            advertisement.bodies = bodies;
            advertisement.brands = brands;
            advertisement.users = users;
            return  advertisement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Brand getBrands() {
        return brands;
    }

    public void setBrands(Brand brands) {
        this.brands = brands;
    }

    public Body getBodies() {
        return bodies;
    }

    public void setBodies(Body bodies) {
        this.bodies = bodies;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", sold=" + sold
                + ", created=" + created
                + ", brands=" + brands
                + ", bodies=" + bodies
                + ", users=" + users
                + ", photo=" + photo
                + '}';
    }
}
