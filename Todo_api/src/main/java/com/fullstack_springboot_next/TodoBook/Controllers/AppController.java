package com.fullstack_springboot_next.TodoBook.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

   Logger logger = LoggerFactory.getLogger(AppController.class);

   @Value("${spring.application.name}")
    private String name;

   @GetMapping("/")
    public String getRoot() {
       logger.info(name);
       return "Hello";
   }
}
