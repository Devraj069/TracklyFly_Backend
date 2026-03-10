package com.satyainfotechnetworks.Trackyfly.repository;

import com.satyainfotechnetworks.Trackyfly.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    long countBySdkKeyIn(java.util.List<String> sdkKeys);

    @org.springframework.data.jpa.repository.Query("SELECT SUM(e.eventValue) FROM Event e WHERE e.eventName IN :eventNames AND e.sdkKey IN :sdkKeys")
    Double sumEventValueByEventNamesAndSdkKeyIn(java.util.List<String> eventNames, java.util.List<String> sdkKeys);
}
