package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantController {

    private final PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @PostMapping("/plants")
    private Plant addPlant(@RequestBody Plant plant) {
        return plantRepository.save(plant);
    }

    @GetMapping("/plants/{id}")
    private Plant getPlants(@PathVariable Long id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new PlantNotFoundException(id));
    }

    @GetMapping("/plants")
    private List<Plant> getPlants(@RequestParam(required = false) String name) {
        // using the name is for the bonus task
        if(name != null) {
            return plantRepository.findAllByName(name);
        }
        return plantRepository.findAll();
    }

    @PutMapping("/plants/{id}")
    private Plant updatePlant(@PathVariable Long id, @RequestBody Plant newPlant) {
      return plantRepository.findById(id).map(plant -> {
          plant.setName(newPlant.getName());
          plant.setPrice(newPlant.getPrice());
          plant.setQuantity(newPlant.getQuantity());
          plant.setColour(newPlant.getColour());
          return plantRepository.save(plant);
      }).orElseGet(() -> {
          newPlant.setId(id);
          return plantRepository.save(newPlant);
      });
    }

    @DeleteMapping("/plants/{id}")
    private void deletePlants(@PathVariable Long id) {
         plantRepository.deleteById(id);
    }
}
