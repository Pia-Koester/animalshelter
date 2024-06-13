package com.accenture.jive.animalshelter;

import java.util.Scanner;

public class UserInteraction {

    private final Scanner scanner;

    public UserInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer askForNumber(String question, String errorMessage) {
        System.out.println(question);
        String userInput = scanner.nextLine();
        Integer parsedUserInput;
        try {
            parsedUserInput = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(errorMessage);
            parsedUserInput = askForNumber(question, "ENTER A NUMBER!");
        }
        return parsedUserInput;
    }

    public String askForString(String question) {
        System.out.println(question);
        String userInput = scanner.nextLine();
        if ("exit".equalsIgnoreCase(userInput.trim())) {
            return "exit";
        } else {
            return userInput;
        }
    }

}
