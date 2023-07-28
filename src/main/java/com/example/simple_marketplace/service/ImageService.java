package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.FileCrud;
import com.example.simple_marketplace.dto.ImageDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.modul.Image;
import com.example.simple_marketplace.repository.ImageRepository;
import com.example.simple_marketplace.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService implements FileCrud<ImageDto, MultipartFile, Integer> {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    @Override
    public ResponseDto<ImageDto> upload(MultipartFile image) {
        try {

            Image image1 = new Image();
            String[] split = Objects.requireNonNull(image.getOriginalFilename()).split("\\.");
            image1.setType(split[1]);
            image1.setName(split[0]);
            image1.setCreatedAt(LocalDateTime.now());
            image1.setPath(saveFile(image));
            image1.setSize((double) image.getSize());
            this.imageRepository.save(image1);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.imageMapper.toDto(image1))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("While information database saving error : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<ImageDto> download(Integer id) {
        try {
        return this.imageRepository.findByIdAndDeletedAtIsNull(id).map(image ->{
            ImageDto dto = this.imageMapper.toDto(image);
            try {
                dto.setData(Files.readAllBytes(Path.of(image.getPath())));
            } catch (IOException e) {
                return ResponseDto.<ImageDto>builder()
                        .code(-3)
                        .message("Image download error : "+e.getMessage())
                        .build();
            }
            return ResponseDto.<ImageDto>builder()
                        .message("OK")
                        .success(true)
                        .data(dto)
                        .build();}).orElse(ResponseDto.<ImageDto>builder()
                .code(-1)
                .message("Image is not found!")
                .build());
    }catch (Exception e){
        return  ResponseDto.<ImageDto>builder()
                .code(-3)
                .message("While database getting error!")
                .build();

        }
    }

    @Override
    public ResponseDto<ImageDto> update(MultipartFile image, Integer id) {
        try {
            ImageDto image2 = new ImageDto();
            String[] split = Objects.requireNonNull(image.getOriginalFilename()).split("\\.");
            image2.setType(split[1]);
            image2.setName(split[0]);
            image2.setUpdatedAt(LocalDateTime.now());
            image2.setPath(saveFile(image));
            image2.setSize((double) image.getSize());
            return this.imageRepository.findByIdAndDeletedAtIsNull(id).map(image1 ->{
                this.imageMapper.updateToDtoFromEntity(image1,image2);
                this.imageRepository.save(image1);
                   return ResponseDto.<ImageDto>builder()
                            .message("OK")
                            .success(true)
                            .data(this.imageMapper.toDto(image1))
                            .build();
        }).orElse(ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build());
        }catch (Exception e){
            return  ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("While database update error!")
                    .build();

        }
    }

    @Override
    public ResponseDto<ImageDto> delete(Integer id) {
        try {
            return this.imageRepository.findByIdAndDeletedAtIsNull(id).map(image ->{
                image.setDeletedAt(LocalDateTime.now());
                imageRepository.save(image);
                    return ResponseDto.<ImageDto>builder()
                            .message("OK")
                            .success(true)
                            .data(this.imageMapper.toDto(image))
                            .build();}).orElse(ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build());
        }catch (Exception e){
            return  ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("While database deleting error!")
                    .build();

        }

    }


    public String saveFile(MultipartFile file) {

        try {
            String folder = String.format("%s/%s", "upload", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            File sfile = new File(folder);
            if (!sfile.exists()) {
                sfile.mkdirs();
            }
            String fileName = String.format("%s/%s", folder, UUID.randomUUID());

            Files.copy(file.getInputStream(), Path.of(fileName));
            return fileName;
        } catch (Exception e) {
            return "While file saving error :" + e.getMessage();
        }


    }
}
