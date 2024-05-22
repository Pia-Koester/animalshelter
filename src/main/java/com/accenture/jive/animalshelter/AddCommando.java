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

    public boolean execute() {
        System.out.println("What is this animals species? (cat|dog)");
        String animalSpecies = scanner.nextLine();
        System.out.println("What is the animals name?");
        String animalName = scanner.nextLine();
        System.out.println("How old is this Animal? Enter a number");
        String animalAge = scanner.nextLine();
        //QUESTION: How can I write an error message telling users to only enter a number?

        int parsedAge = Integer.parseInt(animalAge);
        if ("exit".equalsIgnoreCase(animalSpecies.trim())) {
            return false;
        }
        if ("cat".equals(animalSpecies.trim())) {
            Cat cat = catFactory.createAnimal(animalName, parsedAge);
            //QUESTION: ist das nicht zu viel wiederholung mit unten dem Doggy? - wie in eine eigene Methode packen?
            boolean acceptCat = false;
            if (animalsInShelter.contains(cat)) {
                System.out.println("We already have a cat called " + cat.name + " which is " + cat.age + " years old.");
                System.out.println("no more of these cats allowed");
            } else {
                acceptCat = true;
            }
           /* for (Animal animal : animalsInShelter) {
                if (cat.equals(animal)) {
                    System.out.println("We already have a cat called " + cat.name + " which is " + cat.age + " years old.");
                    System.out.println("no more of these cats allowed");
                } else {
                    acceptCat = true;
                }
            }*/
            if (acceptCat) {
                animalsInShelter.add(cat);
            }
        } else if ("dog".equals(animalSpecies.trim())) {
            Dog dog = dogFactory.createAnimal(animalName, parsedAge);
            boolean acceptDog = false;
            if (animalsInShelter.contains(dog)) {
                System.out.println("We already have a dog called " + dog.name + " which is " + dog.age + " years old.");
                System.out.println("no more of these dogs allowed");
            } else {
                acceptDog = true;
            }
          /*  for (Animal animal : animalsInShelter) {
                if (dog.equals(animal)) {
                    System.out.println("We already have a dog called " + dog.name + " which is " + dog.age + " years old.");
                    System.out.println("no more of these dogs allowed");
                } else {
                    acceptDog = true;
                }
            }*/
            if (acceptDog) {
                animalsInShelter.add(dog);
            }

        } else {
            System.out.println("Our Animal Shelter can currently only accept cats or dogs :(");
        }
        return true; //Muss returned werden, weil mein Commando ja jetzt einen return value boolean hat

    }
}
