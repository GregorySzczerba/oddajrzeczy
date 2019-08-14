package com.example.demo.Gifts;

import com.example.demo.User.User;
import com.example.demo.foundation.Foundation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name =  "gifts")
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gift_id")
    private Long id;
    private int bagsNumber;
    @ManyToMany
    private List<TypeOfGift> typeOfGifts;
    private String street;
    private String city;
    private String zipCode;
    private int phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "foundation")
    private Foundation foundation;

    private LocalDateTime pickedUpByCarrier;

    public LocalDateTime getPickedUpByCarrier() {
        return pickedUpByCarrier;
    }

    public void setPickedUpByCarrier(LocalDateTime pickedUpByCarrier) {
        this.pickedUpByCarrier = pickedUpByCarrier;
    }

    public boolean isPickedUpOrNot() {
        return pickedUpOrNot;
    }

    public void setPickedUpOrNot(boolean pickedUpOrNot) {
        this.pickedUpOrNot = pickedUpOrNot;
    }

    private boolean pickedUpOrNot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBagsNumber() {
        return bagsNumber;
    }

    public void setBagsNumber(int bagsNumber) {
        this.bagsNumber = bagsNumber;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "Gifts{" +
                "id=" + id +
                ", bagsNumber=" + bagsNumber +
                ", typeOfGifts=" + typeOfGifts +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone=" + phone +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                ", user=" + user +
                ", foundation=" + foundation +
                ", pickedUpOrNot=" + pickedUpOrNot +
                '}';
    }
}
