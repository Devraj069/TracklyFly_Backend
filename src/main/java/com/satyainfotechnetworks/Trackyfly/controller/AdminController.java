package com.satyainfotechnetworks.Trackyfly.controller;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.AppRepository;
import com.satyainfotechnetworks.Trackyfly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/apps")
    public List<App> getAllApps() {
        return appRepository.findAll();
    }
}
