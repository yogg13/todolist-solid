package com.example.dao;

//Menerapkan OCP Prinsiple
//Interface ini memungkinkan untuk ekstensi tanpa modifikasi
public interface TodoDao extends TodoReader, TodoWriter {
   
}
