package com.satyainfotechnetworks.Trackyfly.service;

import com.satyainfotechnetworks.Trackyfly.model.App;
import com.satyainfotechnetworks.Trackyfly.model.User;
import com.satyainfotechnetworks.Trackyfly.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppService {

    @Autowired
    private AppRepository appRepository;

    public List<App> getAppsByUser(User user) {
        return appRepository.findByUser(user);
    }

    public App registerApp(User user, String appName, String packageName) {
        if (appRepository.existsByPackageName(packageName)) {
            throw new RuntimeException("Package name already registered!");
        }

        App app = new App();
        app.setAppName(appName);
        app.setPackageName(packageName);
        app.setAppId(UUID.randomUUID().toString().substring(0, 8));
        app.setSdkKey("tf_" + UUID.randomUUID().toString().replace("-", ""));
        app.setUser(user);

        return appRepository.save(app);
    }

    public void deleteApp(User user, Long id) {
        App app = appRepository.findById(id).orElseThrow();
        if (!app.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized delete attempt");
        }
        appRepository.delete(app);
    }
}
