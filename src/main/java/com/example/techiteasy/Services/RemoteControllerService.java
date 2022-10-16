package com.example.techiteasy.Services;


import com.example.techiteasy.Dtos.RemoteControllerDto;
import com.example.techiteasy.Dtos.RemoteControllerInputDto;
import com.example.techiteasy.Exceptions.RecordNotFoundException;
import com.example.techiteasy.Models.RemoteController;
import com.example.techiteasy.Models.Television;
import com.example.techiteasy.Repositories.RemoteControllerRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository repos;


    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }

    public List<RemoteControllerDto> getAllRemoteControllers(){

        List<RemoteController> remoteList = repos.findAll();
        List<RemoteControllerDto> remoteDtoList = new ArrayList<>();

        for(RemoteController remote:remoteList){
            RemoteControllerDto dto = fromRemoteController(remote);
            remoteDtoList.add(dto);
        }

        return remoteDtoList;
    }


    public List<RemoteControllerDto> getAllRemoteControllersByBrand(String brand){
        List<RemoteController> remoteList = repos.findAllRemoteControllersByBrandEqualsIgnoreCase(brand);
        List<RemoteControllerDto> remoteDtoList = new ArrayList<>();

        for(RemoteController remote: remoteList){
            RemoteControllerDto dto = fromRemoteController(remote);
            remoteDtoList.add(dto);
        }

        return remoteDtoList;

    }

    public RemoteControllerDto getRemoteControllerById(Long id){
        if(repos.findById(id).isPresent()){
            RemoteController remote =repos.findById(id).get();
            return fromRemoteController(remote);
        }else{
            throw new RuntimeException("geen remotecontroller gevonden");
        }
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto inputDto){
        RemoteController savedRemoteController = toRemoteController(inputDto);
        this.repos.save(savedRemoteController);

        return fromRemoteController(savedRemoteController);
    }


    public RemoteControllerDto fromRemoteController(RemoteController remoteController){

        RemoteControllerDto dto = new RemoteControllerDto();

        dto.id = remoteController.getId();
        dto.brand = remoteController.getBrand();
        dto.name = remoteController.getName();
        dto.batteryType = remoteController.getBatteryType();
        dto.price = remoteController.getPrice();
        dto.originalStock = remoteController.getOriginalStock();
        dto.compatibleWith = remoteController.getCompatibleWith();

        return dto;
    }

    public RemoteController toRemoteController(RemoteControllerInputDto inputDto){
        RemoteController remoteController = new RemoteController();

        remoteController.setBrand(inputDto.brand);
        remoteController.setName(inputDto.name);
        remoteController.setBatteryType(inputDto.batteryType);
        remoteController.setPrice(inputDto.price);
        remoteController.setOriginalStock(inputDto.originalStock);
        remoteController.setCompatibleWith(inputDto.compatibleWith);

        return remoteController;
    }


    public void deleteRemoteController(@RequestBody  Long id) {
        Optional<RemoteController> remoteControllerFound = repos.findById(id);
        if(remoteControllerFound==null){
            throw new RuntimeException("cannot find remotecontroller");
        } else{
            repos.deleteById(id);
        }
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto inputDto) {

        if(repos.findById(id).isEmpty())
            throw new RuntimeException("cannot find remotecontroller");

        RemoteController foundRemoteController = repos.findById(id).get();

        RemoteController savedRemoteController= toRemoteController(inputDto);

        savedRemoteController.setId(id);

        repos.save(savedRemoteController);

        return fromRemoteController(savedRemoteController);

    }
}

