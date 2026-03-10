package com.satyainfotechnetworks.Trackyfly.service;

import com.satyainfotechnetworks.Trackyfly.model.*;
import com.satyainfotechnetworks.Trackyfly.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrackingService {

    @Autowired
    private CampaignClickRepository clickRepository;

    @Autowired
    private InstallRepository installRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AppRepository appRepository;

    public App getAppById(String appId) {
        return appRepository.findByAppId(appId).orElse(null);
    }

    public String recordClick(String appId, String campaign, String mediaSource, String ip, String ua) {
        String clickId = UUID.randomUUID().toString();
        CampaignClick click = new CampaignClick();
        click.setClickId(clickId);
        click.setAppId(appId);
        click.setCampaign(campaign);
        click.setMediaSource(mediaSource);
        click.setIpAddress(ip);
        click.setUserAgent(ua);
        clickRepository.save(click);
        return clickId;
    }

    public boolean recordInstall(Install install) {
        if (appRepository.findBySdkKey(install.getSdkKey()).isPresent()) {
            installRepository.save(install);
            return true;
        }
        return false;
    }

    public boolean recordEvent(Event event) {
        if (appRepository.findBySdkKey(event.getSdkKey()).isPresent()) {
            eventRepository.save(event);
            return true;
        }
        return false;
    }
}
