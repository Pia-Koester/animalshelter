package com.accenture.jive.animalshelter.services;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalService {


    private final Connection connection;

    public AnimalService(Connection connection) {

        this.connection = connection;
    }

    public List<Animal> readAnimals() throws SQLException {
        String sql = "SELECT animal_id, animal_name, age, type_name FROM animal " +
                " JOIN type ON animal.type_id= type.type_id;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Animal> animals = new ArrayList<>();

        while (resultSet.next()) {
            String animalType = resultSet.getString("type_name");
            Animal createdAnimal;

            if ("cat".equals(animalType)) {
                createdAnimal = new Cat();
            } else {
                createdAnimal = new Dog();
            }

            animals.add(createdAnimal);

            String animalName = resultSet.getString("animal_name");
            Integer animalAge = resultSet.getInt("age");
            Integer animalID = resultSet.getInt("animal_id");
            createdAnimal.setName(animalName);
            createdAnimal.setAge(animalAge);
            createdAnimal.setId(animalID);
        }
        return animals;
    }

}
