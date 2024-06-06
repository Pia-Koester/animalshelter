package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM animal");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String animalName = resultSet.getString("animal_name");
            Integer animalAge = resultSet.getInt("age");
            System.out.println(animalName + " is " + animalAge + " years old");
        }

//        if (animalsInShelter.isEmpty()) {
//            System.out.println("Amazing!! All animals have found a loving home ");
//        } else {
//            int numberOfAnimals = animalsInShelter.size();
//            System.out.println("Currently " + numberOfAnimals + " animals are in our shelter.");
//            showCats(animalsInShelter, "cat");
//            //QUESTION: wie checke ich ob es keinerlei dogs gibt? Dann soll der nächste Satz gar nicht erst geprinted werden.
//            System.out.println("These are all the dogs currently waiting for their furrever home : ");
//            for (Animal animal : animalsInShelter) {
//                if ((animal instanceof Dog)) {
//                    System.out.println("Dog name: " + animal.name + " - Age: " + animal.age);
//                }
//            }
//        }
        //TO DO: filter and give out two lists: one for dogs and one for cats
        return true;
    }

    private void showCats(ArrayList<Animal> animalsInShelter, String typeOfAnimal) { //Kein plan - erstmal egal
        System.out.println("These are all the cats currently waiting for their forever home:");
        //IMPORTANT: this works because of implicit upcasting. All cats and dogs are subclasses of animal, so no matter which object they are, they work in the parent methods
        //Methoden die ausschließlich in den Subclasses sind können hier allerdings nicht verwendet werden!
        for (Animal animal : animalsInShelter) {
            if (animal instanceof Cat) {
                System.out.println("Cat name: " + animal.name + " - Age: " + animal.age);
            }
        }
    }
}

