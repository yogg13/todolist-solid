package com.example.dao;
import java.util.List;
import com.example.model.Todo;

public interface TodoReader {
   Todo findById(int id) throws Exception;

   List<Todo> findAll() throws Exception;
}
