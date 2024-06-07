package com.accenture.jive.animalshelter.commandos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowByIdCommando implements Commando {

    private final Scanner scanner;
    private final Connection connection;

    public ShowByIdCommando(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public boolean execute() throws SQLException {


        return true;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "show-by-id".equalsIgnoreCase(userCommando);
    }
}
