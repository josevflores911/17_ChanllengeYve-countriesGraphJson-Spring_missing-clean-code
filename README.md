# Yve-countries-graph-Spring
graph project with spring


goals

● If there is no land crossing, the endpoint returns HTTP 400 

● HTTP request sample (land route from Czech Republic to Italy) 

● The application exposes REST endpoint /routing/{origin}/{destination} that returns a list of border crossings to get from origin to destination 


----------------------------------------------------------


this Spring Boot service is able to calculate any possible land route from one country to 
another by utilizing individual countries border information

## used dependencies

spring web
spring data jpa
h2 database
lombok
jackson-databind

## task

the application exposes REST endpoint -> /routing/{ origin }/{ destination } 
that return a list of borders crossing to get from origin to destination

a single route is returned if the journey is possible, case not , return a message  "no path founded" 
and a status HTTP 404

## introduction

INPUT

the countries are came from  folder {jsondata}  inside the project with the name countries.json 
to use this file was used The Jackson library API, a very widespread and efficient Java-based library
 which is used to map or serialize the Java objects to JSON and vice versa

ARCHITECTURE
### controller
-countriesController

### domain(model)

	-Country(used)
		->id(added)
		->cca3
		->region
		->latlng
		->borders
	-Name(used)
		->common
    
this variables or classes was builded but not used
	-Awg
	-Currencies
	-Demonyms
	-Eng
	-Fra
	-Idd
	-Language
	-Nld
	-Pap

@JsonIgnoreProperties(ignoreUnknown = true): this annotation  ignores the specified logical properties in JSON serialization and deserialization

there was some properties that wasnt used so using this anotation avoid exceptions on load

@Embedded
@Embeddable

@Embedded annotation is used to specify the {child class} entity should be stored in the {parent class} table as a component. 
@Embeddable annotation is used to specify the Address class will be used as a component

and example of variables not included is translations that have many information that for this purpose can be desconsidered

### repository

CrudRepository and JpaRepository 
JpaRepository offer more tools to work with databases

### service
CountryService have all the required methods from jpaRepository
SearchService have 2 methods 
the first  to see if there is a path between those countries
the second look that path by using the algorithm with the latitude and longitude 

## Instructions

api is running on por 8090 
	
	ENDPOINTS

localhost:8090/list  -> return a list with all the information inside the json file that was recorded for the api naturally ordered

localhost:8090/borderList  -> return a list of borders of all the countries inside the json, naturally ordered

localhost:8090/routing/{origin}/{destination} -> return a list that content all the countries(cca3 identified), required origin (cca3 identified) and destination(cca3 identified) in that ordered the result is the shortest way possible





