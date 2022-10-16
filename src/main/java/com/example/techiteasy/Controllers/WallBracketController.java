package com.example.techiteasy.Controllers;

import com.example.techiteasy.Dtos.TelevisionDto;
import com.example.techiteasy.Dtos.WallBracketDto;
import com.example.techiteasy.Dtos.WbInputDto;
import com.example.techiteasy.Services.TelevisionWallBracketService;
import com.example.techiteasy.Services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
public class WallBracketController {

    private final WallBracketService service;

    private final TelevisionWallBracketService televisionWallBracketService;

    public WallBracketController(WallBracketService service, TelevisionWallBracketService televisionWallBracketService) {
        this.service = service;
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @GetMapping("/wallbrackets")
    public ResponseEntity<List<WallBracketDto>> getAllWallBracketsDto (){
        List<WallBracketDto> dtos;
        dtos =service.getAllWallBrackets();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/wallBrackets/{id}")
    public ResponseEntity<Object> getWallBracket(@PathVariable("id") Long id) {
        WallBracketDto wallBracketDto = service.getWallBracketsById(id);

        return ResponseEntity.ok().body(wallBracketDto);
    }

    @PostMapping("/wallbrackets")
    public  ResponseEntity<Object>createWallBracket(@RequestBody WbInputDto inputDto) {
        WallBracketDto dto = service.createWallBracket(inputDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id).toUri();

        return ResponseEntity.created(location).body(dto);
    }

    @DeleteMapping("/wallbrackets/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id){
        service.deleteWallBracket(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/wallbrakets/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable Long id, @RequestBody WbInputDto newWb){

        WallBracketDto dto = service.updateWallBracket(id, newWb);


        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/wallbrackets/televisions/{wallBracketId}")
    public Collection<TelevisionDto> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId){
        return televisionWallBracketService.getTelevisionWallBracketsByWallBracketId(wallBracketId);
    }

}
