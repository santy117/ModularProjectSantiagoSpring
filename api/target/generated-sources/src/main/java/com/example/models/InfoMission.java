package com.example.models;

import java.util.Objects;
import com.example.models.Captain;
import com.example.models.Planet;
import com.example.models.Starship;
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
 * InfoMission
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-11T19:43:20.061+01:00[Europe/Paris]")
public class InfoMission   {
  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("endDate")
  private String endDate = null;

  @JsonProperty("crew")
  private Integer crew = null;

  @JsonProperty("starship")
  private Starship starship = null;

  @JsonProperty("captains")
  @Valid
  private List<Captain> captains = null;

  @JsonProperty("planets")
  @Valid
  private List<Planet> planets = null;

  public InfoMission startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(value = "")
  
    public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public InfoMission endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  **/
  @ApiModelProperty(value = "")
  
    public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public InfoMission crew(Integer crew) {
    this.crew = crew;
    return this;
  }

  /**
   * Get crew
   * @return crew
  **/
  @ApiModelProperty(value = "")
  
    public Integer getCrew() {
    return crew;
  }

  public void setCrew(Integer crew) {
    this.crew = crew;
  }

  public InfoMission starship(Starship starship) {
    this.starship = starship;
    return this;
  }

  /**
   * Get starship
   * @return starship
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Starship getStarship() {
    return starship;
  }

  public void setStarship(Starship starship) {
    this.starship = starship;
  }

  public InfoMission captains(List<Captain> captains) {
    this.captains = captains;
    return this;
  }

  public InfoMission addCaptainsItem(Captain captainsItem) {
    if (this.captains == null) {
      this.captains = new ArrayList<Captain>();
    }
    this.captains.add(captainsItem);
    return this;
  }

  /**
   * Get captains
   * @return captains
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Captain> getCaptains() {
    return captains;
  }

  public void setCaptains(List<Captain> captains) {
    this.captains = captains;
  }

  public InfoMission planets(List<Planet> planets) {
    this.planets = planets;
    return this;
  }

  public InfoMission addPlanetsItem(Planet planetsItem) {
    if (this.planets == null) {
      this.planets = new ArrayList<Planet>();
    }
    this.planets.add(planetsItem);
    return this;
  }

  /**
   * Get planets
   * @return planets
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<Planet> getPlanets() {
    return planets;
  }

  public void setPlanets(List<Planet> planets) {
    this.planets = planets;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoMission infoMission = (InfoMission) o;
    return Objects.equals(this.startDate, infoMission.startDate) &&
        Objects.equals(this.endDate, infoMission.endDate) &&
        Objects.equals(this.crew, infoMission.crew) &&
        Objects.equals(this.starship, infoMission.starship) &&
        Objects.equals(this.captains, infoMission.captains) &&
        Objects.equals(this.planets, infoMission.planets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, crew, starship, captains, planets);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoMission {\n");
    
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    crew: ").append(toIndentedString(crew)).append("\n");
    sb.append("    starship: ").append(toIndentedString(starship)).append("\n");
    sb.append("    captains: ").append(toIndentedString(captains)).append("\n");
    sb.append("    planets: ").append(toIndentedString(planets)).append("\n");
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
