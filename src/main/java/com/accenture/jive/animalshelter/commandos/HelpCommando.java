package com.accenture.jive.animalshelter.commandos;

public class HelpCommando implements Commando {

    //QUESTION: ich würde die Farben gerne für mehr Verständlichkeit als Konstanten wo anlegen, aber ich möchte sie überall nutzen können.
    // wie gehe ich hier vor?
    @Override
    public boolean execute() throws CommandoException {
        System.out.println("Here is how you can navigate our Animalshelter:");
        System.out.println("\u001B[36m" + "- show" + "\u001B[0m" + " will give you a list of all animals with there age");
        System.out.println("\u001B[36m" + "- show-by-id" + "\u001B[0m" + " let's you look up an animal based on the id");
        System.out.println("\u001B[36m" + "- adopt" + "\u001B[0m" + " will let you choose an animal you want to take home");
        System.out.println("\u001B[36m" + "- birthday" + "\u001B[0m" + " means you can say which animal will celebrate their birthday");
        System.out.println("\u001B[36m" + "- add" + "\u001B[0m" + " you will leave your animal in the Shelter - abandoning them :(");
        System.out.println("\u001B[36m" + "- exit" + "\u001B[0m" + " let's you leave the Animalshelter.");
        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "help".equalsIgnoreCase(userCommando) || " ".equalsIgnoreCase(userCommando);
    }
}
