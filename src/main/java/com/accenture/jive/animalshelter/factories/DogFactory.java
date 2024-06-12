package com.accenture.jive.animalshelter.factories;

import com.accenture.jive.animalshelter.Dog;

public class DogFactory {

    public Dog createAnimal(String name, int age, int id) {
        Dog dog = new Dog();
        dog.name = name;
        dog.age = age;
        return dog;
    }

}
