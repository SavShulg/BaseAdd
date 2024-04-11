package com.example.Json.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Faculty {

    private Object GenerationType;
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;


    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Faculty(long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Faculty() {

    }

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    public Faculty orElseThrow() {
    }

    public Faculty orElseThrow(Object aNew) {


    }

    public Optional<Boolean> map(Object o) {
        return null;
    }
}