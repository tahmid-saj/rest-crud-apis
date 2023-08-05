package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    // define @PostConstructor to load the student data... only once

    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("S", "1"));
        theStudents.add(new Student("S", "2"));
        theStudents.add(new Student("S", "3"));
    }

    // define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {
        loadData();

        return theStudents;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // index into the list

        // check studentId against list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found " + studentId);
        }

        return theStudents.get(studentId);
    }
}
