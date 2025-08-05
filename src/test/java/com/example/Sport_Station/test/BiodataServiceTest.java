package com.example.Sport_Station.test;

import com.example.Sport_Station.FirebaseService.Dto.BiodataRequest;
import com.example.Sport_Station.FirebaseService.Dto.BiodataResponse;
import com.example.Sport_Station.FirebaseService.Entity.Biodata;
import com.example.Sport_Station.FirebaseService.Repository.BiodataRepository;
import com.example.Sport_Station.FirebaseService.Service.BiodataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
public class BiodataServiceTest {

    @Mock
    private BiodataRepository biodataRepository;

    @InjectMocks
    private BiodataServiceImpl biodataService;

    private BiodataRequest biodataRequest;
    private BiodataResponse biodataResponse;
    private Biodata biodata;

    @BeforeEach
    public void setup(){

        biodata = Biodata.builder()
                .id("uu199aa")
                .name("Mike")
                .address("Bogor")
                .age(26)
                .hobby("Futsal")
                .height(178L)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        biodataRequest = BiodataRequest.builder()
                .name("Mike")
                .address("Bogor")
                .age(26)
                .hobby("Futsal")
                .height(178L)
                .build();


        biodataResponse = BiodataResponse.builder()
                .id(biodata.getId())
                .name(biodata.getName())
                .address(biodata.getAddress())
                .age(biodata.getAge())
                .hobby(biodata.getHobby())
                .height(biodata.getHeight())
                .createdAt(biodata.getCreatedAt())
                .updatedAt(biodata.getUpdatedAt())
                .build();
    }
    @Test
    public void testUpdatedBio() throws ExecutionException, InterruptedException {
        Mockito.when(biodataRepository.updated(any(Biodata.class))).thenReturn(biodata);
        Date now = new Date();
        biodata.setUpdatedAt(now);

        BiodataResponse br = biodataService.updatedBio(biodataRequest);

        Assertions.assertNotNull(br);
        Assertions.assertEquals("Mike" , br.getName());
        Assertions.assertEquals(now , br.getUpdatedAt());
        verify(biodataRepository, times(1)).updated(any(Biodata.class));

    }

    @Test
    public void testAddBio() throws ExecutionException, InterruptedException {

        // Todo -> Mock data
        Mockito.when(biodataRepository.save(any(Biodata.class))).thenReturn(biodata);

        BiodataResponse br = biodataService.addBio(biodataRequest);


        Assertions.assertNotNull(br);
        Assertions.assertEquals("uu199aa" , br.getId());
        Assertions.assertEquals("Mike" , br.getName());
        Assertions.assertEquals("Bogor" , br.getAddress());

        // Todo -> Berapa kali pemanggilan Repo di lakukan
        verify(biodataRepository, times(1)).save(any(Biodata.class));
    }

    @Test
    public void testFindById() throws ExecutionException, InterruptedException {
        String id = "uu199aa";

        Mockito.when(biodataRepository.findById(id)).thenReturn(biodata);

        BiodataResponse br = biodataService.findByIdBio(id);

        Assertions.assertNotNull(br);
        Assertions.assertEquals("uu199aa" , br.getId());
        Assertions.assertEquals("Mike" , br.getName());
        verify(biodataRepository, times(1)).findById(id);


    }

    @Test
    public void testDeleted() throws ExecutionException, InterruptedException {
        String id = "uu199aa";
        Mockito.when(biodataRepository.deleted(id)).thenReturn("Success Deleted " + id);

        String res = biodataRepository.deleted(id);

        Assertions.assertNotNull(res);
        Assertions.assertEquals("Success Deleted uu199aa" ,res);
        verify(biodataRepository, times(1)).deleted(id);

    }

    @Test
    public void testFindByName() throws ExecutionException, InterruptedException {
        String name = "Deni";
        List<Biodata> listBio = List.of(
                Biodata.builder()
                        .id("a1")
                        .name("Deni")
                        .address("Bogor")
                        .age(29)
                        .hobby("Football")
                        .height(180L)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build() ,
                Biodata.builder()
                        .id("a2")
                        .name("Mike")
                        .address("Depok")
                        .age(32)
                        .hobby("Basket")
                        .height(170L)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build());

        Mockito.when(biodataRepository.findByName(name)).thenReturn(listBio);

        List<BiodataResponse> res = biodataService.findByName(name);

        Assertions.assertNotNull(res);
        Assertions.assertEquals("a2" , res.get(1).getId());
        verify(biodataRepository, times(1)).findByName(name);
    }


}
