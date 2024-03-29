package com.example.medicalbookjavanew.dao;

import com.example.medicalbookjavanew.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
