package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
}
