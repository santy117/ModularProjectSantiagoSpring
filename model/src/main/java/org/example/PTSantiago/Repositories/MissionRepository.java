package org.example.PTSantiago.Repositories;

import org.example.PTSantiago.Entities.Mission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MissionRepository extends CrudRepository<Mission, Integer> {
    @Override
    List<Mission> findAll();
}
