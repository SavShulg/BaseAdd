package com.example.Json.service;

import com.example.Json.exceptions.RecordNotFoundException;
import com.example.Json.model.Faculty;
import com.example.Json.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }
    public Faculty get(Long id) {
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

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(entity -> repository.save(faculty))
                .orElse(null);

    }

    public Collection<Faculty> getByeColorOrName(String color, String name) {

        return repository.findAllByColorIgnorCaseOrAndNameIgnoreCase(color, name);
    }

    public Collection<Faculty> getAll() {
        return repository.findAll();
    }

    public Collection<Faculty> getByeColorAndName(String color, String name) {

    }
}