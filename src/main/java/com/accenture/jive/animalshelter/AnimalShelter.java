package com.accenture.jive.animalshelter;

import java.util.ArrayList;
import java.util.Scanner;

public class AnimalShelter {

    public void run() {
        //importing and setting up the factories to create new animal objects
        CatFactory catFactory = new CatFactory();
        DogFactory dogFactory = new DogFactory();

        //creating first animal as a test
        //IMPORTANT: since AnimalFactory and the method is non static we use them outside ot the main method
        Cat mieke = catFactory.createAnimal("Mieke", 6);

        ArrayList<Animal> animalsInShelter = new ArrayList<>();
        animalsInShelter.add(mieke);

        //Creating the scanner to use throughout the REPL application
        //The Scanner constructor always need some parameters like System.in
        Scanner scanner = new Scanner(System.in);

        //Creating an instance of addCommando to use its function
        AddCommando addCommando = new AddCommando(scanner, catFactory, dogFactory, animalsInShelter);

        //Creating an instance of showCommando so that all animals in the shelter can be printed
        ShowCommando showCommando = new ShowCommando(animalsInShelter);

        //Loop so that the user gets prompted to select a repl action
        //condition to ensure loop does not loop indefinitely
        boolean runApp = true;

        while (runApp) {
            System.out.println("Welcome to the Animal Shelter. How can we help you? ");
            System.out.println("Enter 'show' to see all animals. Enter 'add' to abandon your animal.");
            String userInput = scanner.nextLine();
            if ("exit".equalsIgnoreCase(userInput)) {
                runApp = false;
            } else if ("show".equalsIgnoreCase(userInput)) {
                showCommando.execute();
            } else if ("add".equalsIgnoreCase(userInput)) {
                addCommando.execute();
            } else {
                System.out.println("Sorry I don't know what you want?! Try again!");
            }
        }

        System.out.println("Bye bye. ");
    }

    public static void main(String[] args) {
        //IMPORTANT: for the run method to work we need an object, so we first create an object of the class AnimalShelter
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.run();
    }
}
