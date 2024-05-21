package com.accenture.jive.animalshelter;

//This is a subclass of Animal - all cats are animals but not all animals are cats
public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("miau miauuuu miau");
    }
}
