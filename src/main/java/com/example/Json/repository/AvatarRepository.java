package com.example.Json.repository;

import com.example.Json.model.Avatar;
import com.example.Json.model.Faculty;
import org.springframework.data.domain.ManagedTypes;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{

    Optional<Avatar> findAllByStudentId(Long studentId);

    Faculty findByStudentId(Long studetntId);

    ManagedTypes findAll(PageRequest of);
}
