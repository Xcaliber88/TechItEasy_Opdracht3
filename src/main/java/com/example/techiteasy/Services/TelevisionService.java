package com.example.techiteasy.Services;

import com.example.techiteasy.Dtos.TelevisionDto;
import com.example.techiteasy.Dtos.TelevisionInputDto;
import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository repos;
    public TelevisionService(TelevisionRepository repos) {this.repos = repos;}


    public List<TelevisionDto> getAllTelevision() {
        List<Television> tvlist = repos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv:tvlist){
            TelevisionDto dto= fromTelevision(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;

    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand){
        List<Television> tvlist= repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtolist = new ArrayList<>();


        for(Television tv:tvlist){
            TelevisionDto dto= fromTelevision(tv);
            tvDtolist.add(dto);
        }
        return tvDtolist;
    }

    public TelevisionDto getTelevisionById(Long id){
        if(repos.findById(id).isPresent()){
            Television tv = repos.findById(id).get();
            return fromTelevision(tv);
        }else{
            throw new RuntimeException("geen televisie gevonden");
        }
    }

    public TelevisionDto createTelevision(TelevisionInputDto inputDto){
        Television savedTelevision= toTelevision(inputDto);
        this.repos.save(savedTelevision);

        return fromTelevision(savedTelevision);
    }

    public void deleteTelevision(@RequestBody Long id){
    Optional<Television> televisionFound = repos.findById(id);
    if (televisionFound == null)
        throw new RecordNotFoundException("cannot find television");
    repos.deleteById(id);
}
    public TelevisionDto updateTelevision(Long id, TelevisionInputDto inputDto) {


        if (repos.findById(id).isEmpty())
            throw new RecordNotFoundException("Cannot find television");

        Television savedTelevision = this.repos.findById(id).get();

        Television tv1= toTelevision(inputDto);
        tv1.setId(savedTelevision.getId());

        repos.save(tv1);

        return fromTelevision(tv1);
    }

    public static TelevisionDto fromTelevision(Television television) {

        TelevisionDto dto= new TelevisionDto();

        dto.name = television.getName();
        dto.type = television.getType();
        dto.brand = television.getBrand();
        dto.price = television.getPrice();
        dto.availableSize = television.getAvailableSize();
        dto.refreshRate = television.getRefreshRate();
        dto.screenType = television.getScreenType();
        dto.screenQuality = television.getScreenQuality();
        dto.smartTv = television.getSmartTv();
        dto.wifi = television.getWifi();
        dto.voiceControl = television.getVoiceControl();
        dto.hdr = television.getHdr();
        dto.bluetooth = television.getBluetooth();
        dto.ambilight= television.getAmbiLight();
        dto.originalStock=television.getOriginalStock();
        dto.sold=television.getSold();

        return dto;

    }

    public Television toTelevision(TelevisionInputDto inputDto){

        Television television = new Television();

        television.setName(inputDto.name);
        television.setType(inputDto.type);
        television.setBrand(inputDto.brand);
        television.setPrice(inputDto.price);
        television.setAvailableSize(inputDto.availableSize);
        television.setRefreshRate(inputDto.refreshRate);
        television.setScreenType(inputDto.screenType);
        television.setScreenQuality(inputDto.screenQuality);
        television.setSmartTv(inputDto.smartTv);
        television.setWifi(inputDto.wifi);
        television.setVoiceControl(inputDto.voiceControl);
        television.setHdr(inputDto.hdr);
        television.setBluetooth(inputDto.bluetooth);
        television.setAmbilight(inputDto.ambilight);
        television.setOriginalStock(inputDto.originalStock);
        television.setSold(inputDto.sold);

        return television;

    }



}
