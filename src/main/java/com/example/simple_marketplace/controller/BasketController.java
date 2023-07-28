package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.BasketDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.BasketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("basket")
public record BasketController(BasketService basketService) implements SimpleCrud<BasketDto,Integer> {

    @Override
    @PostMapping("/create")
    public ResponseDto<BasketDto> create(@RequestBody BasketDto dto) {
        return this.basketService.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseDto<BasketDto> get(@PathVariable Integer id) {
        return this.basketService.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseDto<BasketDto> update(@RequestBody BasketDto dto,@PathVariable Integer id) {
        return this.basketService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseDto<BasketDto> delete(@PathVariable Integer id) {
        return this.basketService.delete(id);
    }
}


