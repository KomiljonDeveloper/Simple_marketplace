package com.example.simple_marketplace.controller;



import com.example.simple_marketplace.dto.FileCrud;
import com.example.simple_marketplace.dto.ImageDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public record ImageController(ImageService imageService) implements FileCrud<ImageDto, MultipartFile,Integer> {

    @Override
    @PostMapping("/upload")
    public ResponseDto<ImageDto> upload(@RequestBody MultipartFile image) {
        return this.imageService.upload(image);
    }

    @Override
    @GetMapping("download/{id}")
    public ResponseDto<ImageDto> download(@PathVariable Integer id) {
        return this.imageService.download(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<ImageDto> update(@RequestBody MultipartFile image,@PathVariable Integer id) {
        return this.imageService.update(image,id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<ImageDto> delete(@PathVariable Integer id) {
        return this.imageService.delete(id);
    }
}
