package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;
import com.accenture.jive.animalshelter.UserInteraction;
import com.accenture.jive.animalshelter.services.AnimalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommando implements Commando {
    private final AnimalService animalService;
    private final UserInteraction userInteraction;
    public ArrayList<Animal> animalsInShelter;

    public ShowCommando(AnimalService animalService, UserInteraction userInteraction) {
        this.animalService = animalService;
        this.userInteraction = userInteraction;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show".equalsIgnoreCase(userCommando);
    }

    @Override
    public boolean execute() throws CommandoException {

        try {
            List<Animal> animals = animalService.readAnimals();
            userInteraction.responseWriter("All these animals are waiting for their forever home: ", null);
            for (Animal animal : animals) {
                userInteraction.responseWriter(animal.getName() + " is " + animal.getAge() + " years old", "purple");
            }
        } catch (SQLException e) {
            throw new CommandoException("Animals cannot be shown", e);
        }

        return true;
    }


}

