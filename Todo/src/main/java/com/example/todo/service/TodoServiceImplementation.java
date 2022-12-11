package com.example.todo.service;


import com.example.todo.repo.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoServiceImplementation implements TodoService{

    private final ToDoRepository toDoRepository;

    @Override
    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }
}