package com.example.config;

public class MySqlConfig implements DatabaseConfig {
   @Override
   public String getUrl() {
      return "jdbc:mysql://localhost:3306/todolist-sol";
   }

   @Override
   public String getUsername() {
      return "root";
   }

   @Override
   public String getPassword() {
      return "";
   }

   @Override
   public String getDriverClassName() {
      return "com.mysql.cj.jdbc.Driver";
   }
}
