package com.example.techiteasy.Repositories;

import com.example.techiteasy.Models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long > {
    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);
}
