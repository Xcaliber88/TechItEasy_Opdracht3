package com.example.techiteasy.Controllers;


import com.example.techiteasy.Dtos.Ci_ModuleDto;
import com.example.techiteasy.Dtos.Ci_ModuleInputDto;
import com.example.techiteasy.Services.Ci_ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class Ci_ModuleController {

    private final Ci_ModuleService service;

    public Ci_ModuleController(Ci_ModuleService service) {
        this.service = service;
    }

    @GetMapping("/ci_modules")
    public ResponseEntity<List<Ci_ModuleDto>> getAllCi_Modules() {
        List<Ci_ModuleDto> dtos;
        dtos = service.getAllCi_Modules();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/ci_modules/{id}")
    public ResponseEntity<Object> getCi_ModuleById(@PathVariable("id") Long id) {
        Ci_ModuleDto dto = service.getCi_ModuleById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping ("/ci_modules/{id}")
    public ResponseEntity<Object> createCi_Module(@RequestBody Ci_ModuleInputDto inputDto){
       Ci_ModuleDto dto = service.createCi_Module(inputDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id).toUri();

       return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping ("/ci_modules/{id}")
    public ResponseEntity<Object> deleteCi_Module(@PathVariable("id") Long id){
        service.deleteCi_Module(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping ("/ci_modules/{id}")
    public ResponseEntity<Object> updateCi_Module(@PathVariable Long id, @RequestBody Ci_ModuleInputDto newCi){
        Ci_ModuleDto dto = service.updateCi_Module(id, newCi);
        return ResponseEntity.ok().body(dto);

    }
}

