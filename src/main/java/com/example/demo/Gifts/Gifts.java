package com.example.demo.Gifts;

import com.example.demo.User.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name =  "gifts")
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gift_id")
    private int id;
    @ElementCollection
    private List<String> typeOfGift;
    private int quantityOfGifts;
    private String street;
    private String city;
    private String postCode;
    private int phoneNumber;
    private Date date;
    private LocalDateTime time;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public List<String> getTypeOfGift() {
        return typeOfGift;
    }

    public void setTypeOfGift(List<String> typeOfGift) {
        this.typeOfGift = typeOfGift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityOfGifts() {
        return quantityOfGifts;
    }

    public void setQuantityOfGifts(int quantityOfGifts) {
        this.quantityOfGifts = quantityOfGifts;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
