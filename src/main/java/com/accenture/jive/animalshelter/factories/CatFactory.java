package com.accenture.jive.animalshelter.factories;

import com.accenture.jive.animalshelter.Cat;

public class CatFactory {
    public Cat createAnimal(String name, int age) {
        Cat cat = new Cat();
        cat.name = name;
        cat.age = age;
        return cat;
    }
}
