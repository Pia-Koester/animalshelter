package com.accenture.jive.animalshelter.commandos;

import java.sql.SQLException;

public interface Commando {
    public boolean execute() throws SQLException;

    public boolean shouldExecute(String userCommando);
}
