package com.accenture.jive.animalshelter.services;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;
import com.accenture.jive.animalshelter.factories.AnimalFactory;

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

    public Animal readAnimalById(Integer animalId) throws SQLException {
        //QUESTION: wo öffne ich diese Factory? im AnimalShelter?
        AnimalFactory animalFactory = new AnimalFactory();
        String sql =
                "SELECT animal_id, animal_name, age, type_name " +
                        "FROM animal " +
                        "JOIN type ON animal.type_id = type.type_id " +
                        "WHERE animal_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animalId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println("404: No animal found!");

        }

        String animalName = resultSet.getString("animal_name");
        Integer animalAge = resultSet.getInt("age");
        String animalType = resultSet.getString("type_name");

        return animalFactory.create(animalType, animalName, animalAge);

    }

    public int addAnimal(Animal animal, Integer animalSpeciesId) throws SQLException {


        String insertSQL = "INSERT INTO animalshelter.animal (animal_name, age, type_id) " +
                "VALUES(?, ?, ?);";

        PreparedStatement preparedStatement1 = connection.prepareStatement(insertSQL);
        preparedStatement1.setString(1, animal.getName());
        preparedStatement1.setInt(2, animal.getAge());
        preparedStatement1.setInt(3, animalSpeciesId);

        return preparedStatement1.executeUpdate();
    }

    public int updateAnimal(Integer animalId) throws SQLException {
        String sql = "UPDATE animal " +
                "SET age = age + 1 " +
                "WHERE animal_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, animalId);

        //QUESTION: passt das so? - ich wil eine erfolgsmeldung schicken
        int i = preparedStatement.executeUpdate();
        return i;
    }

    public int removeAnimal(Integer selectedAnimalId) throws SQLException {
        String sql = "DELETE FROM animalshelter.animal " +
                "WHERE animal_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, selectedAnimalId);

        return preparedStatement.executeUpdate();
    }
}
