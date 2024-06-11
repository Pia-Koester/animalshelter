package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.factories.CatFactory;
import com.accenture.jive.animalshelter.factories.DogFactory;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddCommando implements Commando {

    public Scanner scanner;
    public CatFactory catFactory;
    private final DogFactory dogFactory;
    private final Connection connection;
    private final AnimalService animalService;

    //This is the constructor which takes all the mandatory information from the AnimalShelter class that we need to create new cats
    public AddCommando(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, Connection connection, AnimalService animalService) {
        this.scanner = scanner;
        this.catFactory = catFactory;
        this.dogFactory = dogFactory;

        this.connection = connection;
        this.animalService = animalService;
    }

    @Override
    public boolean execute() throws CommandoException {
        System.out.println("What is this animals species? Enter the appropriate id");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM type;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer typeId = resultSet.getInt("type_id");
                String typeName = resultSet.getString("type_name");
                System.out.println("- " + typeId + "--> " + typeName);
            }

            String animalSpecies = scanner.nextLine();
            if ("exit".equalsIgnoreCase(animalSpecies.trim())) {
                return false;
            }

            Integer animalSpeciesId = null;
            try {
                animalSpeciesId = Integer.parseInt(animalSpecies);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid ID - this must be a number");
                animalSpecies = scanner.nextLine();
                animalSpeciesId = Integer.parseInt(animalSpecies);
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
                //throw new RuntimeException(e);
            }

            String insertSQL = "INSERT INTO animalshelter.animal (animal_name, age, type_id) " +
                    "VALUES(?, ?, ?);";

            PreparedStatement preparedStatement1 = connection.prepareStatement(insertSQL);
            preparedStatement1.setString(1, animalName);
            preparedStatement1.setInt(2, parsedAge);
            preparedStatement1.setInt(3, animalSpeciesId);

            int i = preparedStatement1.executeUpdate();
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
