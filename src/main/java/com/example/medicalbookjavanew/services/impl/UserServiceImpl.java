package com.example.medicalbookjavanew.services.impl;

import com.example.medicalbookjavanew.dao.UserDao;
import com.example.medicalbookjavanew.entity.User;
import com.example.medicalbookjavanew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        if (user != null) {
            userDao.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOneById(Integer id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username);
    }
}
