package com.example.simple_marketplace.controller;

import com.example.simple_marketplace.dto.ForeignDebtDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.service.ForeignDebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("debt/")
@RequiredArgsConstructor
public class ForeignDebtController implements SimpleCrud<ForeignDebtDto,Integer> {

    private final ForeignDebtService foreignDebtService;
    @Override
    @PostMapping("create")
    public ResponseDto<ForeignDebtDto> create(@RequestBody ForeignDebtDto dto) {
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
}
