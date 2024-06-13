package com.accenture.jive.animalshelter;

import com.accenture.jive.animalshelter.commandos.*;
import com.accenture.jive.animalshelter.factories.AnimalFactory;
import com.accenture.jive.animalshelter.services.AnimalService;
import com.accenture.jive.animalshelter.services.AnimalTypeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalShelter {

    public void run(Connection connection) {

        Scanner scanner = new Scanner(System.in);

        AnimalService animalService = new AnimalService(connection);
        AnimalTypeService animalTypeService = new AnimalTypeService(connection);
        AnimalFactory animalFactory = new AnimalFactory();
        UserInteraction userInteraction = new UserInteraction(scanner);

        List<Commando> commandos = createCommandos(animalService, animalTypeService, animalFactory, userInteraction);

        boolean runApp = true;
        while (runApp) {
            userInteraction.responseWriter("Welcome to the Animal Shelter. How can we help you? ", null);
            userInteraction.responseWriter("Enter 'help' if you don't know what to do", "purple");
            String userInput = scanner.nextLine();
            for (Commando commando : commandos) {
                if (commando.shouldExecute(userInput)) {
                    try {
                        runApp = commando.execute();
                    } catch (CommandoException e) {
                        userInteraction.responseWriter("Something went wrong", "red");
                        e.printStackTrace();
                    }
                }
            }
            //TODO: Wie könnte ich hier sowas wie - kein passendes Kommando als Rückmeldung einbauen?
            //if(!ran) {dann rückmeldung dass das usercommando bullshit war}
        }

        userInteraction.responseWriter("Bye bye. ", "green");

    }

    public List<Commando> createCommandos(AnimalService animalService, AnimalTypeService animalTypeService, AnimalFactory animalFactory, UserInteraction userInteraction) {
        List<Commando> commandos = new ArrayList<>();

        Commando addCommando = new AddCommando(animalService, animalTypeService, animalFactory, userInteraction);
        Commando showCommando = new ShowCommando(animalService, userInteraction);
        Commando exitCommando = new ExitCommando();
        Commando showByIdCommando = new ShowByIdCommando(animalService, userInteraction);
        Commando updateCommando = new UpdateCommando(animalService, userInteraction);
        Commando removeCommando = new RemoveCommando(animalService, userInteraction);
        Commando helpCommando = new HelpCommando();

        commandos.add(addCommando);
        commandos.add(showCommando);
        commandos.add(exitCommando);
        commandos.add(showByIdCommando);
        commandos.add(updateCommando);
        commandos.add(removeCommando);
        commandos.add(helpCommando);
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
