package com.satyainfotechnetworks.Trackyfly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaign_clicks")
@Data
public class CampaignClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clickId; // Unique ID generated for the click
    private String appId;
    private String campaign;
    private String mediaSource;

    private String ipAddress;
    private String userAgent;

    private LocalDateTime createdAt = LocalDateTime.now();
}
