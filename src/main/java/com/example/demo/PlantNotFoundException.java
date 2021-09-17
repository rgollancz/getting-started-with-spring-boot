package com.example.demo;

public class PlantNotFoundException extends RuntimeException {
    public PlantNotFoundException(Long id) {
        super("Could not find plant " + id);
    }
}
