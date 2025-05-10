package com.fullstack_springboot_next.TodoBook.Exceptions;

public class ToDoException extends RuntimeException {
    private int status;

    public ToDoException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
