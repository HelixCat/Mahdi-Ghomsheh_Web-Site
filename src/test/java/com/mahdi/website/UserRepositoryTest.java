package com.mahdi.website;

import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.security.SecureRandom;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void addUserTest() {
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword = encoder.encode("Ngc@1368");
        user.setEmail("Kitkat.helix@gmail.com");
        user.setUsername("Ngc1368");
        user.setPassword(encodedPassword);
        user.setFirstName("Mahdi");
        user.setLastName("Ghomsheh");
        user.setNationalCode("3240005905");
        repository.updateUserPassword("Ngc1368", encodedPassword);
    }
}
