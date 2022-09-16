package com.example.techiteasy.Repositories;

import com.example.techiteasy.Models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemoteControllerRepository extends JpaRepository <RemoteController, Long> {
    List<RemoteController> findAllRemoteControllersByBrandEqualsIgnoreCase(String brand);
}
