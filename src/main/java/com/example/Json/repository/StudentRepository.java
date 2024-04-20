package com.example.Json.repository;

import com.example.Json.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAll();

    Student getReferenceById(Long studetntId);

}


