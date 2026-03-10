package com.satyainfotechnetworks.Trackyfly.controller;

import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.UserRepository;
import com.satyainfotechnetworks.Trackyfly.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/summary")
    public Map<String, Object> getDashboardStats() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        return statsService.getDashboardStats(user);
    }
}
