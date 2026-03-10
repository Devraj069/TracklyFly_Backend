package com.satyainfotechnetworks.Trackyfly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "installs")
@Data
public class Install {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String sdkKey;
    private String clickId; // Matched from CampaignClick
    private String packageName;

    private Long installTimestamp;

    private LocalDateTime createdAt = LocalDateTime.now();
}
