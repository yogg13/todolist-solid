// VIEW CLI
package com.example.view;

import com.example.model.Todo;
import com.example.service.TodoService;
import java.util.List;
import java.util.Scanner;

// 7. CLI Interface - Menerapkan SRP
public class TodoCLI {
   private final TodoService todoService;
   private final Scanner scanner;

   public TodoCLI(TodoService todoService) {
      this.todoService = todoService;
      this.scanner = new Scanner(System.in);
   }

   public void start() {
      boolean running = true;
      while (running) {
         displayMenu();
         int choice = getUserChoice();

         try {
            switch (choice) {
               case 1:
                  addTodo();
                  break;
               case 2:
                  listTodos();
                  break;
               case 3:
                  markTodoAsCompleted();
                  break;
               case 4:
                  updateTodo();
                  break;
               case 5:
                  deleteTodo();
                  break;
               case 6:
                  running = false;
                  System.out.println("Terima kasih telah menggunakan TodoList CLI!");
                  break;
               default:
                  System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
         } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
         }
      }
      scanner.close();
   }

   private void displayMenu() {
      System.out.println("\n===== TodoList CLI =====");
      System.out.println("1. Tambah Todo");
      System.out.println("2. Lihat Semua Todo");
      System.out.println("3. Tandai Todo Selesai");
      System.out.println("4. Update Todo");
      System.out.println("5. Hapus Todo");
      System.out.println("6. Keluar");
      System.out.print("Pilih menu: ");
   }

   private int getUserChoice() {
      try {
         return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
         return -1;
      }
   }

   private void addTodo() throws Exception {
      System.out.print("Masukkan judul todo: ");
      String title = scanner.nextLine();

      System.out.print("Masukkan deskripsi todo: ");
      String description = scanner.nextLine();

      todoService.addTodo(title, description);
      System.out.println("Todo berhasil ditambahkan!");
   }

   private void listTodos() throws Exception {
      List<Todo> todos = todoService.getAllTodos();

      if (todos.isEmpty()) {
         System.out.println("Tidak ada todo.");
         return;
      }

      System.out.println("\n===== Daftar Todo =====");
      for (Todo todo : todos) {
         System.out.println(todo);
      }
   }

   private void markTodoAsCompleted() throws Exception {
      System.out.print("Masukkan ID todo yang akan ditandai selesai: ");
      int id = Integer.parseInt(scanner.nextLine());

      todoService.markAsCompleted(id);
      System.out.println("Todo berhasil ditandai selesai!");
   }

   private void updateTodo() throws Exception {
      System.out.print("Masukkan ID todo yang akan diupdate: ");
      int id = Integer.parseInt(scanner.nextLine());

      Todo todo = todoService.getTodoById(id);
      if (todo == null) {
         System.out.println("Todo dengan ID " + id + " tidak ditemukan.");
         return;
      }

      System.out.print("Masukkan judul baru (kosongkan jika tidak ingin mengubah): ");
      String title = scanner.nextLine();
      if (!title.isEmpty()) {
         todo.setTitle(title);
      }

      System.out.print("Masukkan deskripsi baru (kosongkan jika tidak ingin mengubah): ");
      String description = scanner.nextLine();
      if (!description.isEmpty()) {
         todo.setDescription(description);
      }

      todoService.updateTodo(todo);
      System.out.println("Todo berhasil diupdate!");
   }

   private void deleteTodo() throws Exception {
      System.out.print("Masukkan ID todo yang akan dihapus: ");
      int id = Integer.parseInt(scanner.nextLine());

      todoService.deleteTodo(id);
      System.out.println("Todo berhasil dihapus!");
   }
}