package com.example.Sport_Station.FirebaseService.controller;

import com.example.Sport_Station.FirebaseService.Entity.Mahasiswa;
import com.example.Sport_Station.FirebaseService.Repository.MahasiswaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mhs")
public class MahasiswaController {

    private final MahasiswaRepository mahasiswaRepository;

    @PostMapping("/save")
    public ResponseEntity<?>saveMahasiswa(@RequestBody Mahasiswa mahasiswa) throws ExecutionException, InterruptedException {

        var data = mahasiswaRepository.saveMahasiswa(mahasiswa);

        return ResponseEntity.ok(data);
    }

    @PostMapping("/saved")
    public ResponseEntity<?>save(@RequestBody Mahasiswa mahasiswa) {
        var data = mahasiswaRepository.save(mahasiswa);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?>findById(@PathVariable String id) throws ExecutionException, InterruptedException {
        var data = mahasiswaRepository.findById(id);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?>findAll() throws ExecutionException, InterruptedException {

        var data = mahasiswaRepository.findAll();

        return ResponseEntity.ok(data);
    }

    @PutMapping("/updated")
    public ResponseEntity<?>updated(@RequestBody Mahasiswa mahasiswa) throws ExecutionException, InterruptedException {

        var data = mahasiswaRepository.updated(mahasiswa);

        return ResponseEntity.ok(data);
    }



    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?>deleted(@PathVariable String id) throws ExecutionException, InterruptedException {

        var data = mahasiswaRepository.deleted(id);

        return ResponseEntity.ok(data);
    }
}
