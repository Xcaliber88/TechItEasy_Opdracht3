package com.example.techiteasy.Controllers;

import com.example.techiteasy.Services.TelevisionWallBracketService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tvwb")
public class TelevisionWallBracketController {

    private final TelevisionWallBracketService televisionWallBracketService;

    public TelevisionWallBracketController(TelevisionWallBracketService televisionWallBracketService) {
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @PostMapping("/{televisionId}/{wallBracketId}")
    public void addTelevisionWallBracket(@PathVariable("televisionId") Long televisionId,@PathVariable("wallBracketId") Long wallBracketId) {
        televisionWallBracketService.addTelevisionWallBracket(televisionId, wallBracketId);
    }
}
