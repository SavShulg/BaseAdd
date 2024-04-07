package com.example.Json.repository;

import com.example.Json.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{

    Optional<Avatar> findAllByStudentId(Long studentId);
}
