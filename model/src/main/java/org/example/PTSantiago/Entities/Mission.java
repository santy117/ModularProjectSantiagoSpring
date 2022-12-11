package org.example.PTSantiago.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "MISSION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "ID_MISSION")
    private Integer idMission;

    @Column(name= "CAPTAINS")
    private String captains;

    @Column(name= "PLANETS")
    private String planets;

    @Column(name= "STARSHIP")
    private Integer idStarship;

    @Column(name= "PASSENGERS")
    private Integer passengers;

    @Column(name= "STARTDATE")
    private Date startDate;

    @Column(name= "ENDDATE")
    private Date endDate;

}