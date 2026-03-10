package com.satyainfotechnetworks.Trackyfly.controller;

import com.satyainfotechnetworks.Trackyfly.model.Campaign;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.UserRepository;
import com.satyainfotechnetworks.Trackyfly.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Campaign> getMyCampaigns() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return campaignService.getCampaignsByUser(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCampaign(@RequestBody CampaignRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Campaign campaign = campaignService.createCampaign(user, request.getName(), request.getAppId(),
                request.getMediaSource());
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        try {
            campaignService.deleteCampaign(user, id);
            return ResponseEntity.ok("Campaign deleted");
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    public static class CampaignRequest {
        private String name;
        private Long appId;
        private String mediaSource;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getAppId() {
            return appId;
        }

        public void setAppId(Long appId) {
            this.appId = appId;
        }

        public String getMediaSource() {
            return mediaSource;
        }

        public void setMediaSource(String mediaSource) {
            this.mediaSource = mediaSource;
        }
    }
}
