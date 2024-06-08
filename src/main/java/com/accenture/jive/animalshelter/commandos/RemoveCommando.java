package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RemoveCommando implements Commando {
    private final Scanner scanner;
    private final Connection connection;
    private final AnimalService animalService;

    public RemoveCommando(Scanner scanner, Connection connection, AnimalService animalService) {

        this.scanner = scanner;
        this.connection = connection;
        this.animalService = animalService;
    }

    @Override
    public boolean execute() throws SQLException {
        System.out.println("Which of these animals do you want to adopt? ");
        List<Animal> animals = animalService.readAnimals();

        for (Animal animal : animals) {
            System.out.println(animal.getId() + " - " + animal.getName());
        }

        String selectedAnimalIdAsString = scanner.nextLine();

        if ("exit".equalsIgnoreCase(selectedAnimalIdAsString)) {
            return false;
        }
        Integer selectedAnimalId = null;
        try {
            selectedAnimalId = Integer.parseInt(selectedAnimalIdAsString);
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid ID - this must be a number");
            selectedAnimalIdAsString = scanner.nextLine();
            selectedAnimalId = Integer.parseInt(selectedAnimalIdAsString);
        }

        String sql = "DELETE FROM animalshelter.animal " +
                "WHERE animal_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, selectedAnimalId);

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("\u001B[36m" + "204: Removal from Shelter successfull" + "\u001B[0m");
        }

        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "remove".equalsIgnoreCase(userCommando);
    }
}
