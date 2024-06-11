package com.accenture.jive.animalshelter.commandos;

public class CommandoException extends Exception {

    public CommandoException(String message) {
        super(message);
    }

    public CommandoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandoException(Throwable cause) {
        super(cause);
    }
}
