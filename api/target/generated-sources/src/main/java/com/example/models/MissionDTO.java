package com.example.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This is the mission model
 */
@ApiModel(description = "This is the mission model")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-11T19:43:20.061+01:00[Europe/Paris]")
public class MissionDTO   {
  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("starshipId")
  private Integer starshipId = null;

  @JsonProperty("captainIds")
  @Valid
  private List<Integer> captainIds = new ArrayList<Integer>();

  @JsonProperty("crew")
  private Integer crew = null;

  @JsonProperty("planetIds")
  @Valid
  private List<Integer> planetIds = new ArrayList<Integer>();

  public MissionDTO startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public MissionDTO starshipId(Integer starshipId) {
    this.starshipId = starshipId;
    return this;
  }

  /**
   * Get starshipId
   * @return starshipId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getStarshipId() {
    return starshipId;
  }

  public void setStarshipId(Integer starshipId) {
    this.starshipId = starshipId;
  }

  public MissionDTO captainIds(List<Integer> captainIds) {
    this.captainIds = captainIds;
    return this;
  }

  public MissionDTO addCaptainIdsItem(Integer captainIdsItem) {
    this.captainIds.add(captainIdsItem);
    return this;
  }

  /**
   * Get captainIds
   * @return captainIds
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public List<Integer> getCaptainIds() {
    return captainIds;
  }

  public void setCaptainIds(List<Integer> captainIds) {
    this.captainIds = captainIds;
  }

  public MissionDTO crew(Integer crew) {
    this.crew = crew;
    return this;
  }

  /**
   * Get crew
   * @return crew
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getCrew() {
    return crew;
  }

  public void setCrew(Integer crew) {
    this.crew = crew;
  }

  public MissionDTO planetIds(List<Integer> planetIds) {
    this.planetIds = planetIds;
    return this;
  }

  public MissionDTO addPlanetIdsItem(Integer planetIdsItem) {
    this.planetIds.add(planetIdsItem);
    return this;
  }

  /**
   * Get planetIds
   * @return planetIds
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public List<Integer> getPlanetIds() {
    return planetIds;
  }

  public void setPlanetIds(List<Integer> planetIds) {
    this.planetIds = planetIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MissionDTO missionDTO = (MissionDTO) o;
    return Objects.equals(this.startDate, missionDTO.startDate) &&
        Objects.equals(this.starshipId, missionDTO.starshipId) &&
        Objects.equals(this.captainIds, missionDTO.captainIds) &&
        Objects.equals(this.crew, missionDTO.crew) &&
        Objects.equals(this.planetIds, missionDTO.planetIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, starshipId, captainIds, crew, planetIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MissionDTO {\n");
    
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    starshipId: ").append(toIndentedString(starshipId)).append("\n");
    sb.append("    captainIds: ").append(toIndentedString(captainIds)).append("\n");
    sb.append("    crew: ").append(toIndentedString(crew)).append("\n");
    sb.append("    planetIds: ").append(toIndentedString(planetIds)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
