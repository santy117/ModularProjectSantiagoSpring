package com.example.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Starship
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-11T19:43:20.061+01:00[Europe/Paris]")
public class Starship   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("crew")
  private Integer crew = null;

  @JsonProperty("passengers")
  private Integer passengers = null;

  public Starship id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Starship name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Starship crew(Integer crew) {
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

  public Starship passengers(Integer passengers) {
    this.passengers = passengers;
    return this;
  }

  /**
   * Get passengers
   * @return passengers
  **/
  @ApiModelProperty(value = "")
  
    public Integer getPassengers() {
    return passengers;
  }

  public void setPassengers(Integer passengers) {
    this.passengers = passengers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Starship starship = (Starship) o;
    return Objects.equals(this.id, starship.id) &&
        Objects.equals(this.name, starship.name) &&
        Objects.equals(this.crew, starship.crew) &&
        Objects.equals(this.passengers, starship.passengers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, crew, passengers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Starship {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    crew: ").append(toIndentedString(crew)).append("\n");
    sb.append("    passengers: ").append(toIndentedString(passengers)).append("\n");
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
