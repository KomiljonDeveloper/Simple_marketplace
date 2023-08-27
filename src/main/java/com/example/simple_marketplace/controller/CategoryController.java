package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category/")
@RequiredArgsConstructor
public class CategoryController implements SimpleCrud<CategoryDto,Integer> {
    private final CategoryService categoryService;
    @Override
    @PostMapping("create")
    public ResponseDto<CategoryDto> create(@RequestBody @Valid CategoryDto dto) {
        return this.categoryService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<CategoryDto> get(@PathVariable Integer id) {
        return this.categoryService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto dto,@PathVariable Integer id) {
        return this.categoryService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<CategoryDto> delete(@PathVariable Integer id) {
        return this.categoryService.delete(id);
    }

    @Override
    @GetMapping("get-all-page")
    public ResponseDto<Page<CategoryDto>> getAllByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return this.categoryService.getAllByPage(page, size);
    }
}
