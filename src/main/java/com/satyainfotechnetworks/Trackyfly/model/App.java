package com.satyainfotechnetworks.Trackyfly.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "apps")
@Data
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String appId; // unique identifier

    private String packageName;
    private String appName;

    @Column(unique = true)
    private String sdkKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();
}
