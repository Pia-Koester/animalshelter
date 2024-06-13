package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.UserInteraction;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RemoveCommando implements Commando {
    private final AnimalService animalService;
    private final UserInteraction userInteraction;

    public RemoveCommando(AnimalService animalService, UserInteraction userInteraction) {
        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {
        userInteraction.responseWriter("Which of these animals do you want to adopt? ", null);
        try {
            List<Animal> animals = animalService.readAnimals();

            for (Animal animal : animals) {
                userInteraction.responseWriter(animal.getId() + " - " + animal.getName(), null);
            }

            Integer selectedAnimalId = userInteraction.askForNumber("Enter the animal ID", "Enter a valid ID - this must be a number");


            int i = animalService.removeAnimal(selectedAnimalId);
            if (i > 0) {
                userInteraction.responseWriter("204: Removal from Shelter successfull", "blue");
            }
        } catch (SQLException e) {
            throw new CommandoException("Animal cannot be removed", e);
        }

        return true;
    }


    @Override
    public boolean shouldExecute(String userCommando) {
        return "adopt".equalsIgnoreCase(userCommando);
    }
}
