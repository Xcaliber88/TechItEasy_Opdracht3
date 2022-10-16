package com.example.techiteasy.Services;

import com.example.techiteasy.Dtos.Ci_ModuleDto;
import com.example.techiteasy.Dtos.Ci_ModuleInputDto;
import com.example.techiteasy.Models.Ci_Module;
import com.example.techiteasy.Repositories.Ci_ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Ci_ModuleService {

    private final Ci_ModuleRepository repos;

    public Ci_ModuleService(Ci_ModuleRepository repos) {
        this.repos = repos;
    }

    public List<Ci_ModuleDto> getAllCi_Modules(){
        List<Ci_Module> Ci_ModuleList = repos.findAll();
        List<Ci_ModuleDto> Ci_ModuleDtoList = new ArrayList<>();

        for(Ci_Module Ci: Ci_ModuleList) {
            Ci_ModuleDto dto = fromCi_Modules(Ci);
            Ci_ModuleDtoList.add(dto);
        }
        return Ci_ModuleDtoList;
    }

    public Ci_ModuleDto getCi_ModuleById(Long id){
        if(repos.findById(id).isPresent()){
            Ci_Module ci = repos.findById(id).get();
            return fromCi_Modules(ci);
        }else{
            throw new RuntimeException("Ci_Module niet gevonden");
        }
    }

    public Ci_ModuleDto createCi_Module(Ci_ModuleInputDto inputDto){
        Ci_Module savedCi_module = toCi_Module(inputDto);
        this.repos.save(savedCi_module);

        return fromCi_Modules(savedCi_module);
    }

    public void deleteCi_Module(@PathVariable Long id){
        Optional<Ci_Module> ciFound= repos.findById(id);

        if(ciFound==null){
            throw new RuntimeException("Ci module not found");
        }else{
            repos.deleteById(id);
        }
    }

    public Ci_ModuleDto updateCi_Module(Long id, Ci_ModuleInputDto inputDto){
        if(repos.findById(id).isEmpty())
        throw new RuntimeException("Ci module niet gevonden");

        Ci_Module foundCi = repos.findById(id).get();
        Ci_Module updatedCi= toCi_Module(inputDto);
        updatedCi.setId(id);
        repos.save(updatedCi);


        return fromCi_Modules(updatedCi);
    }

    public Ci_ModuleDto fromCi_Modules(Ci_Module ci_module){
        Ci_ModuleDto dto = new Ci_ModuleDto();

        dto.id = ci_module.getId();
        dto.name = ci_module.getName();
        dto.type = ci_module.getType();
        dto.price = ci_module.getPrice();

        return dto;
    }

    public Ci_Module toCi_Module(Ci_ModuleInputDto inputDto){
        Ci_Module newCi_Module = new Ci_Module();

        newCi_Module.setName(inputDto.name);
        newCi_Module.setType(inputDto.type);
        newCi_Module.setPrice(inputDto.price);

        return newCi_Module;
    }
}
