package com.fullstack_springboot_next.TodoBook.dto;

import lombok.Data;

@Data
public class CreateToDoDto {
    private String name;
    private Boolean completed;
}
