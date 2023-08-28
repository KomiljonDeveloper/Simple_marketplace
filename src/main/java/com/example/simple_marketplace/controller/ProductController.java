package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ProductDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product/")
@RequiredArgsConstructor
public class ProductController implements SimpleCrud<ProductDto,Integer> {
    private  final ProductService productService;
    @Override
    @PostMapping("create")
    public ResponseDto<ProductDto> create(@RequestBody @Valid ProductDto dto) {
        return this.productService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<ProductDto> get(@PathVariable Integer id) {
        return  this.productService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<ProductDto> update(@RequestBody ProductDto dto,@PathVariable Integer id) {
        return this.productService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<ProductDto> delete(@PathVariable Integer id) {
        return this.productService.delete(id);
    }

    @Override
    @GetMapping("/get-all-page")
    public ResponseDto<Page<ProductDto>> getAllByPage(@RequestParam Integer page,@RequestParam Integer size) {
        return this.productService.getAllByPage(page,size);
    }
    @GetMapping("/search-by-basic")
    public ResponseDto<Page<ProductDto>> searchByBasic(Map<String, String> params) {
        return this.productService.searchByBasic(params);
    }
    @GetMapping("/search-by-advanced")
    public ResponseDto<Page<ProductDto>> searchByAdvanced(@RequestParam Map<String, String> params) {
        return this.productService.searchByAdvanced(params);
    }
}
