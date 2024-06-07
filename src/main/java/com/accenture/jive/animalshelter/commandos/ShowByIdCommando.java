package com.accenture.jive.animalshelter.commandos;

import java.sql.SQLException;

public class ShowByIdCommando implements Commando {
    @Override
    public boolean execute() throws SQLException {
        

        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show-by-id".equalsIgnoreCase(userCommando);
    }
}
