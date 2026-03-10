package com.satyainfotechnetworks.Trackyfly.repository;

import com.satyainfotechnetworks.Trackyfly.model.Install;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallRepository extends JpaRepository<Install, Long> {
    long countBySdkKeyIn(java.util.List<String> sdkKeys);
}
