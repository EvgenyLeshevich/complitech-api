package com.evgeniy.complitechapi.service;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledService {

    private final ConfigurableApplicationContext context;
    private final HikariDataSource hikariDataSource;

    @Value("${date_for_stop_app}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Scheduled(fixedRateString = "${fixedRate}")
    public void checkDateFromProperties() {
        if (date.isBefore(LocalDateTime.now())) {
            hikariDataSource.close();
            context.close();
        }
    }
}
