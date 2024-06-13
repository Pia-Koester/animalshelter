package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.UserInteraction;
import com.accenture.jive.animalshelter.factories.AnimalFactory;
import com.accenture.jive.animalshelter.services.AnimalService;
import com.accenture.jive.animalshelter.services.AnimalTypeService;

import java.sql.SQLException;
import java.util.Scanner;

public class AddCommando implements Commando {

    public Scanner scanner;
    private final AnimalService animalService;
    private final AnimalTypeService animalTypeService;
    private final AnimalFactory animalFactory;
    private final UserInteraction userInteraction;


    //This is the constructor which takes all the mandatory information from the AnimalShelter class that we need to create new cats
    public AddCommando(Scanner scanner, AnimalService animalService, AnimalTypeService animalTypeService, AnimalFactory animalFactory, UserInteraction userInteraction) {
        this.scanner = scanner;
        this.animalService = animalService;
        this.animalTypeService = animalTypeService;
        this.animalFactory = animalFactory;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {

        System.out.println("Which animal do you want to abandon? ");
        try {
            animalTypeService.readAnimalTypes();
            Integer animalTypeId =
                    userInteraction
                            .askForNumber(
                                    "What is this animals species? Enter the id",
                                    "please enter a number");
//            if ("exit".equalsIgnoreCase(animalType.trim())) {
//                return false;
//            }

            System.out.println("What is the animals name?");
            String animalName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(animalName.trim())) {
                return false;
            }

//            if ("exit".equalsIgnoreCase(animalAge.trim())) {
//                return false;
//            }
            int parsedAge = userInteraction
                    .askForNumber(
                            "How old is this Animal? Enter a number",
                            "Please enter a valid age- this must be a number");


            String retrievedAnimalType = animalTypeService.readAnimalTypeById(animalTypeId);

            Animal animal = animalFactory.create(retrievedAnimalType, animalName, parsedAge);

            int i = animalService.addAnimal(animal, animalTypeId);

            if (i > 0) {
                System.out.println("\u001B[36m" + "200: Animal successfully added!" + "\u001B[0m");
            }
            //TODO: read last id methode und dann id mit object zurückgeben
        } catch (SQLException e) {
            throw new CommandoException("Adding the animal did not work", e);
        }

        return true; //Muss returned werden, weil mein Commando ja jetzt einen return value boolean hat

    }


    @Override
    public boolean shouldExecute(String userCommando) {
        return "add".equalsIgnoreCase(userCommando);
    }
}
