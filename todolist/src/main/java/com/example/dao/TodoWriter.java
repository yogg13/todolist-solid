package com.example.dao;

import com.example.model.Todo;

public interface TodoWriter {
   void create(Todo todo) throws Exception;

   void update(Todo todo) throws Exception;

   void delete(int id) throws Exception;
}
