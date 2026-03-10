package com.satyainfotechnetworks.Trackyfly.controller;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.UserRepository;
import com.satyainfotechnetworks.Trackyfly.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apps")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElseThrow();
    }

    @GetMapping
    public List<App> getMyApps() {
        return appService.getAppsByUser(getCurrentUser());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerApp(@RequestBody App request) {
        try {
            App app = appService.registerApp(getCurrentUser(), request.getAppName(), request.getPackageName());
            return ResponseEntity.ok(app);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApp(@PathVariable Long id) {
        try {
            appService.deleteApp(getCurrentUser(), id);
            return ResponseEntity.ok("App deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
