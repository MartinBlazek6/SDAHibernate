package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repo.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

    private final ToDoRepository toDoRepository;

    public TodoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    //@ResponseBody
    @GetMapping({"/todo", "/"})
    public String list(@RequestParam(required = false) String isActive, Model model) {

        model.addAttribute("isActive", isActive);
        model.addAttribute("todos", toDoRepository.findAll());
        return "todolist";

    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id) {
        ModelAndView editView = new ModelAndView("update");
        Todo todo = toDoRepository.findById(id).get();
        editView.addObject("todo", todo);

        return editView;

    }


    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable(name = "id") Long id) {
        toDoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/todo/add")
    public String getTodo() {
        return "todo_app";
    }

    @GetMapping("/update")
    public String getUpdate() {
        return "update";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@RequestParam String title, boolean done, boolean urgent, @PathVariable Long id) {
        toDoRepository.findById(id).get().setDone(done);
        toDoRepository.findById(id).get().setTitle(title);
        toDoRepository.findById(id).get().setUrgent(urgent);
        toDoRepository.save(toDoRepository.findById(id).get());
        return "redirect:/todo";
    }

    @PostMapping("/todo/add")
    public String addTodo(@RequestParam(name = "todo") String todo) {
        toDoRepository.save(new Todo(todo, false));
        return "redirect:/todo";
    }
}
