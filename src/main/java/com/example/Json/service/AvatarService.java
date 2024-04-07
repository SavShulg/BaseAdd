package com.example.Json.service;

import com.example.Json.model.Avatar;
import com.example.Json.repository.AvatarRepository;
import com.example.Json.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class AvatarService {

    private final AvatarRepository AvatarRepository;
    private final StudentRepository studentRepository;
    private final Path avatarsDir;

    public AvatarService(AvatarRepository repository, StudentRepository studentRepository, @Value("${avatars.dir}") Path avatarsDir) {
        this.studentRepository = studentRepository;
        this.avatarsDir = avatarsDir;
        this.AvatarRepository = repository;
    }

    public void Avatar save(Long studetntId, MultipartFile file) throws IOException {


        Files.createDirectories(avatarsDir);

        //student_1.jpg
        var index = file.getOriginalFilename().lastIndexOf('.');
        var extention = file.getOriginalFilename().substring(index);
        Path filePath = avatarsDir.resolve(studetntId + extention);
        try (var in = file.getInputStream()) {

            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }


        Avatar avatar = avatarRepository.findByStudentId(studetntId).orElse(new Avatar());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setStudent(studentRepository.getReferenceById(studetntId));
        avatar.setFilePath(filePath.toString());
        return avatarRepository.save(avatar);

    }

    public Avatar getById(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    public com.example.Json.repository.AvatarRepository getAvatarRepository() {
        return AvatarRepository;
    }
}
