package com.example.techiteasy.Dtos;

public class WallBracketDto {

    public Long id;
    public String size;
    public Boolean adjustable;
    public String name;
    public Double price;

    public WallBracketDto() {
    }

    public WallBracketDto(String size, Boolean adjustable, String name, Double price) {
        this.size = size;
        this.adjustable = adjustable;
        this.name = name;
        this.price = price;
    }
}
