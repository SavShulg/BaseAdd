package com.example.Json.controller;

import com.example.Json.model.Faculty;
import com.example.Json.model.Student;
import com.example.Json.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {

        this.service = service;
    }

    @GetMapping
    public Student get(@RequestParam Long id) {
        return service.get(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
        return service.delete(id);
    }

    @PutMapping
    public Student get(@RequestBody Student student) {
        return service.update(student);
    }

    @GetMapping("/byAge")
    public Collection<Student> getByeAgeBetween(@RequestParam(required = false) Integer min,
                                                @RequestParam(required = false) Integer max) {
        if (min != null && max != null) {
            return service.getByAgeBetween(min, max);
        }

        return service.getAll();
    }

    @GetMapping("faculty")
    public Faculty getStudentFaculty(@RequestParam Long studentId) {
        return service.get(studentId).getFaculty();

    }
}
