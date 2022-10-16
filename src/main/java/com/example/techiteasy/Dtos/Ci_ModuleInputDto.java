package com.example.techiteasy.Dtos;

public class Ci_ModuleInputDto {

    public Long id;
    public String name;
    public String type;
    public Double price;

    public Ci_ModuleInputDto(String name, String type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
