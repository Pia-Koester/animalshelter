package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UpdateCommando implements Commando {
    private final Scanner scanner;
    private final Connection connection;
    private final AnimalService animalService;

    public UpdateCommando(Scanner scanner, Connection connection, AnimalService animalService) {

        this.scanner = scanner;
        this.connection = connection;
        this.animalService = animalService;
    }

    @Override
    public boolean execute() throws SQLException {
        System.out.println("The Animals are celebrating a birthday - Select which cat got one year older");
        List<Animal> animals = animalService.readAnimals();

        for (Animal animal : animals) {
            System.out.println(animal.getId() + " - Name: " + animal.getName() + " age: " + animal.getAge());
        }

        String animalIdString = scanner.nextLine();
        Integer animalId = Integer.parseInt(animalIdString);

        String sql = "UPDATE animal " +
                "SET age = age + 1 " +
                "WHERE animal_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animalId);

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("\u001B[36m" + "200: Update successfull - Happy Birthday!" + "\u001B[0m");
        }

        List<Animal> updatedAnimals = animalService.readAnimals();

        for (Animal updatedAnimal : updatedAnimals) {
            System.out.println(updatedAnimal.getId() + " - Name: " + updatedAnimal.getName() + " age: " + updatedAnimal.getAge());

        }


        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "birthday".equalsIgnoreCase(userCommando);
    }
}
