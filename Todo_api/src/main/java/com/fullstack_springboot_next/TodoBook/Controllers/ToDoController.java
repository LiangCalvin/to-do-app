package com.fullstack_springboot_next.TodoBook.Controllers;

import com.fullstack_springboot_next.TodoBook.Models.ToDo;
import com.fullstack_springboot_next.TodoBook.Services.ToDoService;
import com.fullstack_springboot_next.TodoBook.dto.CreateToDoDto;
import com.fullstack_springboot_next.TodoBook.dto.ToDoDto;
import com.fullstack_springboot_next.TodoBook.dto.UpdateToDoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody CreateToDoDto newToDo) {
        ToDoDto toDoDto = toDoService.createTodo(newToDo);
        return new ResponseEntity<>(toDoDto, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public List<ToDoDto> getTodos(@RequestParam Optional<Boolean> completed) {
        if (completed.isPresent()) {
            return toDoService.getToDos(completed.get());
        }
        return toDoService.getToDos();
    }

    @GetMapping("/getbyId/{id}")
    public ToDoDto getTodoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/updateTodo/{id}")
    public ToDoDto updateToDo(@PathVariable Long id, @RequestBody UpdateToDoDto updateToDo) {
        return toDoService.updateToDo(id, updateToDo);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
