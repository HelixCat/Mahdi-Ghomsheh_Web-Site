package com.mahdi.website.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "t_address")
public class Address extends BaseEntity {

    @Column(name = "c_country", length = 50)
    private String country;
    @Column(name = "c_province", length = 50)
    private String province;
    @Column(name = "c_city", length = 50)
    private String city;
    @Column(name = "c_postal_code" ,length = 10)
    private String postalCode;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
