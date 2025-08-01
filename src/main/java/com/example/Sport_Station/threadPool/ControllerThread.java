package com.example.Sport_Station.threadPool;

import com.example.Sport_Station.threadPool.service.RunAsyncTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ControllerThread {

    private final RunAsyncTask runAsyncTask;

    @GetMapping("/cek")
    public void testAsync(){
        for (int i = 1; i <= 10; i++) {
            runAsyncTask.runAsyncTask(i);
        }
    }
}
