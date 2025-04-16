package com.example.model;

//Menerapkan SRP
//Kelas ini hanya bertanggung jawab  untuk merepresentasikan data todo

public class Todo {
   private int id;
   private String title;
   private String description;
   private boolean completed;

   public Todo() {}

   public Todo(String title, String description) {
      this.title = title;
      this.description = description;
      this.completed = false;
   }

   // Getter & Setter
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isCompleted() {
      return completed;
   }

   public void setCompleted(boolean completed) {
      this.completed = completed;
   }

   @Override
   public String toString() {
      return id + "- " + title + (completed ? "[V]" : "[ ]") +
            "\n " + description;
   }
}
