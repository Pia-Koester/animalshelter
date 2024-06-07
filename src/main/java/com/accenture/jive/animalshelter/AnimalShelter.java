package com.accenture.jive.animalshelter;

import com.accenture.jive.animalshelter.commandos.*;
import com.accenture.jive.animalshelter.factories.CatFactory;
import com.accenture.jive.animalshelter.factories.DogFactory;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalShelter {

    public void run() throws SQLException {

        Connector connector = new Connector();
        Connection connection = connector.getConnection();

        //importing and setting up the factories to create new animal objects
        CatFactory catFactory = new CatFactory();
        DogFactory dogFactory = new DogFactory();

        //Creating the scanner to use throughout the REPL application
        //The Scanner constructor always need some parameters like System.in
        Scanner scanner = new Scanner(System.in);


        //Service classes
        AnimalService animalService = new AnimalService(connection);

        List<Commando> commandos = createCommandos(scanner, catFactory, dogFactory, connection, animalService);
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

    public List<Commando> createCommandos(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, Connection connection, AnimalService animalService) {


        List<Commando> commandos = new ArrayList<>();
        //Creating an instance of addCommando to use its function
        Commando addCommando = new AddCommando(scanner, catFactory, dogFactory, connection);

        //Creating an instance of showCommando so that all animals in the shelter can be printed
        Commando showCommando = new ShowCommando(animalService);
        Commando exitCommando = new ExitCommando();
        Commando showByIdCommando = new ShowByIdCommando(scanner, connection);
        Commando updateCommando = new UpdateCommando(scanner, connection);

        commandos.add(addCommando);
        commandos.add(showCommando);
        commandos.add(exitCommando);
        commandos.add(showByIdCommando);
        commandos.add(updateCommando);
        return commandos;
    }

    public static void main(String[] args) throws SQLException {
        //IMPORTANT: for the run method to work we need an object, so we first create an object of the class AnimalShelter
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.run();
    }
}
