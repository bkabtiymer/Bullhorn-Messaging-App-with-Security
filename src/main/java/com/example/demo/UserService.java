package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email)
    {
        return userRepository.countByEmail(email);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    // Returns the currently logged in User object
//    public User getCurrentUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentusername = authentication.getName();
//        User user = userRepository.findByUsername(currentusername);
//        return user;
//    }

    public void saveUser(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentusername = authentication.getName();

        User user = userRepository.findByUsername(currentusername);

        return user;
    }
}
