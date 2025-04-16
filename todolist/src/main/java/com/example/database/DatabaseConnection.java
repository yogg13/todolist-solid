package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.example.config.DatabaseConfig;

//Menerapkan SRP
//Kelas ini hanya bertanggung jawab untuk mengelola database

public class DatabaseConnection {
   private final DatabaseConfig config;
   private static Connection connection = null;

   public DatabaseConnection(DatabaseConfig config) {
      this.config = config;
   }

   public Connection getConnection() throws SQLException {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(
               config.getUrl(), config.getUsername(), config.getPassword());
      } catch (ClassNotFoundException e) {
         throw new SQLException("MySQL JDBC Driver tidak ditemukan", e);
      }
      return connection;
   }

   public static void closeConnection() throws SQLException {
      if (connection != null && !connection.isClosed()) {
         connection.close();
      }
   }
}
