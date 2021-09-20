# Lab Exercise 2

Now we will extend the Application we created earlier to create a model API for a plant shop where we can add plants and update the price and stock levels. 

_NB this exercise was written to be used with Visual Studio Code. You may have to adapt it for use with other IDEs_

**Add an Entity**
  - Create a class called Plant
  - Add the @Entity annotation (from javax.persistence)
  - Add the fields for id, name, colour, quantity and price
  - Add the appropriate getters and setters (you can install the vscode plugin “Java Code Generators” and use ctrl + shift + p “Generate getters and setters” to generate them for you)
  - Add the annotations @Id and @GeneratedValue to the id field (also from javax.persistence)

**Create the repository**
  - Create an empty interface which extends JpaRepository<Plant, Long>

**Add new endpoints**
  - In your controller add a field for the Plant repository and add a constructor to initialize it
```
private PlantRepository plantRepository;

public PlantController(PlantRepository plantRepository) {
   this.plantRepository = plantRepository;
}
```

**Create a method for adding plants**
  - Use the annotation @PostMapping(“/plants”) on the method
  - The method should take a parameter of type Plant which should have the @RequestBody annotation
  - Use the plant repository to save the plant
  - Add a method to get by id
```
Example request to test:
POST http://localhost:8080/plants
Content-Type: application/json
{
  "name": "lily",
  "colour": "white",
   "quantity": 5,
  "price": 16.99
}
```

**Create a method for getting plants**
  - Add a new get method that uses the annotation @GetMapping("/plants/{id}")
  - Add a parameter called name and use the @PathVariable annotation
  - Add a function to update the details of a plant 
  - Use the repository function “findById”
  - Create a specific exception for when a plant is not found and throw it in that case. See below for an example exception class
```
public class PlantNotFoundException extends RuntimeException {
   public PlantNotFoundException(Long id) {
       super("Could not find plant " + id);
   }
}
```

**Return a 404 for that exception by adding the following class**
```
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class PlantNotFoundAdvice {
   @ResponseBody
   @ExceptionHandler
   @ResponseStatus(HttpStatus.NOT_FOUND)
   String plantNotFoundHandler(PlantNotFoundException exception) {
       return exception.getMessage();
   }
}
```

Example request to test:
```
GET http://localhost:8080/plants/1
Accept: application/json
```

**Add a method to get plants**
  - Use the @GetMapping(“/plants”)
  - Use the repository to get all of the plants
  - Return a list of all the plants

Example request to test:
```
GET http://localhost:8080/plants
Accept: application/json
```

**Add a method to update a plant**
  - Use @PutMapping(“/plants/{id}”)
  - You will need to use both @PathVariable and @RequestBody
  - You should create a new plant with the id if the id doesn’t exist

Example request to test:
```
PUT http://localhost:8080/plants/1
Content-Type: application/json
{ 
  "name": "lily",
   "colour": "white", 
   "quantity": 3,
  "price": 10.99
}
```

**Add a function to delete a plant**
  - Use @DeleteMapping(“/plants/{id}”)

Example request to test:
```
DELETE  http://localhost:8080/plants/1
Accept: application/json
```

**Bonus: Update getPlants to take an optional QueryParam to search by name**
  - Create a function that returns a list of plants, and still returns all plants if the query param is missing
  - You will need to use @RequestParam(required = false)
  - You will need to add the following "findAllByName" function to your plant repository

```
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PlantRepository extends JpaRepository<Plant, Long> {
   List<Plant> findAllByName(String name);
}
```

Example request to test:
```
GET http://localhost:8080/plants?name=lily
Accept: application/json
```



