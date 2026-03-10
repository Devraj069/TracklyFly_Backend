package com.satyainfotechnetworks.Trackyfly.service;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private CampaignClickRepository clickRepository;

    @Autowired
    private InstallRepository installRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    public Map<String, Object> getDashboardStats(User user) {
        List<App> myApps = appRepository.findByUser(user);
        List<String> appIds = myApps.stream().map(App::getAppId).collect(Collectors.toList());
        List<String> sdkKeys = myApps.stream().map(App::getSdkKey).collect(Collectors.toList());

        long totalClicks = 0;
        long totalInstalls = 0;
        long totalEvents = 0;
        double totalRevenue = 0;
        long totalCampaigns = campaignRepository.findByUser(user).size();

        if (!appIds.isEmpty()) {
            totalClicks = clickRepository.countByAppIdIn(appIds);
        }

        if (!sdkKeys.isEmpty()) {
            totalInstalls = installRepository.countBySdkKeyIn(sdkKeys);
            totalEvents = eventRepository.countBySdkKeyIn(sdkKeys);
            Double revenue = eventRepository
                    .sumEventValueByEventNamesAndSdkKeyIn(java.util.Arrays.asList("purchase", "ad_revenue"), sdkKeys);
            totalRevenue = revenue != null ? revenue : 0;
        }

        double conversionRate = totalClicks > 0 ? (double) totalInstalls / totalClicks * 100 : 0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalClicks", totalClicks);
        stats.put("totalInstalls", totalInstalls);
        stats.put("totalEvents", totalEvents);
        stats.put("totalRevenue", totalRevenue);
        stats.put("totalCampaigns", totalCampaigns);
        stats.put("conversionRate", Math.round(conversionRate * 100.0) / 100.0);

        return stats;
    }
}
