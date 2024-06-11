package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(AddressRepository addressRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = prepareUser(null, userDTO);
        userRepository.save(user);
    }

    private User prepareUser(User userDetail, UserDTO userDTO) {
        User user = Objects.nonNull(userDetail) ? userDetail : new User();
        user.setActive(Boolean.TRUE);
        user.setManager(prepareAdminUser(userDTO));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setNationalCode(userDTO.getNationalCode());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(prepareHashedPassword(userDTO));
        user.setAddresses(prepareAddress(userDTO.getAddressDTO(), user));
        return user;
    }

    private List<Address> prepareAddress(AddressDTO addressDTO, User user) {
        List<Address> addressList = new ArrayList<>();
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setActive(Boolean.TRUE);
        address.setUser(user);
        addressList.add(address);
        return addressList;
    }

    private Boolean prepareAdminUser(UserDTO userDTO) {
        if (Objects.equals(userDTO.getNationalCode(), "3240005905")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private String prepareHashedPassword(UserDTO userDTO) {
        String password = userDTO.getPassword();
        return createHashedPassword(password);
    }

    private List<Address> saveAddress(List<Address> addressList) {
        for (Address address : addressList) {
            address.setActive(true);
            addressRepository.save(address);
        }
        return addressList;
    }

    private String createHashedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return encoder.encode(password);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDTO loadUserDTOByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return prepareUserDTO(user);
    }

    @Override
    public UserDTO loadUserDTOByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return prepareUserDTO(user);
    }

    @Override
    public void updateUser(String userName, UserDTO userDTO) throws Exception {
        User user = loadUserByUserName(userName);
        if (Objects.nonNull(user)) {
            user = prepareUser(user, userDTO);
            userRepository.save(user);
        } else {
            throw new Exception("user not found with userName " + userName);
        }
    }

    private UserDTO prepareUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setManager(user.getManager());
        userDTO.setNationalCode(user.getNationalCode());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddressDTO(prepareAddressDTO(user.getAddresses()));
        return userDTO;
    }

    private AddressDTO prepareAddressDTO(List<Address> addressList) {
        Address address = addressList.stream().findFirst().orElse(null);
        return modelMapper.map(address, AddressDTO.class);
    }
}
