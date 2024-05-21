package com.accenture.jive.animalshelter;

public abstract class Animal {
    String name;
    int age;

    //To be able to have an empty method which the child classes need to finish we use the keyword abstract
    public abstract void makeSound();
}
