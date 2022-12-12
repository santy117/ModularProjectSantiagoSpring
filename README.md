# Technical work Spring + Star Wars API
This work is a challenge from Eccocar to implement a API REST to manage staraships missions using swapi.

I have used Java, Maven, Spring Boot, JPA, Hibernate, OpenAPI, Google Cloud and MySQL for this solution.

## Optional improvements:
Creation of a MySQL relational database to store the missions created.      
Deployment of the database in Google Cloud.   
OpenApi 3.0 specification for the creation of endpoints.    
## Parameters needed:
To create a mission, we need to know beforehand the IDs of the following parameters to enter:

starship    
captains  
planets   
This information that can be obtained through the Star Wars API: https://swapi.co/

## Features:
Create a mission  
List all missions   
List missions filtered by captain IDs   

## Requirements:
The project is made with Java8, you need the JVM installed in order to run the project.   
After cloning the repository, in the root folder, we need to first build the project with Maven to generate de JAR file: 
```
mvn clean package
```
This command will generate a JAR file located in \service\target\service-1.0-SNAPSHOT.jar   
The database is deployed in Google Cloud to be able to connect anywhere, but we need to specify the password for the connection when executing the JAR file:    
```
java -jar .\service\target\service-1.0-SNAPSHOT.jar --spring.datasource.password=pruebaTecnica1234
```
The application will start in http://localhost:8080/ with a JSON body with the following parameters:

## Usage:
### Create a mission:       
POST Request at http://localhost:8080/StarWars/createMission      
```
{
  "startDate": "dd/mm/aaaa",
  "starshipId": {id},
  "captainIds": [
    {id}
  ],
  "crew": {crew},
  "planetIds": [
    {id}
  ]
}
```

An example of a JSON body for this request:       
```
{
  "startDate": "11/11/2022",
  "starshipId": 5,
  "captainIds": [
    3,
    2
  ],
  "crew": 15,
  "planetIds": [
    1,
    2,
    3
  ]
}
```
### List Missions:
GET Request at http://localhost:8080/StarWars/listMission     

### List Missions by captain IDs:
GET Request at http://localhost:8080/StarWars/listMission?captain={id} with the path parameter captain to filter by a captain ID

An example of a GET Request to filter missions by 2 captain IDs:      

http://localhost:8080/StarWars/listMission?captain=2&captain=1
