package com.example.springassignmentone;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository repository;
    @GetMapping("/all")
    public ResponseEntity<List<Course>> all() {
        return  ResponseEntity.ok(
                this.repository.findAll()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Course> add(@Valid @RequestBody JsonBody body){

        if(!body.getName().trim().equalsIgnoreCase("csc")){
            throw new RuntimeException("The course name can only be CSC.");
        }
        if(
                body.getCategory().equalsIgnoreCase("FOUNDATION") ||
                        body.getCategory().equalsIgnoreCase("HONOURS") ||
                        body.getCategory().equalsIgnoreCase("UNDERGRADUATE")
        ){
            Course course = new Course();
            course.setCategory(body.getCategory().toUpperCase().trim());
            course.setFullCourseName(
                    body.getName().concat(" ").concat(
                            ""+body.getCode()
                    ).concat(
                            body.getCategory().equalsIgnoreCase("FOUNDATION") ? "F": ""
                    )
            );
            course.setCode(body.getCode());
            course.setName(body.getName().trim().toUpperCase());
            course.setTitle(body.getTitle().trim());
            if(this.repository.findByFullCourseName(course.getFullCourseName()).isPresent()){
                throw new RuntimeException("The course already exists.");
            }
            return ResponseEntity.ok(this.repository.save(course));
        }else{
            throw new RuntimeException("The Course category is invalid.");
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody JsonBody body
    ){
        if(!body.getName().trim().equalsIgnoreCase("csc")){
            throw new RuntimeException("The course name can only be CSC.");
        }
        if(
                body.getCategory().equalsIgnoreCase("FOUNDATION") ||
                        body.getCategory().equalsIgnoreCase("HONOURS") ||
                        body.getCategory().equalsIgnoreCase("UNDERGRADUATE")
        ){
            Course course = this.repository.findById(id).orElseThrow();
            course.setCategory(body.getCategory().toUpperCase().trim());
            course.setFullCourseName(
                    body.getName().concat(" ").concat(
                            ""+body.getCode()
                    ).concat(
                            body.getCategory().equalsIgnoreCase("FOUNDATION") ? "F": ""
                    )
            );
            course.setCode(body.getCode());
            course.setName(body.getName().trim().toUpperCase());
            course.setTitle(body.getTitle().trim());
            return ResponseEntity.ok(this.repository.save(course));
        }else{
            throw new RuntimeException("The Course category is invalid.");
        }

    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Course> one(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.repository.findById(id).orElseThrow());
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") Long id){
        this.repository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}


