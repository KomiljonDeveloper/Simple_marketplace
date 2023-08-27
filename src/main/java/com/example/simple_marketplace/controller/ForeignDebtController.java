package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ForeignDebtDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.ForeignDebtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("debt/")
@RequiredArgsConstructor
public class ForeignDebtController implements SimpleCrud<ForeignDebtDto,Integer> {

    private final ForeignDebtService foreignDebtService;
    @Override
    @PostMapping("create")
    public ResponseDto<ForeignDebtDto> create(@RequestBody @Valid ForeignDebtDto dto) {
        return this.foreignDebtService.create(dto);
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseDto<ForeignDebtDto> get(@PathVariable Integer id) {
        return this.foreignDebtService.get(id);
    }

    @Override
    @PutMapping("update/{id}")
    public ResponseDto<ForeignDebtDto> update(@RequestBody ForeignDebtDto dto,@PathVariable Integer id) {
        return this.foreignDebtService.update(dto, id);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseDto<ForeignDebtDto> delete(@PathVariable Integer id) {
        return this.foreignDebtService.delete(id);
    }

    @Override
    public ResponseDto<Page<ForeignDebtDto>> getAllByPage(Integer page, Integer size) {
        return this.foreignDebtService.getAllByPage(page, size);
    }
}
