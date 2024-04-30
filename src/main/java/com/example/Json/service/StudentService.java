package com.example.Json.service;

import com.example.Json.exceptions.RecordNotFoundException;
import com.example.Json.model.Faculty;
import com.example.Json.model.Student;
import com.example.Json.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository repository;
    private Object studentRepository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student student) {
        return repository.save(student);
    }
    public Student get(Long id) {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public boolean delete(Long id) {
        repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    public Student update(Student student) {
        return repository.findById(student.getId())
                .map(entity -> repository.save(student))
                .orElse(null);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        return repository.findAllByAgeBetween(min, max);
    }

    public Collection<Student> getAll() {
        return repository.findAll();

    }

    public int getStudentCount() {
        return studentRepository.countStudents();
    }
    public double getAvgAge() {
        return studentRepository.avgAge();
    }

    public Collection<Student> getLastFive() {
        var students = studentRepository.getLastFive();
    }
}