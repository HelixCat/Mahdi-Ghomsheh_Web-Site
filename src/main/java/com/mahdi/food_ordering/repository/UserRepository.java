package com.mahdi.food_ordering.repository;

import com.mahdi.food_ordering.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
