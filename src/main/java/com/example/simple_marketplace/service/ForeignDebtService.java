package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.ForeignDebtDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.repository.ForeignDebtRepository;
import com.example.simple_marketplace.service.mapper.ForeignDebtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ForeignDebtService implements SimpleCrud<ForeignDebtDto, Integer> {
    private final ForeignDebtRepository foreignDebtRepository;
    private final ForeignDebtMapper foreignDebtMapper;

    @Override
    public ResponseDto<ForeignDebtDto> create(ForeignDebtDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            this.foreignDebtRepository.save(this.foreignDebtMapper.toEntity(dto));
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message("OK")
                    .data(dto)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    @Override
    public ResponseDto<ForeignDebtDto> get(Integer id) {
        try {
            return this.foreignDebtRepository.findByIdAndDeletedAtIsNull(id).map(foreignDebt ->
                    ResponseDto.<ForeignDebtDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.foreignDebtMapper.toDto(foreignDebt))
                            .build()
            ).orElse(ResponseDto.<ForeignDebtDto>builder()
                    .code(-1)
                    .message("Debt is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    @Override
    public ResponseDto<ForeignDebtDto> update(ForeignDebtDto dto, Integer id) {
        try {
            return this.foreignDebtRepository.findByIdAndDeletedAtIsNull(id).map(foreignDebt -> {
                foreignDebt.setUpdatedAt(LocalDateTime.now());
                this.foreignDebtMapper.updateToDtoFromEntity(dto, foreignDebt);
                this.foreignDebtRepository.save(foreignDebt);
                return ResponseDto.<ForeignDebtDto>builder()
                        .success(true)
                        .message("OK")
                        .data(dto)
                        .build();
            }).orElse(ResponseDto.<ForeignDebtDto>builder()
                    .code(-1)
                    .message("Debt is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    @Override
    public ResponseDto<ForeignDebtDto> delete(Integer id) {
        try {
            return this.foreignDebtRepository.findByIdAndDeletedAtIsNull(id).map(foreignDebt -> {
                foreignDebt.setDeletedAt(LocalDateTime.now());
                this.foreignDebtRepository.save(foreignDebt);
                return ResponseDto.<ForeignDebtDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.foreignDebtMapper.toDto(foreignDebt))
                        .build();

            }).orElse(ResponseDto.<ForeignDebtDto>builder()
                    .code(-1)
                    .message("Debt is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("While database deleting error : %s", e.getMessage()))
                    .code(-3)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ForeignDebtDto>> getAllByPage(Integer page, Integer size) {
        Page<ForeignDebtDto> map = this.foreignDebtRepository.findAllByPage(PageRequest.of(page, size)).map(this.foreignDebtMapper::toDto);
        if (map.isEmpty()) {
            return  ResponseDto.<Page<ForeignDebtDto>>builder()
                    .code(-1)
                    .message("Debts are not found!")
                    .build();
        }
        return ResponseDto.<Page<ForeignDebtDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();
    }
}
