package com.accenture.jive.animalshelter.commandos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCommando implements Commando {
    private final Scanner scanner;
    private final Connection connection;

    public UpdateCommando(Scanner scanner, Connection connection) {

        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public boolean execute() throws SQLException {
        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "update".equalsIgnoreCase(userCommando);
    }
}
