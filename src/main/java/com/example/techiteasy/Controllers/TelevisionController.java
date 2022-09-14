package com.example.techiteasy.Controllers;

import com.example.techiteasy.Dtos.TelevisionDto;
import com.example.techiteasy.Dtos.TelevisionInputDto;
import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Repositories.TelevisionRepository;
import com.example.techiteasy.Services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class TelevisionController {

    private final TelevisionRepository repos;
    private final TelevisionService service;



    public TelevisionController(TelevisionRepository repos, TelevisionService service){
        this.repos = repos;
        this.service=service;
    }


//    @Autowired
//    public TelevisionController(TelevisionService televisionService){
//        this.televisionService = televisionService;
//    }
    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false)Optional<String> brand) {
        List<TelevisionDto> dtos;

        if(brand.isEmpty()){
            dtos = service.getAllTelevision();

        } else {
            dtos = service.getAllTelevisionsByBrand(brand.get());
        }
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable("id") Long id) {
        TelevisionDto television = service.getTelevisionById(id);

        return ResponseEntity.ok().body(television);
    }

    @PostMapping("/televisions")
    public  ResponseEntity<Object>createTelevision(@RequestBody TelevisionInputDto inputDto) {
        TelevisionDto dto = service.createTelevision(inputDto);
        return ResponseEntity.created(null).body(dto);
    }
//    @PostMapping("televisions")
//    public ResponseEntity<Object> addTelevision(@RequestBody List<String> television) {
//
//        this.televisions.addAll(television);
//        return ResponseEntity.created(null).build();
//    }


@DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id){
        service.deleteTelevision(id);
        return  ResponseEntity.noContent().build();
}
//    @PutMapping("televisions/{id}")
//    public ResponseEntity<Object> updateTelevision(@PathVariable Integer id, @RequestBody String television) {
//        if (id == null) { // determine if id exists
//            throw new RecordNotFoundException("ID cannot be found");
//        }
//        this.televisions.set(id, television);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto newTelevision) {

        TelevisionDto dto = service.updateTelevision(id, newTelevision);


        return ResponseEntity.ok().body(dto);
    }
}
//    @DeleteMapping("televisions/{id}")
//    public ResponseEntity<Object> deleteTelevision(@PathVariable Integer id) {
//        if (id > televisions.size() - 1) { // determine if id exists
//            throw new RecordNotFoundException("ID cannot be found");
//        } else {
//            this.televisions.remove(id);
//            return ResponseEntity.noContent().build();
//        }
//    }





