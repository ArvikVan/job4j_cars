package models;

import javax.persistence.*;
import java.util.ArrayList;
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
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "bodies_id")
    private Body body;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Photo> photoList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "brands_id", insertable = false, updatable = false)
    private Brand brands;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id", insertable = false, updatable = false)
    private Mark mark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bodies_id", insertable = false, updatable = false)
    private Body bodies;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private User users;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Body getBodies() {
        return bodies;
    }

    public void setBodies(Body bodies) {
        this.bodies = bodies;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Brand getBrands() {
        return brands;
    }

    public void setBrands(Brand brands) {
        this.brands = brands;
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
}
