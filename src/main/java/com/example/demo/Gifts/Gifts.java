package com.example.demo.Gifts;

import com.example.demo.Organisation.Organisation;
import com.example.demo.User.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name =  "gifts")
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gift_id")
    private Long id;
    private int quantityOfGifts;
    @ManyToMany
    @JoinColumn(name = "type_of_gift_id")
    private List<TypeOfGift> typeOfGifts;
    private String street;
    private String city;
    private String postCode;
    private int phoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime time;
    private String remarks;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityOfGifts() {
        return quantityOfGifts;
    }

    public void setQuantityOfGifts(int quantityOfGifts) {
        this.quantityOfGifts = quantityOfGifts;
    }

    public List<TypeOfGift> getTypeOfGifts() {
        return typeOfGifts;
    }

    public void setTypeOfGifts(List<TypeOfGift> typeOfGifts) {
        this.typeOfGifts = typeOfGifts;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    @Override
    public String toString() {
        return "Gifts{" +
                "id=" + id +
                ", quantityOfGifts=" + quantityOfGifts +
                ", typeOfGifts=" + typeOfGifts +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", date=" + date +
                ", time=" + time +
                ", remarks='" + remarks + '\'' +
                ", user=" + user +
                ", organisation=" + organisation +
                '}';
    }
}
