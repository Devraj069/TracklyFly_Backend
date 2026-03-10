package com.satyainfotechnetworks.Trackyfly.repository;

import com.satyainfotechnetworks.Trackyfly.model.CampaignClick;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CampaignClickRepository extends JpaRepository<CampaignClick, Long> {
    Optional<CampaignClick> findByClickId(String clickId);

    long countByAppIdIn(java.util.List<String> appIds);
}
