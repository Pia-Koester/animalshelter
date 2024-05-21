package com.accenture.jive.animalshelter;

public class DogFactory {

    public Dog createAnimal(String name, int age) {
        Dog dog = new Dog();
        dog.name = name;
        dog.age = age;
        return dog;
    }

}
