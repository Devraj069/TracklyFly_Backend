package com.satyainfotechnetworks.Trackyfly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String sdkKey;
    private String eventName;
    private Double eventValue;

    private Long eventTimestamp;

    private LocalDateTime createdAt = LocalDateTime.now();
}
