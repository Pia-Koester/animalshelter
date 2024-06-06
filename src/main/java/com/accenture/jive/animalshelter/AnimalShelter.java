package com.accenture.jive.animalshelter;

import com.accenture.jive.animalshelter.commandos.AddCommando;
import com.accenture.jive.animalshelter.commandos.Commando;
import com.accenture.jive.animalshelter.commandos.ExitCommando;
import com.accenture.jive.animalshelter.commandos.ShowCommando;

import java.util.ArrayList;
import java.util.List;
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

        List<Commando> commandos = createCommandos(scanner, catFactory, dogFactory, animalsInShelter);


        //Loop so that the user gets prompted to select a repl action
        //condition to ensure loop does not loop indefinitely
        boolean runApp = true;

        while (runApp) {
            System.out.println("Welcome to the Animal Shelter. How can we help you? ");
            System.out.println("Enter 'show' to see all animals. Enter 'add' to abandon your animal.");
            String userInput = scanner.nextLine();
            for (Commando commando : commandos) {
                if (commando.shouldExecute(userInput)) {
                    runApp = commando.execute();
                }
            }
            //QUESTION: Wie könnte ich hier sowas wie - kein passendes Kommando als Rückmeldung einbauen?
        }

        System.out.println("Bye bye. ");
    }

    public List<Commando> createCommandos(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, ArrayList<Animal> animalsInShelter) {
        List<Commando> commandos = new ArrayList<>();
        //Creating an instance of addCommando to use its function
        Commando addCommando = new AddCommando(scanner, catFactory, dogFactory, animalsInShelter);

        //Creating an instance of showCommando so that all animals in the shelter can be printed
        Commando showCommando = new ShowCommando(animalsInShelter);
        Commando exitCommando = new ExitCommando();
        commandos.add(addCommando);
        commandos.add(showCommando);
        commandos.add(exitCommando);
        return commandos;
    }

    public static void main(String[] args) {
        //IMPORTANT: for the run method to work we need an object, so we first create an object of the class AnimalShelter
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.run();
    }
}
