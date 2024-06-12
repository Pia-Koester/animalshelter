package com.accenture.jive.animalshelter.factories;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;

public class AnimalFactory {


    public Animal create(String retrievedAnimalType, String animalName, Integer parsedAge) {
        Animal animal;
        if ("cat".equals(retrievedAnimalType)) {
            animal = new Cat();
            animal.setName(animalName);
            animal.setAge(parsedAge);
        } else {
            animal = new Dog();
            animal.setName(animalName);
            animal.setAge(parsedAge);
        }
        return animal;
    }

    
}
