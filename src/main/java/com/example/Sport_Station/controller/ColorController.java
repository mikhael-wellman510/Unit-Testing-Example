package com.example.Sport_Station.controller;

import com.example.Sport_Station.dto.request.ColorRequest;
import com.example.Sport_Station.entity.Color;
import com.example.Sport_Station.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/addColor")
    public ResponseEntity<?>addColor(@RequestBody ColorRequest colorRequest){

        var addColor = colorService.addColor(colorRequest);

        return ResponseEntity.ok(addColor);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?>findByIdColor(@PathVariable Long id){
        log.info("Id : {} " , id);
        var res = colorService.findColorById(id);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/updated")
    public ResponseEntity<?>updatedColor(@RequestBody ColorRequest colorRequest){

        var res = colorService.updatedColor(colorRequest);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?>deletedColor(@PathVariable Long id){

        colorService.deletedData(id);

        return ResponseEntity.ok("Succes Deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<?>searchColorAndPagging(@RequestParam(name = "color" , defaultValue = "")String color , @RequestParam(name = "page" , defaultValue = "0")Integer page , @RequestParam(name = "size" , defaultValue = "5")Integer size){
        log.info("color : {}  ,  page : {}  ,  size : {} " , color , page , size);
        var res = colorService.filterAndPaggingColor(color,page,size);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/search2")
    public ResponseEntity<?>searchColorAndPagging2(@RequestParam(name = "color" , defaultValue = "")String color , @RequestParam(name = "page" , defaultValue = "0")Integer page , @RequestParam(name = "size" , defaultValue = "5")Integer size){

        Page<Color> res = colorService.searchColor(color,page,size);

        return ResponseEntity.ok(res);
    }

}
