package com.example.techiteasy.Dtos;

import com.example.techiteasy.Models.RemoteController;
import com.example.techiteasy.Models.Television;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TelevisionDto {

    public Long id;
    public String type;

    public String brand;

    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambilight;
    public Integer originalStock;
    public Integer sold;

//    @JsonSerialize
    public RemoteControllerDto remoteControllerDto;

    public Ci_ModuleDto ciModuleDto;



}


