package com.example.springassignmentone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}

