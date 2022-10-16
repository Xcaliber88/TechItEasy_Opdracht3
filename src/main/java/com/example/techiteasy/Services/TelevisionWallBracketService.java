package com.example.techiteasy.Services;

import com.example.techiteasy.Dtos.TelevisionDto;
import com.example.techiteasy.Dtos.WallBracketDto;
import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Models.TelevisionWallBracket;
import com.example.techiteasy.Models.TelevisionWallBracketKey;
import com.example.techiteasy.Models.WallBracket;
import com.example.techiteasy.Repositories.TelevisionRepository;
import com.example.techiteasy.Repositories.TelevisionWallBracketRepository;
import com.example.techiteasy.Repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class TelevisionWallBracketService {

    private final TelevisionRepository televisionRepository;

    private final WallBracketRepository wallBracketRepository;

    private final TelevisionWallBracketRepository televisionWallBracketRepository;

    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionWallBracketRepository televisionWallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionWallBracketRepository = televisionWallBracketRepository;
    }

    public Collection<TelevisionDto> getTelevisionWallBracketsByWallBracketId(Long wallBracketId){
       Collection<TelevisionDto> dtos = new HashSet<>();
       Collection<TelevisionWallBracket> televisionWallBrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
       for(TelevisionWallBracket televisionWallBracket : televisionWallBrackets) {
           Television television= televisionWallBracket.getTelevision();
           TelevisionDto dto = new TelevisionDto();

           television.setId(dto.id);
           television.setType(dto.type);
           television.setBrand(dto.brand);
           television.setName(dto.name);
           television.setPrice(dto.price);
           television.setAvailableSize(dto.availableSize);
           television.setRefreshRate(dto.refreshRate);
           television.setScreenType(dto.screenType);
           television.setScreenQuality(dto.screenQuality);
           television.setSmartTv(dto.smartTv);
           television.setWifi(dto.wifi);
           television.setVoiceControl(dto.voiceControl);
           television.setHdr(dto.hdr);
           television.setBluetooth(dto.bluetooth);
           television.setAmbilight(dto.ambilight);
           television.setOriginalStock(dto.originalStock);
           television.setSold(dto.sold);

           dtos.add(dto);
       }
       return dtos;
    }

    public Collection<WallBracketDto> getTelevisionWallBracketByTelevisionId(Long televisonId){
        Collection<WallBracketDto> dtos = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallBrackets = televisionWallBracketRepository.findAllByTelevisionId(televisonId);
        for(TelevisionWallBracket televisionWallBracket: televisionWallBrackets){
            WallBracket wallBracket = televisionWallBracket.getWallBracket();
            WallBracketDto dto = new WallBracketDto();

            dto.id = wallBracket.getId();;
            dto.name = wallBracket.getName();
            dto.size = wallBracket.getSize();
            dto.adjustable = wallBracket.getAdjustable();
            dto.price = wallBracket.getPrice();

            dtos.add(dto);
        }

        return dtos;

    }

    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId){
        TelevisionWallBracket televisionWallBracket = new TelevisionWallBracket();
        if(!televisionRepository.existsById(televisionId)) { throw new RecordNotFoundException();}
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if(!wallBracketRepository.existsById(wallBracketId)) { throw new RecordNotFoundException();}
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);

        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId) ;
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);

        return id;

    }
}
