package com.satyainfotechnetworks.Trackyfly.service;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.Campaign;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.AppRepository;
import com.satyainfotechnetworks.Trackyfly.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private AppRepository appRepository;

    public List<Campaign> getCampaignsByUser(User user) {
        return campaignRepository.findByUser(user);
    }

    public Campaign createCampaign(User user, String name, Long appId, String mediaSource) {
        App app = appRepository.findById(appId).orElseThrow();

        Campaign campaign = new Campaign();
        campaign.setName(name);
        campaign.setApp(app);
        campaign.setMediaSource(mediaSource);
        campaign.setUser(user);
        campaign.setCampaignId("cp_" + UUID.randomUUID().toString().substring(0, 8));

        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(User user, Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow();
        if (!campaign.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized delete attempt");
        }
        campaignRepository.delete(campaign);
    }
}
