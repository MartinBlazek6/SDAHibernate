package com.example.todo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String title;
    private  boolean urgent;
    private  boolean done;



    public Todo(String title, boolean urgent) {
        this.title = title;
        this.urgent = urgent;
    }

}