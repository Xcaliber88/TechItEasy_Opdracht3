package com.example.techiteasy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Ci_Modules")
public class Ci_Module {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Double price;

    @OneToMany(mappedBy = "ci_module")
    @JsonIgnore
    List<Television> television;

    public List<Television> getTelevision() {
        return television;
    }

    public void setTelevision(List<Television> television) {
        this.television = television;
    }

    public Ci_Module(String name, String type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Ci_Module() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
