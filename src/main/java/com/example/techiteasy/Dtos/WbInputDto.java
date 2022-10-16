package com.example.techiteasy.Dtos;

public class WbInputDto {

    public String size;
    public Boolean adjustable;
    public String name;
    public Double price;

    public WbInputDto(){}

    public WbInputDto(String size, Boolean adjustable, String name, Double price) {
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }
}
