package com.fullstack_springboot_next.TodoBook.Services;

import com.fullstack_springboot_next.TodoBook.Exceptions.ToDoException;
import com.fullstack_springboot_next.TodoBook.Models.ToDo;
import com.fullstack_springboot_next.TodoBook.Repositories.ToDoRepository;
import com.fullstack_springboot_next.TodoBook.dto.CreateToDoDto;
import com.fullstack_springboot_next.TodoBook.dto.ToDoDto;
import com.fullstack_springboot_next.TodoBook.dto.UpdateToDoDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDoDto createTodo(CreateToDoDto createToDoDto) {
        ToDo newToDo = new ToDo();
        newToDo.setName(createToDoDto.getName());
        newToDo.setCompleted(createToDoDto.getCompleted());
        ToDo toDo = toDoRepository.save(newToDo);
        return new ToDoDto(toDo);
    }

    public List<ToDoDto> getToDos() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
    }

    public List<ToDoDto> getToDos(Boolean completed) {
        List<ToDo> toDos = toDoRepository.findByCompleted(completed);
        return toDos.stream().map(entity -> new ToDoDto(entity)).toList();
    }

    public ToDoDto getToDoById(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            return new ToDoDto(toDo.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "getToDoById - to do not found");
        }
    }

    public ToDoDto updateToDo(Long id, UpdateToDoDto updateToDoDto) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDo.get().setName(updateToDoDto.getName());
            toDo.get().setCompleted(updateToDoDto.getCompleted());
            toDoRepository.save(toDo.get());
            return new ToDoDto(toDo.get());
        } else {
            throw new ToDoException(404, "updateToDo - to do not found");
        }
    }

    public void deleteToDo(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (toDo.isPresent()) {
            toDoRepository.delete(toDo.get());
        } else {
            throw new RuntimeException("deleteToDo - to do not found");
        }
    }

}
