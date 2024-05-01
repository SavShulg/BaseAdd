package com.example.Json.service;

import com.example.Json.model.Avatar;
import com.example.Json.repository.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class AvatarService {

    private final com.example.Json.repository.avatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final Path avatarsDir;

    public AvatarService(com.example.Json.repository.avatarRepository repository,
                         com.example.Json.repository.avatarRepository avatarRepository,
                         StudentRepository studentRepository, @Value("${avatars.dir}") Path avatarsDir) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.avatarsDir = avatarsDir;
        this.avatarRepository = repository;
    }

    public Object save(Long studetntId, @NotNull MultipartFile file) throws IOException {


        Files.createDirectories(avatarsDir);

        //student_1.jpg
        var index = file.getOriginalFilename().lastIndexOf('.');
        var extention = file.getOriginalFilename().substring(index);
        Path filePath = avatarsDir.resolve(studetntId + extention);
        try (var in = file.getInputStream()) {

            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }


        Avatar avatar = com.example.Json.repository.avatarRepository.findByStudentId(studetntId).orElse(new Avatar());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setStudent(studentRepository.getReferenceById(studetntId));
        avatar.setFilePath(filePath.toString());
        return com.example.Json.repository.avatarRepository.save(avatar.getStudent());

    }

    public Avatar getById(Long id) {
        return com.example.Json.repository.avatarRepository.findById(id).orElse(null);
    }

    public List<Avatar> getPage(int pageNumder, int pageSize) {
        return com.example.Json.repository.avatarRepository.findAll(PageRequest.of(pageNumder, pageSize)).toList();
    }

    public com.example.Json.repository.avatarRepository getAvatarRepository() {
        return com.example.Json.repository.avatarRepository;
    }
}
