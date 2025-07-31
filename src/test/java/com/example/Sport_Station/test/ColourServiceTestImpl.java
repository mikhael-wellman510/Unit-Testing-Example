package com.example.Sport_Station.test;

import com.example.Sport_Station.dto.request.ColorRequest;
import com.example.Sport_Station.dto.response.ColorResponse;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.dto.response.ProductResponse;
import com.example.Sport_Station.entity.Color;
import com.example.Sport_Station.repository.ColorRepository;
import com.example.Sport_Station.service.ColorService;
import com.example.Sport_Station.service.impl.ColorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@SpringBootTest
public class ColourServiceTestImpl {

    @Mock
    private ColorRepository colorRepository;

    @InjectMocks
    private ColorServiceImpl colorServiceImpl;

    private ColorRequest colorRequest;
    private ColorResponse colorResponse;
    private Color color;

    // Todo -> setup untuk dummy data
    @BeforeEach
    public void setup() {
        colorRequest = ColorRequest.builder()
                .id(1L)
                .colorName("Red")
                .hexCode("##HXXX")
                .build();

        colorResponse = new ColorResponse(1L, "Red", "##HXXX");
        color = Color.builder()
                .id(1L)
                .colorName("Red")
                .hexCode("##HXXX")
                .build();
    }


    @Test
    public void testGetProductById() {
        Long id = 1L;

        // Todo -> Mock Data Dummy (Arrange)
        Mockito.when(colorRepository.findById(id)).thenReturn(Optional.of(color));
        // Todo -> final hasil nya (Act)
        ColorResponse res = colorServiceImpl.findColorById(id);

        // Todo -> assert (Pengujian hasil)
        Assertions.assertNotNull(res);
        Assertions.assertEquals(1L, res.getId());

    }

    @Test
    public void testUpdatedColor() {
        // Todo -> Mock update Req (Arrange)
        ColorRequest req = ColorRequest.builder()
                .id(1L)
                .colorName("Blue")
                .hexCode("##45FDW")
                .build();

        // Todo - > Mock data Dummy (Arrange)
        // Todo -> dapatkan data id dari mock Color
        Mockito.when(colorRepository.findById(req.getId())).thenReturn(Optional.of(color));

        // Todo -> Set data sesuai skenario updated data
        color.setColorName(req.getColorName());
        color.setHexCode(req.getHexCode());

        // Todo -> Mock data Updated (Act)
        Mockito.when(colorRepository.save(any(Color.class))).thenReturn(color);

        // Todo -> final hasil nya  (Act)
        ColorResponse res = colorServiceImpl.updatedColor(req);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(1L, res.getId());
        Assertions.assertEquals("Blue", res.getColorName());
        Assertions.assertEquals("##45FDW", res.getHexCode());

    }


    @Test
    public void testDeleted() {
        Long id = 1L;

        // todo -> arrange
        Mockito.when(colorRepository.findById(id)).thenReturn(Optional.of(color));

        // todo -> Act
        colorServiceImpl.deletedData(id);

        // todo > Assert
        //todo -> Memastikan repo ini terpanggil , karena hasil deleted void
        Mockito.verify(colorRepository).findById(id);

    }

    @Test
    public void testCreate() {

        // Todo -> Mock data dummy untuk repo nya(Arrange)
        Mockito.when(colorRepository.save(any(Color.class))).thenReturn(color);
        // Todo -> Final hasil nya untuk di uji(Act)
        ColorResponse res = colorServiceImpl.addColor(colorRequest);

        // Todo -> Pembanding nya (Assert)
        Assertions.assertNotNull(res);
        Assertions.assertEquals(1L, res.getId());
        Assertions.assertEquals("Red", res.getColorName());
        Assertions.assertEquals("##HXXX", res.getHexCode());
    }

    @Test
    public void testFilterAndPaggingColor() {
        String color = "blue";
        int page = 0;
        int size = 5;
        List<Color> dataDummy = List.of(
                Color.builder()
                        .id(1L)
                        .colorName("Blue Sky")
                        .hexCode("##B1")
                        .build(),

                Color.builder()
                        .id(2L)
                        .colorName("Navy Blue")
                        .hexCode("##B2")
                        .build());
        Pageable pageable = PageRequest.of(page , size);

        // Todo -> Tampung data , seolah2 sudah ada datanya
        Page<Color>mock = new PageImpl<>(dataDummy ,pageable , dataDummy.size());

        // Todo -> Mock data dummy (Arrange) ->Seolah2 hasil return Query nya
        Mockito.when(colorRepository.findAll(Mockito.
                <Specification<Color>>any(),any(Pageable.class))).thenReturn(mock);

        // Todo -> Test langsung , panggil service nya (ACT)
        PaggingResponse res = colorServiceImpl.filterAndPaggingColor(color , page , size);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(0 ,res.getPage());
        Assertions.assertEquals(5 , res.getSize());
        Assertions.assertEquals(1, res.getTotalPages());
        Assertions.assertEquals(2, res.getData().size());
        Assertions.assertFalse(res.isHasNext());
        Assertions.assertFalse(res.isHasPrev());

    }

    @Test
    public void testSearchColor(){

        String color = "blue";
        int page = 0;
        int size = 5;
        List<Color> dataDummy = List.of(
                Color.builder()
                        .id(1L)
                        .colorName("Blue Sky")
                        .hexCode("##B1")
                        .build(),

                Color.builder()
                        .id(2L)
                        .colorName("Navy Blue")
                        .hexCode("##B2")
                        .build());

        Pageable pageable = PageRequest.of(page,size);
        Page<Color> mockPage = new PageImpl<>(dataDummy,pageable , dataDummy.size());
        // Todo -> Mock Data Repo nya  (Arrange)
        Mockito.when(colorRepository.findByColorNameContainingIgnoreCase(color,pageable)).thenReturn(mockPage);

        // Todo -> Act , panggil method

        Page<Color> searchColor = colorServiceImpl.searchColor(color , page , size);

        // Todo -> Assert

        Assertions.assertNotNull(searchColor);

        Assertions.assertEquals("Blue Sky" , searchColor.getContent().get(0).getColorName());
        Assertions.assertEquals("##B1" , searchColor.getContent().get(0).getHexCode());
        Assertions.assertEquals(2, searchColor.getTotalElements());
        Assertions.assertEquals(1, searchColor.getTotalPages());
        Assertions.assertEquals(2, searchColor.getNumberOfElements());
        Assertions.assertEquals(0,searchColor.getPageable().getPageNumber()
        );
        Assertions.assertFalse(searchColor.isEmpty());
        Assertions.assertTrue(searchColor.isLast());
        Assertions.assertTrue(searchColor.isFirst());

    }
}
