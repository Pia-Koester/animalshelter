package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.UserInteraction;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UpdateCommando implements Commando {
    private final AnimalService animalService;
    private final UserInteraction userInteraction;

    public UpdateCommando(AnimalService animalService, UserInteraction userInteraction) {

        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {
        userInteraction.responseWriter("The Animals are celebrating a birthday:", null);
        try {
            List<Animal> animals = animalService.readAnimals();

            for (Animal animal : animals) {
                userInteraction.responseWriter(animal.getId() + " - Name: " + animal.getName() + " age: " + animal.getAge(), null);
            }

            Integer animalId = userInteraction.askForNumber("Select which animal got one year older", "Enter the animal ID");

            int i = animalService.updateAnimal(animalId);
            if (i > 0) {
                userInteraction.responseWriter("200: Update successfull - Happy Birthday!", "purple");
            }

            List<Animal> updatedAnimals = animalService.readAnimals();

            for (Animal updatedAnimal : updatedAnimals) {
                userInteraction.responseWriter(updatedAnimal.getId() + " - Name: " + updatedAnimal.getName() + " age: " + updatedAnimal.getAge(), null);
            }
        } catch (SQLException e) {
            throw new CommandoException("Updating the animal did not work", e);
        }

        return true;
    }


    @Override
    public boolean shouldExecute(String userCommando) {
        return "birthday".equalsIgnoreCase(userCommando);
    }
}
