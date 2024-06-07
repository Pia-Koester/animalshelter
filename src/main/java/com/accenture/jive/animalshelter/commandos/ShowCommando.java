package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommando implements Commando {
    private final Connection connection;
    public ArrayList<Animal> animalsInShelter;

    public ShowCommando(Connection connection) {
        this.connection = connection;

    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show".equalsIgnoreCase(userCommando);
    }

    @Override
    public boolean execute() throws SQLException {

        List<Animal> animals = readAnimals();
        System.out.println("All these animals are waiting for their forever home: ");
        for (Animal animal : animals) {
            System.out.println(animal.getName() + " is " + animal.getAge() + " years old");
        }

        //TODO: filter and give out two lists: one for dogs and one for cats
        return true;
    }

    private List<Animal> readAnimals() throws SQLException {
        String sql = "SELECT animal_id, animal_name, age, type_name FROM animal \n" +
                " JOIN type ON animal.type_id= type.type_id;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Animal> animals = new ArrayList<>();

        while (resultSet.next()) {
            String animalType = resultSet.getString("type_name");
            Animal createdAnimal;

            //QUESTION: ich will hier eine Lösung haben für Tiere die keinen der beiden Types haben, wie gehe ich vor?
            if ("cat".equals(animalType)) {
                createdAnimal = new Cat();
            } else {
                createdAnimal = new Dog();
            }

            animals.add(createdAnimal);

            String animalName = resultSet.getString("animal_name");
            Integer animalAge = resultSet.getInt("age");
            createdAnimal.setName(animalName);
            createdAnimal.setAge(animalAge);
        }
        return animals;
    }

}

