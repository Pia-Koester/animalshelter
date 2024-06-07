package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.*;
import com.accenture.jive.animalshelter.factories.CatFactory;
import com.accenture.jive.animalshelter.factories.DogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddCommando implements Commando {

    public Scanner scanner;
    public CatFactory catFactory;
    private final DogFactory dogFactory;
    public ArrayList<Animal> animalsInShelter;
    private final Connection connection;

    //This is the constructor which takes all the mandatory information from the AnimalShelter class that we need to create new cats
    public AddCommando(Scanner scanner, CatFactory catFactory, DogFactory dogFactory, ArrayList<Animal> animalsInShelter, Connection connection) {
        this.scanner = scanner;
        this.catFactory = catFactory;
        this.dogFactory = dogFactory;
        this.animalsInShelter = animalsInShelter;
        this.connection = connection;
    }

    @Override
    public boolean execute() throws SQLException {
        System.out.println("What is this animals species? Enter the appropriate id");
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
        //QUESTION: How can I write an error message telling users to only enter a number? - if exit is allowed this doesn't work??
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
            System.out.println("Animal successfully added!");
        }

        return true; //Muss returned werden, weil mein Commando ja jetzt einen return value boolean hat

    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "add".equalsIgnoreCase(userCommando);
    }
}
