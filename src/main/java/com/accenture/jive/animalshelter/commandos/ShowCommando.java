package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommando implements Commando {
    private final AnimalService animalService;
    public ArrayList<Animal> animalsInShelter;

    public ShowCommando(AnimalService animalService) {
        this.animalService = animalService;

    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show".equalsIgnoreCase(userCommando);
    }

    @Override
    public boolean execute() throws SQLException {

        List<Animal> animals = animalService.readAnimals();
        System.out.println("All these animals are waiting for their forever home: ");
        for (Animal animal : animals) {
            System.out.println(animal.getName() + " is " + animal.getAge() + " years old");
        }

        //TODO: filter and give out two lists: one for dogs and one for cats
        return true;
    }


}

