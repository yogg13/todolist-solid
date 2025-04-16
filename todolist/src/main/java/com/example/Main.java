//MAIN
package com.example;

import java.sql.SQLException;

import com.example.database.DatabaseConnection;
import com.example.factory.TodoFactory;
import com.example.view.TodoCLI;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            TodoFactory factory = new TodoFactory();
            TodoCLI cli = factory.createTodoCLI();

            cli.start();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
