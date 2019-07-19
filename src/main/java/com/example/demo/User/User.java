package com.example.demo.User;

import com.example.demo.ConfirmationToken.ConfirmationToken;
import com.example.demo.Gifts.Gifts;
import com.example.demo.role.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
    @Email(message = "*Podaj prawidłowy email")
    @NotEmpty(message = "*Podaj email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Hasło musi mieć przynajmniej 5 znaków")
    @NotEmpty(message = "*Podaj hasło.")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Podaj imię")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Podaj nazwisko.")
    private String lastName;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private ConfirmationToken confirmationToken;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Gifts> giftsList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ConfirmationToken getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(ConfirmationToken confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public List<Gifts> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(List<Gifts> giftsList) {
        this.giftsList = giftsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}



