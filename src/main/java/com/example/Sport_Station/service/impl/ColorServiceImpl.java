package com.example.Sport_Station.service.impl;

import com.example.Sport_Station.dto.request.ColorRequest;
import com.example.Sport_Station.dto.response.ColorResponse;
import com.example.Sport_Station.dto.response.PaggingResponse;
import com.example.Sport_Station.entity.Color;
import com.example.Sport_Station.repository.ColorRepository;
import com.example.Sport_Station.service.ColorService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public ColorResponse addColor(ColorRequest colorRequest) {

        try {
            Color color = colorRepository.save(Color.builder()
                    .colorName(colorRequest.getColorName())
                    .hexCode(colorRequest.getHexCode())
                    .build());

            return ColorResponse.builder()
                    .id(color.getId())
                    .colorName(color.getColorName())
                    .hexCode(color.getHexCode())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ColorResponse findColorById(Long id) {
        Color findId = colorRepository.findById(id).orElseThrow(()-> new RuntimeException("Data Tidak di temukan"));

        return ColorResponse.builder()
                .id(findId.getId())
                .colorName(findId.getColorName())
                .hexCode(findId.getHexCode())
                .build();
    }

    @Override
    public ColorResponse updatedColor(ColorRequest colorRequest) {
        try {

            Color find = colorRepository.findById(colorRequest.getId()).orElseThrow(()->new RuntimeException("Data tidak ditemukan"));

            find.setColorName(colorRequest.getColorName());
            find.setHexCode(colorRequest.getHexCode());

            Color updated = colorRepository.save(find);

            return ColorResponse.builder()
                    .id(updated.getId())
                    .colorName(updated.getColorName())
                    .hexCode(updated.getHexCode())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletedData(Long id) {
        try {
            Color find = colorRepository.findById(id).orElseThrow(()-> new RuntimeException("Data tidak ditemukan , Gagal hapus"));


            colorRepository.delete(find);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaggingResponse filterAndPaggingColor(String colorName, Integer page, Integer size) {

        try {

            Pageable pageable = PageRequest.of(page,size);

            Specification<Color> specification =((root, query, criteriaBuilder) -> {
                List<Predicate> predicates =new ArrayList<>();

                if (colorName != null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("colorName")),"%" + colorName.toLowerCase()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            });

            Page<Color> colors = colorRepository.findAll(specification,pageable);

            List<ColorResponse> listColor = new ArrayList<>();

            for (Color res : colors){
                listColor.add(ColorResponse.builder()
                                .id(res.getId())
                                .colorName(res.getColorName())
                                .hexCode(res.getHexCode())
                        .build());
            }


            return PaggingResponse.builder()
                    .data(listColor)
                    .page(page)
                    .size(size)
                    .totalPages(colors.getTotalPages())
                    .totalData(colors.getTotalElements())
                    .hasNext(colors.hasNext())
                    .hasPrev(colors.hasPrevious())
                    .build();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Page<Color> searchColor(String color, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page,size);

        Page<Color> data = colorRepository.findByColorNameContainingIgnoreCase(color,pageable);

        return new PageImpl<>(data.getContent() , pageable ,data.getTotalElements());
    }


}
