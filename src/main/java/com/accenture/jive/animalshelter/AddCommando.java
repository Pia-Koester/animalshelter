package com.accenture.jive.animalshelter;

import java.util.ArrayList;
import java.util.Scanner;

public class AddCommando {

    public Scanner scanner;
    public CatFactory catFactory;
    private final DogFactory dogFactory;
    public ArrayList<Animal> animalsInShelter;

    //This is the constructor which takes all the mandatory information from the AnimalShelter class that we need to create new cats
    public AddCommando(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, ArrayList<Animal> animalsInShelter) {
        this.scanner = scanner;
        this.catFactory = catFactory;
        this.dogFactory = dogFactory;
        this.animalsInShelter = animalsInShelter;
    }

    public void execute() {
        System.out.println("What is this animals species? (cat|dog)");
        String animalSpecies = scanner.nextLine();
        System.out.println("What is the animals name?");
        String animalName = scanner.nextLine();
        System.out.println("How old is this Animal? Enter a number");
        String animalAge = scanner.nextLine();
        //QUESTION: How can I write an error message telling users to only enter a number?

        int parsedAge = Integer.parseInt(animalAge);

        if ("cat".equals(animalSpecies)) {
            Cat cat = catFactory.createAnimal(animalName, parsedAge);
            animalsInShelter.add(cat);
        } else if ("dog".equals(animalSpecies)) {
            Dog dog = dogFactory.createAnimal(animalName, parsedAge);
            animalsInShelter.add(dog);
        } else {
            System.out.println("Our Animal Shelter can currently only accept cats or dogs :(");
        }

    }
}
