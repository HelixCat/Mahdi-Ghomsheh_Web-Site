package com.mahdi.website.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @Column(name = "c_user_name", nullable = false, length = 16)
    private String userName;
    @Column(name = "c_first_name", length = 45)
    private String firstName;
    @Column(name = "c_last_name", length = 45)
    private String lastName;
    @Column(name = "c_email", length = 60)
    private String email;
    @Column(name = "c_phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @Column(name = "c_password", nullable = false, length = 4000, unique = true)
    private String password;
    @Column(name = "c_national_code", length = 10, nullable = false, unique = true)
    private String nationalCode;
    @Column(name = "c_manager")
    private Boolean manager;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

}



