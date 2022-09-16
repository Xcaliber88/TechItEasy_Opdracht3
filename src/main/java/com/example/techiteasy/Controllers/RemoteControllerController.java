package com.example.techiteasy.Controllers;

import com.example.techiteasy.Dtos.RemoteControllerDto;
import com.example.techiteasy.Dtos.RemoteControllerInputDto;
import com.example.techiteasy.Models.RemoteController;
import com.example.techiteasy.Services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.Remote;
import java.util.List;
import java.util.Optional;

@RestController
public class RemoteControllerController {

    private final RemoteControllerService service;

    public RemoteControllerController(RemoteControllerService service){
        this.service = service;
    }

    @GetMapping("/remoteControllers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers(){
        List<RemoteControllerDto> dtos;

        dtos= service.getAllRemoteControllers();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remoteControllers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers (@RequestParam(value= "brand", required = false) Optional<String> brand){
        List<RemoteControllerDto> dtos;

        if(brand.isEmpty()){
            dtos= service.getAllRemoteControllers();
        }else{
            dtos = service.getAllRemoteControllersByBrand(brand.get());
        }
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remoteControllers/{id}")
    public ResponseEntity<Object> getRemoteController (@PathVariable("id") Long id){
        RemoteControllerDto dtos = service.getRemoteControllerById(id);

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping("/remoteControllers")
    public ResponseEntity<Object> createRemoteController(@RequestBody RemoteControllerInputDto inputDto ){
        RemoteControllerDto savedDto = service.createRemoteController(inputDto);

        return ResponseEntity.created(null).body(savedDto);
    }

    @DeleteMapping("/remoteControllers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("Id") Long id){
        service.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remoteControllers/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable ("id") Long id, RemoteControllerInputDto newRemoteController ){
    RemoteControllerDto dto = service.updateRemoteController(id, newRemoteController);
    return ResponseEntity.ok().body(dto);
    }
}
