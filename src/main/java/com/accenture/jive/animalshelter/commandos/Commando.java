package com.accenture.jive.animalshelter.commandos;

public interface Commando {
    public boolean execute();

    public boolean shouldExecute(String userCommando);
}