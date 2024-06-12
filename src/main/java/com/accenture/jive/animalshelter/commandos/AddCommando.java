package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;
import com.accenture.jive.animalshelter.services.AnimalService;
import com.accenture.jive.animalshelter.services.AnimalTypeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AddCommando implements Commando {

    public Scanner scanner;
    private final AnimalService animalService;
    private final AnimalTypeService animalTypeService;

    //This is the constructor which takes all the mandatory information from the AnimalShelter class that we need to create new cats
    public AddCommando(Scanner scanner, AnimalService animalService, AnimalTypeService animalTypeService) {
        this.scanner = scanner;
        this.animalService = animalService;
        this.animalTypeService = animalTypeService;
    }

    @Override
    public boolean execute() throws CommandoException {
        System.out.println("What is this animals species? Enter the appropriate id");
        try {

            animalTypeService.readAnimalTypes();

            String animalType = scanner.nextLine();
            if ("exit".equalsIgnoreCase(animalType.trim())) {
                return false;
            }

            Integer animalTypeId = null;
            try {
                animalTypeId = Integer.parseInt(animalType);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid ID - this must be a number");
                animalType = scanner.nextLine();
                animalTypeId = Integer.parseInt(animalType);
            }
            System.out.println("What is the animals name?");
            String animalName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(animalName.trim())) {
                return false;
            }
            System.out.println("How old is this Animal? Enter a number");
            String animalAge = scanner.nextLine();

            if ("exit".equalsIgnoreCase(animalAge.trim())) {
                return false;
            }
            int parsedAge;
            try {
                parsedAge = Integer.parseInt(animalAge);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age- this must be a number");
                animalAge = scanner.nextLine();
                parsedAge = Integer.parseInt(animalAge);

            }

            String retrievedAnimalType = animalTypeService.readAnimalTypeById(animalTypeId);

            System.out.println(retrievedAnimalType);

            Animal animal;
            if ("cat".equals(retrievedAnimalType)) {
                animal = new Cat();
                animal.setName(animalName);
                animal.setAge(parsedAge);
            } else {
                animal = new Dog();
                animal.setName(animalName);
                animal.setAge(parsedAge);
            }

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
