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
    private final Scanner scanner;
    private final AnimalService animalService;
    private final UserInteraction userInteraction;

    public UpdateCommando(Scanner scanner, AnimalService animalService, UserInteraction userInteraction) {

        this.scanner = scanner;
        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {
        System.out.println("The Animals are celebrating a birthday:");
        try {
            List<Animal> animals = animalService.readAnimals();

            for (Animal animal : animals) {
                System.out.println(animal.getId() + " - Name: " + animal.getName() + " age: " + animal.getAge());
            }

            Integer animalId = userInteraction.askForNumber("Select which animal got one year older", "Enter the animal ID");

            int i = animalService.updateAnimal(animalId);
            if (i > 0) {
                System.out.println("\u001B[36m" + "200: Update successfull - Happy Birthday!" + "\u001B[0m");
            }

            List<Animal> updatedAnimals = animalService.readAnimals();

            for (Animal updatedAnimal : updatedAnimals) {
                System.out.println(updatedAnimal.getId() + " - Name: " + updatedAnimal.getName() + " age: " + updatedAnimal.getAge());

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
