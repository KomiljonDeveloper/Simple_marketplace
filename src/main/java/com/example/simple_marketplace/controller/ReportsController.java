package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.ReportsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("reports/")
@RestController
@RequiredArgsConstructor
public class ReportsController implements SimpleCrud<ReportsDto,Integer> {

    private final ReportsService reportsService;
    @Override
    @PostMapping("create")
    public ResponseDto<ReportsDto> create(@RequestBody @Valid ReportsDto dto) {
        return this.reportsService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<ReportsDto> get(@PathVariable Integer id) {
        return this.reportsService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<ReportsDto> update(@RequestBody ReportsDto dto, @PathVariable Integer id) {
        return this.reportsService.update(dto,id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<ReportsDto> delete(@PathVariable Integer id) {
        return this.reportsService.delete(id);
    }
}
