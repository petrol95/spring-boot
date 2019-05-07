package com.geekbrains.springboot.services;

import com.geekbrains.springboot.config.RoleType;
import com.geekbrains.springboot.entities.Role;
import com.geekbrains.springboot.entities.SystemUser;
import com.geekbrains.springboot.entities.User;
import com.geekbrains.springboot.repositories.RoleRepository;
import com.geekbrains.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


   /* private UserRepository userRepository;
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
    }*/

   //////////////////////////////////////////////////////////////////////////

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Override
    @Transactional
    public void save(SystemUser systemUser) {
        User user = new User();
        user.setUserName(systemUser.getUserName());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        String role = RoleType.EMPLOYEE.getRole();
        if (systemUser.getUserRole() != null) {
            role = systemUser.getUserRole();
        }
        user.setRoles(Collections.singletonList(roleRepository.findOneByName(role)));

        userRepository.save(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////
}
