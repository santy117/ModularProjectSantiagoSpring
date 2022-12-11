package org.example.PTSantiago.service;

import com.example.models.ListMissionResponseDTO;
import com.example.models.MissionDTO;

import java.util.List;

public interface StarWarsService {
    void postCreateMission(MissionDTO body);

    ListMissionResponseDTO getStarWarsListMission(List<Integer> filterCaptain);

    ListMissionResponseDTO getStarWarsRecommendedMission(String criterion);
}
