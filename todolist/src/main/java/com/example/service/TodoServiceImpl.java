package com.example.service;

import com.example.model.Todo;
import com.example.dao.TodoDao;
import java.util.List;

//Menerapkan SRP & LSP

public class TodoServiceImpl implements TodoService {
   private final TodoDao todoDao;

   public TodoServiceImpl(TodoDao todoDao) {
      this.todoDao = todoDao;
   }

   @Override
   public void addTodo(String title, String description) throws Exception {
      Todo todo = new Todo(title, description);
      todoDao.create(todo);
   }

   @Override
   public void updateTodo(Todo todo) throws Exception {
      todoDao.update(todo);
   }

   @Override
   public void deleteTodo(int id) throws Exception {
      todoDao.delete(id);
   }

   @Override
   public Todo getTodoById(int id) throws Exception {
      return todoDao.findById(id);
   }

   @Override
   public List<Todo> getAllTodos() throws Exception {
      return todoDao.findAll();
   }

   @Override
   public void markAsCompleted(int id) throws Exception {
      Todo todo = todoDao.findById(id);

      if (todo != null) {
         todo.setCompleted(true);
         todoDao.update(todo);
      } else {
         throw new Exception("Todo dengan ID " + id + "tidak ditemukan");
      }
   }
}
