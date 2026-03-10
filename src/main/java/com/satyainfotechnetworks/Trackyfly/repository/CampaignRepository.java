package com.satyainfotechnetworks.Trackyfly.repository;

import com.satyainfotechnetworks.Trackyfly.model.Campaign;
import com.satyainfotechnetworks.Trackyfly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByUser(User user);

    Optional<Campaign> findByCampaignId(String campaignId);
}
