package com.fullstack_springboot_next.Controllers;

import com.fullstack_springboot_next.TodoBook.Services.ToDoService;
import com.fullstack_springboot_next.TodoBook.dto.ToDoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    public void getToDosShouldReturnTodos() throws Exception {
        List<ToDoDto> todos = new ArrayList<>();
        ToDoDto toDoDto = new ToDoDto(1L, "wriging unit test", false);
        todos.add(toDoDto);
        when(toDoService.getToDos()).thenReturn(todos);
        mockMvc.perform(get("/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(toDoDto.getName())));
    }

}
