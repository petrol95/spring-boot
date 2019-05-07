package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Role;
import com.geekbrains.springboot.entities.SystemUser;
import com.geekbrains.springboot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUserName (String userName);

    void save(SystemUser systemUser);

    /////////////////////////////////
    List<Role> getAllRoles();
}
