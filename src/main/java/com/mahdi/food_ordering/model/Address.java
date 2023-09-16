package com.mahdi.food_ordering.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_address")
public class Address extends BaseEntity {

    @Column(name = "c_country", length = 50)
    private String country;
    @Column(name = "c_province", length = 50)
    private String province;
    @Column(name = "c_city", length = 50)
    private String city;
    @Column(name = "c_postalCode" ,length = 10)
    private String postalCode;
    @OneToOne(mappedBy = "address")
    private User user;
}
