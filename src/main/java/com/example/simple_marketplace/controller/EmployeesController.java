package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.EmployeesDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.EmployeesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee/")
@RequiredArgsConstructor
public class EmployeesController implements SimpleCrud<EmployeesDto,Integer> {
    private final EmployeesService employeesService;
    @Override
    @PostMapping("create")
    public ResponseDto<EmployeesDto> create(@RequestBody @Valid EmployeesDto dto) {
        return this.employeesService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<EmployeesDto> get(@PathVariable Integer id) {
        return this.employeesService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<EmployeesDto> update(@RequestBody EmployeesDto dto,@PathVariable Integer id) {
        return this.employeesService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<EmployeesDto> delete(@PathVariable Integer id) {
        return this.employeesService.delete(id);
    }

    @Override
    @GetMapping("/get-all-page")
    public ResponseDto<Page<EmployeesDto>> getAllByPage(Integer page, Integer size) {
        return this.employeesService.getAllByPage(page, size);
    }
}
