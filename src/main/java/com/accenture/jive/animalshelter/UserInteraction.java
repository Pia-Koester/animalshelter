package com.accenture.jive.animalshelter;

import java.util.Scanner;

public class UserInteraction {

    private final Scanner scanner;
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";


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

    public void responseWriter(String message, String color) {
        String responseColor = switch (color) {
            case "red" -> RED;
            case "green" -> GREEN;
            case "purple" -> PURPLE;
            case "blue" -> CYAN;
            case null, default -> RESET_COLOR;
        };
        System.out.println(responseColor + message + RESET_COLOR);
    }


}
