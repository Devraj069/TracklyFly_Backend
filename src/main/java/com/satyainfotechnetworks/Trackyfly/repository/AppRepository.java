package com.satyainfotechnetworks.Trackyfly.repository;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByAppId(String appId);

    Optional<App> findBySdkKey(String sdkKey);

    List<App> findByUser(User user);

    boolean existsByPackageName(String packageName);
}
