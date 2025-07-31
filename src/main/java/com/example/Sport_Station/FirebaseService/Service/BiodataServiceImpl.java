package com.example.Sport_Station.FirebaseService.Service;

import com.example.Sport_Station.FirebaseService.Dto.BiodataRequest;
import com.example.Sport_Station.FirebaseService.Dto.BiodataResponse;
import com.example.Sport_Station.FirebaseService.Entity.Biodata;
import com.example.Sport_Station.FirebaseService.Repository.BiodataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class BiodataServiceImpl {

    private final BiodataRepository biodataRepository;

    public BiodataResponse addBio(BiodataRequest biodataRequest) throws ExecutionException, InterruptedException {

        Biodata saveBio = biodataRepository.save(Biodata.builder()
                        .name(biodataRequest.getName())
                        .address(biodataRequest.getAddress())
                        .age(biodataRequest.getAge())
                        .hobby(biodataRequest.getHobby())
                        .height(biodataRequest.getHeight())
                        .createdAt(new Date())
                        .updatedAt(new Date())
                .build());

        return BiodataResponse.builder()
                .id(saveBio.getId())
                .name(saveBio.getName())
                .address(saveBio.getAddress())
                .age(saveBio.getAge())
                .hobby(saveBio.getHobby())
                .height(saveBio.getHeight())
                .createdAt(saveBio.getCreatedAt())
                .updatedAt(saveBio.getUpdatedAt())
                .build();
    }


    public BiodataResponse findByIdBio(String id) throws ExecutionException, InterruptedException {

        try {
            Biodata find = biodataRepository.findById(id);

            return BiodataResponse.builder()
                    .id(find.getId())
                    .name(find.getName())
                    .address(find.getAddress())
                    .age(find.getAge())
                    .hobby(find.getHobby())
                    .height(find.getHeight())
                    .createdAt(find.getCreatedAt())
                    .updatedAt(find.getCreatedAt())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public BiodataResponse updatedBio(BiodataRequest biodataRequest){
        try {


            Biodata updated = biodataRepository.updated(Biodata.builder()
                            .id(biodataRequest.getId())
                            .name(biodataRequest.getName())
                            .address(biodataRequest.getAddress())
                            .age(biodataRequest.getAge())
                            .hobby(biodataRequest.getHobby())
                            .height(biodataRequest.getHeight())
                    .build());

            return BiodataResponse.builder()
                    .id(updated.getId())
                    .name(updated.getName())
                    .address(updated.getAddress())
                    .age(updated.getAge())
                    .hobby(updated.getHobby())
                    .height(updated.getHeight())
                    .createdAt(updated.getCreatedAt())
                    .updatedAt(updated.getUpdatedAt())
                    .build();
        } catch (RuntimeException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleted(String id) throws ExecutionException, InterruptedException {

        return biodataRepository.deleted(id);
    }

    public List<BiodataResponse> findAll() throws ExecutionException, InterruptedException {

        return biodataRepository.findAll().stream().map(val -> BiodataResponse.builder()
                .id(val.getId())
                .name(val.getName())
                .address(val.getAddress())
                .age(val.getAge())
                .hobby(val.getHobby())
                .height(val.getHeight())
                .createdAt(val.getCreatedAt())
                .updatedAt(val.getUpdatedAt())
                .build()).toList();
    }

    public List<BiodataResponse> findByName(String name) throws ExecutionException, InterruptedException {

        return biodataRepository.findByName(name).stream().map(val -> BiodataResponse.builder()
                .id(val.getId())
                .name(val.getName())
                .address(val.getAddress())
                .age(val.getAge())
                .hobby(val.getHobby())
                .height(val.getHeight())
                .createdAt(val.getCreatedAt())
                .updatedAt(val.getUpdatedAt())
                .build()).toList();
    }
}
