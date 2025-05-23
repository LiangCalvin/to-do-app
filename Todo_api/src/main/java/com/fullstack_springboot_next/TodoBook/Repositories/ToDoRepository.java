package com.fullstack_springboot_next.TodoBook.Repositories;

import com.fullstack_springboot_next.TodoBook.Models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    List<ToDo> findByCompleted(Boolean completed);
}
