package com.mahdi.website.dto;

import com.mahdi.website.model.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String fullName;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private Boolean manager;
    private String password;
    private AddressDTO addressDTO;

}
