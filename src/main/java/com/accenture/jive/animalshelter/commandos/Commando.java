package com.accenture.jive.animalshelter.commandos;

import java.sql.SQLException;

public interface Commando {
    public boolean execute() throws CommandoException;

    public boolean shouldExecute(String userCommando);
}
