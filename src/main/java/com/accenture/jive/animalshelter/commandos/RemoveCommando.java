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
    private final Scanner scanner;
    private final AnimalService animalService;
    private final UserInteraction userInteraction;

    public RemoveCommando(Scanner scanner, AnimalService animalService, UserInteraction userInteraction) {

        this.scanner = scanner;
        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {
        System.out.println("Which of these animals do you want to adopt? ");
        try {
            List<Animal> animals = animalService.readAnimals();

            for (Animal animal : animals) {
                System.out.println(animal.getId() + " - " + animal.getName());
            }

            String selectedAnimalIdAsString = scanner.nextLine();

//QUESTION: das exit mache ich immer und immer wieder - wo kann ich es hinpacken sodass ich es wiederverwenden kann?
            if ("exit".equalsIgnoreCase(selectedAnimalIdAsString)) {
                return false;
            }
            Integer selectedAnimalId = userInteraction.askForNumber("Enter the animal ID", "Enter a valid ID - this must be a number");
           

            int i = animalService.removeAnimal(selectedAnimalId);
            if (i > 0) {
                System.out.println("\u001B[36m" + "204: Removal from Shelter successfull" + "\u001B[0m");
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
