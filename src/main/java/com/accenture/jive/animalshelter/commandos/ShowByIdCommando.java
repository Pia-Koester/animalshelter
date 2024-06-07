package com.accenture.jive.animalshelter.commandos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowByIdCommando implements Commando {

    private final Scanner scanner;
    private final Connection connection;

    public ShowByIdCommando(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public boolean execute() throws SQLException {

        System.out.println("Which animal do you want to see?");
        String animalIdAsString = scanner.nextLine();
        if ("exit".equals(animalIdAsString)) {
            return false;
        }
        Integer animalId = Integer.parseInt(animalIdAsString);

        //QUESTION: diese Umbrüche sind drin, wenn ich es aus mySQL workbench kopiere, ich will auch nicht dass es alles in einer Reihe steht,
        //finde es so aber hässlich - was tun?
        String sql = "SELECT animal_id, animal_name, age, type_name FROM animal \n" +
                " JOIN type ON animal.type_id= type.type_id " +
                "WHERE animal_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animalId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println("404: No animal found!");
            return true;
        }

        //TODO: we need to know the animal subclass to create an object here
        String animalName = resultSet.getString("animal_name");
        Integer animalAge = resultSet.getInt("age");
        String animalType = resultSet.getString("type_name");
        //QUESTION: speichern wir in datenbanken wenn möglich alles lowercase? Gibt es einen leichteren weg das großzuschreiben?
        String animalTypeCapitalized = animalType.substring(0, 1).toUpperCase() + animalType.substring(1);
        System.out.println(animalTypeCapitalized + " found: " + animalName + ", age: " + animalAge);

        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show-by-id".equalsIgnoreCase(userCommando);
    }
}