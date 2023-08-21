package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.metadata.MethodType;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequestMapping("users")
@RestController
public record UserController(UserService userService) implements SimpleCrud<UserDto,Integer> {
    @Override
    @PostMapping("/create")
    public ResponseDto<UserDto> create(@RequestBody @Valid UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseDto<UserDto> get(@PathVariable Integer id) {
        return this.userService.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseDto<UserDto> update(@RequestBody UserDto dto, @PathVariable Integer id) {
        return this.userService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseDto<UserDto> delete(@PathVariable Integer id) {
        return this.userService.delete(id);
    }
    @GetMapping(value = "/get-all")
    public ResponseDto<List<UserDto>> getAll(){
        return this.userService.getAll();
    }
    @GetMapping("/get-all-page")
    public ResponseDto<Page<UserDto>> getAllByPage(@RequestParam Integer page,@RequestParam Integer size){
       return this.userService.getAllByPage(page,size);
    }



}
