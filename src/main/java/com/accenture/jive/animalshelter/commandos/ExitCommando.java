package com.accenture.jive.animalshelter.commandos;

public class ExitCommando implements Commando {

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "exit".equalsIgnoreCase(userCommando);
    }
}
