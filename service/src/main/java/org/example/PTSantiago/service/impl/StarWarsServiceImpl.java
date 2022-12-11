package org.example.PTSantiago.service.impl;

import com.example.models.*;

import java.net.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.DateUtils;
import org.example.PTSantiago.Entities.Mission;
import org.example.PTSantiago.Repositories.MissionRepository;
import org.example.PTSantiago.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



@Service
public class StarWarsServiceImpl implements StarWarsService {
    private MissionRepository missionRepository;
    private static final String SWAPI = "https://swapi.dev/api";

    @Autowired
    public StarWarsServiceImpl(final MissionRepository missionRepository){
        this.missionRepository = missionRepository;
    }

    @Override
    @Transactional
    public void postCreateMission(MissionDTO body) {
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate= new SimpleDateFormat("dd/MM/yyyy").parse(body.getStartDate());
        }catch (ParseException e){
            System.out.println("error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error start date", e);
        }
        if(body.getStarshipId() == null || body.getPlanetIds().isEmpty() || body.getCaptainIds().isEmpty() || body.getCrew() == null){
            System.out.println("error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Empty values");
        }
        Integer passengers = this.checkTotalCrew(body.getCrew(), body.getCaptainIds(), body.getStarshipId());
        Integer hourDuration = this.calculateDuration(body.getPlanetIds(), body.getCaptainIds(), body.getCrew());
        endDate = new Date( startDate.getTime() + TimeUnit.HOURS.toMillis(hourDuration));
        String arrayCaptains = body.getCaptainIds().toString();
        arrayCaptains = arrayCaptains.substring(1, arrayCaptains.length()-1);
        String arrayPlanets = body.getPlanetIds().toString();
        arrayPlanets = arrayPlanets.substring(1, arrayPlanets.length()-1);
        // Check if captains are in another mission
        this.checkCaptainAvailability(body.getCaptainIds());
           Mission mission = new Mission().builder()
                   .captains(arrayCaptains)
                   .passengers(passengers)
                   .planets(arrayPlanets)
                   .idStarship(body.getStarshipId())
                   .startDate(startDate)
                   .endDate(endDate)
                   .build();
           this.missionRepository.save(mission);

    }

    private void checkCaptainAvailability(List<Integer> captainIds) {
        for(Integer captain : captainIds){
                List<Mission> listMission = this.missionRepository.findAll();
                for (Mission mission : listMission){
                    List<Integer> lst = Arrays.stream(mission.getCaptains().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
                    if(lst.contains(captain)){
                        System.out.println("error, el capitan ya esta en una mision");
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Captain already in mission");
                    }
                }
        }
    }

    private Integer checkTotalCrew(Integer crew, List<Integer> captainIds, Integer starshipId) {
        Integer totalCrew = crew + captainIds.size();
        try {
            WebClient webClient = WebClient.create(SWAPI);
            Mono<String> call = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/starships/{id}/")
                            .build(starshipId))
                    .retrieve()
                    .bodyToMono(String.class);

            String response = call.block();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(response);
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> pilots = mapper.convertValue(json.get("pilots"), ArrayList.class);
            if(!pilots.isEmpty()){
                Boolean pilotExists = false;
                for(Integer pilot : pilots){
                    if(captainIds.contains(pilot)){
                        pilotExists = true;
                    }
                }
                if(!pilotExists){
                    System.out.println("error");
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "No captain id in pilot crew");
                }
            }
            Integer starshipCrew = json.get("crew").asInt();
            Integer starshipPassengers = json.get("passengers").asInt();
            if(totalCrew < starshipCrew || totalCrew > starshipPassengers){
                System.out.println("error");
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "total crew out of bounds");
            }

        }catch(Exception e){
            System.out.println("error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error url", e);
        }
        return totalCrew;
    }

    private Integer calculateDuration(List<Integer> planetIds, List<Integer> captainIds, Integer crew) {
        double hourDuration = 0;
        double minDuration = 0;
        Integer totalDiameter = 0;
        double coveredKm = crew.floatValue()*10.0 + captainIds.size()*100.0;
        double coveredKmPerMin = coveredKm/60.0;
        try {
            for(Integer planetId : planetIds){
                WebClient webClient = WebClient.create(SWAPI);
                Mono<String> call = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/planets/{id}/")
                                .build(planetId))
                        .retrieve()
                        .bodyToMono(String.class);

                String response = call.block();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode json = objectMapper.readTree(response);
                Integer diameter = json.get("diameter").asInt();
                totalDiameter = totalDiameter + diameter;
            }
        }catch(Exception e){
            System.out.println("error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error url", e);
        }
        minDuration = Math.ceil(totalDiameter/coveredKmPerMin);
        hourDuration = Math.ceil(minDuration/60);
        return (int) hourDuration;
    }

    @Override
    public ListMissionResponseDTO getStarWarsListMission(List<Integer> filterCaptain) {
        List<Mission> missions = this.missionRepository.findAll();
        ListMissionResponseDTO responseDTO = new ListMissionResponseDTO();
        for(Mission mission : missions){
            if(filterCaptain != null){
                List<Integer> lst = Arrays.stream(mission.getCaptains().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
                for(Integer idCaptainFilter : filterCaptain){
                    if(lst.contains(idCaptainFilter)){
                        this.addMision(responseDTO, mission);
                    }
                }
            }else{
                this.addMision(responseDTO, mission);
            }

        }
        return responseDTO;
    }

    private void addMision(ListMissionResponseDTO responseDTO, Mission mission) {
        InfoMission missionDTO = new InfoMission();
        Starship starship = this.getInfoStarship(mission.getIdStarship());
        List<Planet> listPlanets = this.getInfoPlanets(mission.getPlanets());
        List<Captain> captains = this.getInfoCaptains(mission.getCaptains());
        missionDTO.setCaptains(captains);
        missionDTO.setStartDate(mission.getStartDate().toString());
        missionDTO.setEndDate(mission.getEndDate().toString());
        missionDTO.setStarship(starship);
        missionDTO.setPlanets(listPlanets);
        missionDTO.setCrew(mission.getPassengers());
        responseDTO.add(missionDTO);
    }

    private List<Captain> getInfoCaptains(String captains) {
        List<Captain> listCaptains = new ArrayList<>();
        List<Integer> lst = Arrays.stream(captains.split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        for(Integer id : lst){
            Captain captain = new Captain();
            try {
                WebClient webClient = WebClient.create(SWAPI);
                Mono<String> call = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/people/{id}/")
                                .build(id))
                        .retrieve()
                        .bodyToMono(String.class);

                String response = call.block();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode json = objectMapper.readTree(response);
                String name = json.get("name").asText();
                captain.setId(id);
                captain.setName(name);
                listCaptains.add(captain);
            }catch(Exception e){
                System.out.println("error");
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error url", e);
            }
        }
        return listCaptains;
    }

    private List<Planet> getInfoPlanets(String planets) {
        List<Planet> listPlanets = new ArrayList<>();
        List<Integer> lst = Arrays.stream(planets.split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        for(Integer id : lst){
            Planet planet = new Planet();
            try {
                WebClient webClient = WebClient.create(SWAPI);
                Mono<String> call = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/planets/{id}/")
                                .build(id))
                        .retrieve()
                        .bodyToMono(String.class);

                String response = call.block();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode json = objectMapper.readTree(response);
                String name = json.get("name").asText();
                Integer diameter = json.get("diameter").asInt();
                planet.setId(id);
                planet.setName(name);
                planet.setDiameter(diameter);
                listPlanets.add(planet);
            }catch(Exception e){
                System.out.println("error");
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Error url", e);
            }
        }
        return listPlanets;
    }

    private Starship getInfoStarship(Integer idStarship) {
        Starship starship = new Starship();
        try {
            WebClient webClient = WebClient.create(SWAPI);
            Mono<String> call = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/starships/{id}/")
                            .build(idStarship))
                    .retrieve()
                    .bodyToMono(String.class);

            String response = call.block();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(response);
            String name = json.get("name").asText();
            Integer crew = json.get("crew").asInt();
            Integer passengers = json.get("passengers").asInt();
            starship.setId(idStarship);
            starship.setName(name);
            starship.setCrew(crew);
            starship.setPassengers(passengers);
        }catch(Exception e){
            System.out.println("error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error url", e);
        }
        return starship;
    }

    @Override
    public ListMissionResponseDTO getStarWarsRecommendedMission(String criterion) {
        return null;
    }
}
