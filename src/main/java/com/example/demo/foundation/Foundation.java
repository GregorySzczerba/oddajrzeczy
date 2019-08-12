package com.example.demo.foundation;

import com.example.demo.Category.Category;
import com.example.demo.Gifts.Gifts;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "foundations")
public class Foundation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    private String street;

    private String city;

    private String name;

    @OneToMany(mappedBy = "foundation", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Gifts> giftsList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Gifts> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(List<Gifts> giftsList) {
        this.giftsList = giftsList;
    }

    @Override
    public String toString() {
        return "foundation{" +
                "id=" + id +
                ", category=" + category +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}