package com.example.techiteasy.Services;


import com.example.techiteasy.Dtos.TelevisionDto;
import com.example.techiteasy.Dtos.TelevisionInputDto;
import com.example.techiteasy.Dtos.WallBracketDto;
import com.example.techiteasy.Dtos.WbInputDto;
import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Models.WallBracket;
import com.example.techiteasy.Repositories.WallBracketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> bracketList = repos.findAll();
        List<WallBracketDto> bracketDtoList = new ArrayList<>();

        for (WallBracket wb : bracketList) {
            WallBracketDto dto = fromWallBrackets(wb);
            bracketDtoList.add(dto);
        }
        return bracketDtoList;
    }

    public WallBracketDto getWallBracketsById(Long id) {
        if (repos.findById(id).isPresent()) {
            WallBracket wb = repos.findById(id).get();
        return fromWallBrackets(wb);
    }else {
        throw new RuntimeException("geen wallbracket gevonden");
    }

}public WallBracketDto createWallBracket(WbInputDto inputDto){
        WallBracket savedWallBracket= toWallBracket(inputDto);
        this.repos.save(savedWallBracket);

        return fromWallBrackets(savedWallBracket);
    }

    public void deleteWallBracket(@RequestBody Long id) {
        Optional<WallBracket> wbFound= repos.findById(id);

        if(wbFound==null){
            throw new RuntimeException("cannot find wall bracket");
        }else {
            repos.deleteById(id);
        }
    }

    public WallBracketDto updateWallBracket(Long id, WbInputDto inputDto) {


        if (repos.findById(id).isEmpty())
            throw new RecordNotFoundException("Cannot find wallbracket");

        WallBracket foundWallBracket = this.repos.findById(id).get();

        WallBracket updatedWb= toWallBracket(inputDto);
        updatedWb.setId(id); //geef de bestaande id aan nieuwe tv

        repos.save(updatedWb); // saved alle aangepaste waarden

        return fromWallBrackets(updatedWb); // vertaling van television naar dto
    }

    public WallBracketDto fromWallBrackets( WallBracket wallBracket){
        WallBracketDto dto = new WallBracketDto();


        dto.id = wallBracket.getId();
        dto.name = wallBracket.getName();
        dto.price= wallBracket.getPrice();
        dto.size = wallBracket.getSize();
        dto.adjustable= wallBracket.getAdjustable();

        return dto;
    }

    public WallBracket toWallBracket(WbInputDto inputDto){

        WallBracket wb = new WallBracket();

        wb.setName(inputDto.name);
        wb.setSize(inputDto.size);
        wb.setAdjustable(inputDto.adjustable);
        wb.setPrice(inputDto.price);

        return wb;
    }


}


