package com.accenture.jive.animalshelter;

import java.util.Objects;

public abstract class Animal {
    String name;
    int age;

    //To be able to have an empty method which the child classes need to finish we use the keyword abstract
    public abstract void makeSound();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
