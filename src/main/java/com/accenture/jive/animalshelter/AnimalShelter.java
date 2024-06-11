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

    public void run(Connection connection) {

        CatFactory catFactory = new CatFactory();
        DogFactory dogFactory = new DogFactory();

        Scanner scanner = new Scanner(System.in);

        AnimalService animalService = new AnimalService(connection);

        List<Commando> commandos = createCommandos(scanner, catFactory, dogFactory, connection, animalService);

        boolean runApp = true;
        while (runApp) {
            System.out.println("Welcome to the Animal Shelter. How can we help you? ");
            System.out.println("Enter 'show' to see all animals. Enter 'add' to abandon your animal.");
            String userInput = scanner.nextLine();
            for (Commando commando : commandos) {
                if (commando.shouldExecute(userInput)) {
                    try {
                        runApp = commando.execute();
                    } catch (CommandoException e) {
                        System.out.println("Something went wrong");
                        e.printStackTrace();
                    }
                }
            }
            //TODO: Wie könnte ich hier sowas wie - kein passendes Kommando als Rückmeldung einbauen?
            //if(!ran) {dann rückmeldung dass das usercommando bullshit war}
        }

        System.out.println("Bye bye. ");

    }

    public List<Commando> createCommandos(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, Connection connection, AnimalService animalService) {
        List<Commando> commandos = new ArrayList<>();

        Commando addCommando = new AddCommando(scanner, catFactory, dogFactory, connection, animalService);
        Commando showCommando = new ShowCommando(animalService);
        Commando exitCommando = new ExitCommando();
        Commando showByIdCommando = new ShowByIdCommando(scanner, connection);
        Commando updateCommando = new UpdateCommando(scanner, connection, animalService);
        Commando removeCommando = new RemoveCommando(scanner, connection, animalService);

        commandos.add(addCommando);
        commandos.add(showCommando);
        commandos.add(exitCommando);
        commandos.add(showByIdCommando);
        commandos.add(updateCommando);
        commandos.add(removeCommando);
        return commandos;
    }

    public static void main(String[] args) {
        try {
            Connector connector = new Connector();
            Connection connection = connector.getConnection();
            AnimalShelter animalShelter = new AnimalShelter();
            animalShelter.run(connection);
        } catch (SQLException e) {
            System.out.println("oops, something went wrong ... ");
            e.printStackTrace();
        }
    }
}
