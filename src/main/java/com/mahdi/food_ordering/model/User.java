package com.mahdi.food_ordering.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @Column(name = "c_userName", nullable = false, length = 16)
    private String userName;
    @Column(name = "c_firstName", length = 45)
    private String firstName;
    @Column(name = "c_lastName", length = 45)
    private String lastName;
    @Column(name = "c_email", length = 60)
    private String email;
    @Column(name = "c_phoneNumber", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @Column(name = "c_password", nullable = false, length = 4000, unique = true)
    private String password;
    @Column(name = "c_nationalCode", length = 10)
    private String nationalCode;
    @Column(name = "c_manager")
    private Boolean manager;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_address", referencedColumnName = "id")
    private Address address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
