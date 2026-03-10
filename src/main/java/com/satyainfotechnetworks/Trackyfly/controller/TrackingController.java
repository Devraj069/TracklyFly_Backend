package com.satyainfotechnetworks.Trackyfly.controller;

import com.satyainfotechnetworks.Trackyfly.model.Event;
import com.satyainfotechnetworks.Trackyfly.model.Install;
import com.satyainfotechnetworks.Trackyfly.service.TrackingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/click")
    public RedirectView handleClick(
            @RequestParam("app_id") String appId,
            @RequestParam(value = "campaign", required = false) String campaign,
            @RequestParam(value = "media_source", required = false) String mediaSource,
            HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");

        String clickId = trackingService.recordClick(appId, campaign, mediaSource, ip, ua);

        // Fetch real package name from DB
        com.satyainfotechnetworks.Trackyfly.model.App app = trackingService.getAppById(appId);
        String packageName = (app != null) ? app.getPackageName() : appId;

        // Redirect to Play Store with referrer
        String playStoreUrl = "https://play.google.com/store/apps/details?id=" + packageName + "&referrer=click_id%3D"
                + clickId;

        return new RedirectView(playStoreUrl);
    }

    @PostMapping("/install")
    public ResponseEntity<String> handleInstall(@RequestBody Install install) {
        if (trackingService.recordInstall(install)) {
            return ResponseEntity.ok("Install recorded");
        }
        return ResponseEntity.status(401).body("Invalid SDK Key");
    }

    @PostMapping("/event")
    public ResponseEntity<String> handleEvent(@RequestBody Event event) {
        if (trackingService.recordEvent(event)) {
            return ResponseEntity.ok("Event recorded");
        }
        return ResponseEntity.status(401).body("Invalid SDK Key");
    }
}
