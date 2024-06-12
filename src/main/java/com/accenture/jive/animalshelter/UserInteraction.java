package com.accenture.jive.animalshelter;

import java.util.Scanner;

public class UserInteraction {

    public Integer askForNumber(Scanner scanner, String question, String errorMessage) {
        System.out.println(question);
        String userInput = scanner.nextLine();
        Integer parsedUserInput;
        try {
            parsedUserInput = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(errorMessage);
            parsedUserInput = askForNumber(scanner, question, "you really need to enter a number here");
        }
        return parsedUserInput;
    }
}
