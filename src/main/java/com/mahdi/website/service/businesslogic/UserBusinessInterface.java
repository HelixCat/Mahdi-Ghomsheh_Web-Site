package com.mahdi.website.service.businesslogic;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;

import java.util.List;

public interface UserBusinessInterface {
    Boolean isValidPasswordForLogin(String plainPassword, String hashedPassword);
}
