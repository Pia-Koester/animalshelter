package com.accenture.jive.animalshelter.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTypeService {

    private final Connection connection;

    public AnimalTypeService(Connection connection) {
        this.connection = connection;
    }

    //QUESTION: packe ich das einen weiteren Service namens AnimalTypeService?
    public void readAnimalTypes() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM type;");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer typeId = resultSet.getInt("type_id");
            String typeName = resultSet.getString("type_name");
            System.out.println("- " + typeId + "--> " + typeName);
        }
    }
}
