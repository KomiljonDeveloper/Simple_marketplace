package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.LoanerDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.LoanerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loaner/")
public record LoanerController(LoanerService loanerService) implements SimpleCrud<LoanerDto,Integer> {
    @Override
    @PostMapping("create")
    public ResponseDto<LoanerDto> create(@RequestBody LoanerDto dto) {
        return this.loanerService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<LoanerDto> get(@PathVariable Integer id) {
        return this.loanerService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<LoanerDto> update(@RequestBody LoanerDto dto,@PathVariable Integer id) {
        return this.loanerService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<LoanerDto> delete(@PathVariable Integer id) {
        return this.loanerService.delete(id);
    }
}
