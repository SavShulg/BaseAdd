package com.example.Json.repository;

import com.example.Json.model.Faculty;
import com.example.Json.model.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {


    Collection<Faculty> findAllByColorIgnorCaseOrAndNameIgnoreCase(String color, String name);

    Faculty save(Faculty faculty);

    Faculty findById(Long id);


    Collection<Faculty> findAll();
}