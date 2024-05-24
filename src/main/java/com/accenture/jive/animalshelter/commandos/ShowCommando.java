package com.accenture.jive.animalshelter.commandos;

import com.accenture.jive.animalshelter.Animal;
import com.accenture.jive.animalshelter.Cat;
import com.accenture.jive.animalshelter.Dog;

import java.util.ArrayList;

public class ShowCommando implements Commando {
    public ArrayList<Animal> animalsInShelter;

    public ShowCommando(ArrayList<Animal> animalsInShelter) {
        this.animalsInShelter = animalsInShelter;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show".equalsIgnoreCase(userCommando);
    }

    @Override
    public boolean execute() {
        if (animalsInShelter.isEmpty()) {
            System.out.println("Amazing!! All animals have found a loving home ");
        } else {
            int numberOfAnimals = animalsInShelter.size();
            System.out.println("Currently " + numberOfAnimals + " animals are in our shelter.");
            showCats(animalsInShelter, "cat");
            //QUESTION: wie checke ich ob es keinerlei dogs gibt? Dann soll der nächste Satz gar nicht erst geprinted werden.
            System.out.println("These are all the dogs currently waiting for their furrever home : ");
            for (Animal animal : animalsInShelter) {
                if ((animal instanceof Dog)) {
                    System.out.println("Dog name: " + animal.name + " - Age: " + animal.age);
                }
            }
        }
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

