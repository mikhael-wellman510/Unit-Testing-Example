package com.example.Sport_Station.FirebaseService.controller;

import com.example.Sport_Station.FirebaseService.Dto.BiodataRequest;
import com.example.Sport_Station.FirebaseService.Service.BiodataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bio")
public class BiodataController {


    private final BiodataServiceImpl biodataService;

    @PostMapping("/save")
    public ResponseEntity<?>saveBiodata(@RequestBody BiodataRequest biodataRequest) throws ExecutionException, InterruptedException {

        var data = biodataService.addBio(biodataRequest);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?>findByIdBio(@PathVariable String id) throws ExecutionException, InterruptedException {

        var data = biodataService.findByIdBio(id);

        return ResponseEntity.ok(data);
    }

    @PutMapping("/updated")
    public ResponseEntity<?>updated(@RequestBody BiodataRequest biodataRequest){

        var data = biodataService.updatedBio(biodataRequest);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?>deleted(@PathVariable String id) throws ExecutionException, InterruptedException {
        var data = biodataService.deleted(id);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?>findALl() throws ExecutionException, InterruptedException {

        var data = biodataService.findAll();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/name")
    public ResponseEntity<?>findByName(@RequestParam(name = "name" , defaultValue = "")String name) throws ExecutionException, InterruptedException {

        var data = biodataService.findByName(name);

        return ResponseEntity.ok(data);
    }
}
