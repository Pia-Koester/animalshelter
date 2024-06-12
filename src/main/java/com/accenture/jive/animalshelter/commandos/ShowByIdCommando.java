package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowByIdCommando implements Commando {

    private final Scanner scanner;
    private final AnimalService animalService;

    public ShowByIdCommando(Scanner scanner, AnimalService animalService) {
        this.scanner = scanner;
        this.animalService = animalService;
    }

    @Override
    public boolean execute() throws CommandoException {

        System.out.println("Which animal do you want to see?");
        String animalIdAsString = scanner.nextLine();
        if ("exit".equals(animalIdAsString)) {
            return false;
        }
        Integer animalId = null;
        try {
            animalId = Integer.parseInt(animalIdAsString);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid id- this must be a number");
            animalIdAsString = scanner.nextLine();
            animalId = Integer.parseInt(animalIdAsString);
        }

        try {
            Animal animal = animalService.readAnimalById(animalId);
            System.out.println("Animal found: " + animal.getName() + ", age: " + animal.getAge());
        } catch (SQLException e) {
            throw new CommandoException("Animal cannot be shown", e);
        }

        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show-by-id".equalsIgnoreCase(userCommando);
    }
}
