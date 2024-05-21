package com.accenture.jive.animalshelter;

import java.util.ArrayList;

public class ShowCommando {
    public ArrayList<Animal> animalsInShelter;

    public ShowCommando(ArrayList<Animal> animalsInShelter) {
        this.animalsInShelter = animalsInShelter;
    }

    public void execute() {
        if (animalsInShelter.isEmpty()) {
            System.out.println("Amazing!! All animals have found a loving home ");
        } else {
            System.out.println("These are all the animals currently waiting for their forever home:");
            for (Animal animal : animalsInShelter) {
                System.out.println("Name: " + animal.name + " - Age: " + animal.age);
                animal.makeSound();
            }
            //TO DO: filter and give out two lists: one for dogs and one for cats
        }
    }
}
