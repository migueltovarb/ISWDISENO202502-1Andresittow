package com.inventoryfast.scheduler;

import com.inventoryfast.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertScheduler {

    @Autowired
    private AlertService alertService;

    // Run every 10 minutes
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void checkLowStockPeriodically() {
        alertService.checkAndGenerateLowStockAlerts();
    }
}