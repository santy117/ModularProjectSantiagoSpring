package org.example.PTSantiago.controller;


import com.example.api.StarWarsApi;
import com.example.models.ListMissionResponseDTO;
import com.example.models.MissionDTO;
import org.example.PTSantiago.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StarWarsController implements StarWarsApi {

    @Autowired
    private StarWarsService starWarsService;


    @Override
    public ResponseEntity<ListMissionResponseDTO> getStarWarsListMission(List<Integer> captain) {
        ListMissionResponseDTO listMissions = this.starWarsService.getStarWarsListMission(captain);
        return new ResponseEntity<>(listMissions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListMissionResponseDTO> getStarWarsRecommendedMission(String criterion) {
        ListMissionResponseDTO listMissions = this.starWarsService.getStarWarsRecommendedMission(criterion);
        return new ResponseEntity<>(listMissions, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> postCreateMission(MissionDTO body) {
        this.starWarsService.postCreateMission(body);
        return ResponseEntity.ok().build();
    }


}
