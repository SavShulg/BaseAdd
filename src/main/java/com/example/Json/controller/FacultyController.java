package com.example.Json.controller;

import com.example.Json.model.Faculty;
import com.example.Json.model.Student;
import com.example.Json.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")

public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping
    public Faculty get(@RequestParam Long id) {
        return service.get(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @DeleteMapping
    public boolean delete(@RequestParam Long id) {
        return service.delete(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @GetMapping("/byColorAndName")
    public Collection<Faculty> getByeColor(@RequestParam(required = false) String color,
                                           @RequestParam(required = false) String name) {
        if (color == null && name == null) {
            return service.getAll();
        }
            return service.getByeColorAndName(color, name);

        }
    @GetMapping("students")
    public List<Student> getStudentFaculty(@RequestParam Long facultyId) {
        return service.get(facultyId).getStudents();
    }
    }

