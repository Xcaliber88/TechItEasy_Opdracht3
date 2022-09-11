package com.example.techiteasy.Controllers;

import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class TelevisionController {

//@autowired
//TelevisionRepository televisionRepository;


    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false)String brand) {
        List<Television> televisions;

        if(brand == null){
            televisions= televisionRepository.findAll();
            return ResponseEntity.ok().body(televisions);
        } else {
            televisions = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        }
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) { // determine if id exists
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television television1 = television.get();
            return ResponseEntity.ok().body(television1);
        }
    }

    @PostMapping("/televisions")
    public  ResponseEntity<Object>createTelevision(@RequestBody Television t) {
        televisionRepository.save(t);
        return ResponseEntity.created(null).body(t);
    }
//
//    @PostMapping("televisions")
//    public ResponseEntity<Object> addTelevision(@RequestBody List<String> television) {
//
//        this.televisions.addAll(television);
//        return ResponseEntity.created(null).build();
//    }


@DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id){
        televisionRepository.deleteById(id);
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
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {

        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {

            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            Television television1 = television.get();
            television1.setId(television1.getId());
            television1.setAmbilight(newTelevision.getAmbiLight());
            television1.setAvailableSize(newTelevision.getAvailableSize());
            television1.setAmbilight(newTelevision.getAmbiLight());
            television1.setBluetooth(newTelevision.getBluetooth());
            television1.setBrand(newTelevision.getBrand());
            television1.setHdr(newTelevision.getHdr());
            television1.setName(newTelevision.getName());
            television1.setOriginalStock(newTelevision.getOriginalStock());
            television1.setPrice(newTelevision.getPrice());
            television1.setRefreshRate(newTelevision.getRefreshRate());
            television1.setScreenQuality(newTelevision.getScreenQuality());
            television1.setScreenType(newTelevision.getScreenType());
            television1.setSmartTv(newTelevision.getSmartTv());
            television1.setSold(newTelevision.getSold());
            television1.setType(newTelevision.getType());
            television1.setVoiceControl(newTelevision.getVoiceControl());
            television1.setWifi(newTelevision.getWifi());
            televisionRepository.save(television1);
            return ResponseEntity.ok().body(television1);
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

}



