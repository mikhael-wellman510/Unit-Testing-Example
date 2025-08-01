package com.example.Sport_Station.threadPool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Todo -> ini supaya bisa langsung mengerjakan beberapa tugas secara bersamaan
        executor.setCorePoolSize(5);            // Minimal thread aktif
        executor.setMaxPoolSize(10);            // Maksimal thread aktif
        executor.setQueueCapacity(100);         // Kapasitas antrean task
        executor.setThreadNamePrefix("MyExecutor-"); // Prefix nama thread (untuk debugging)
        executor.initialize();
        return executor;
    }
}
