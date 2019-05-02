package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.SystemUser;
import com.geekbrains.springboot.entities.User;
import com.geekbrains.springboot.repositories.RoleRepository;
import com.geekbrains.springboot.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public void save(SystemUser systemUser) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
