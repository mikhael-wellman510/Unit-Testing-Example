package com.example.Sport_Station.threadPool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RunAsyncTask {


    @Async("taskExecutor")
    public void runAsyncTask(int taskNumber){
        log.info("Task number : {}  , Running on thread : {} ",taskNumber , Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task " + taskNumber + " finished.");
    }
}
