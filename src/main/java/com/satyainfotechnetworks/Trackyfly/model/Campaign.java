package com.satyainfotechnetworks.Trackyfly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaigns")
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "app_id_fk", referencedColumnName = "id")
    private App app;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String mediaSource;

    @Column(unique = true)
    private String campaignId; // e.g. cp_abc123

    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean active = true;
}
