package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.UserInteraction;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowByIdCommando implements Commando {

    private final AnimalService animalService;
    private final UserInteraction userInteraction;

    public ShowByIdCommando(AnimalService animalService, UserInteraction userInteraction) {
        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean execute() throws CommandoException {

        Integer animalId = userInteraction.askForNumber("Which animal do you want to see?", "You need to enter an ID - this is a number!");

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
