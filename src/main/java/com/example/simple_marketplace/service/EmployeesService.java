package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.EmployeesDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.repository.EmployeesRepository;
import com.example.simple_marketplace.service.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeesService implements SimpleCrud<EmployeesDto, Integer> {
    private final EmployeesRepository employeesRepository;
    private final EmployeesMapper employeesMapper;

    @Override
    public ResponseDto<EmployeesDto> create(EmployeesDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            this.employeesRepository.save(this.employeesMapper.toEntity(dto));
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("OK")
                    .data(dto)
                    .build();

        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeesDto> get(Integer id) {
        try {
            return this.employeesRepository.findByIdAndDeletedAtIsNull(id).map(employees ->
                    ResponseDto.<EmployeesDto>builder()
                            .data(this.employeesMapper.toDto(employees))
                            .success(true)
                            .message("OK")
                            .build()
            ).orElse(
                    ResponseDto.<EmployeesDto>builder()
                            .code(-1)
                            .message("Employees is not found!")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeesDto> update(EmployeesDto dto, Integer id) {
        try {
            return this.employeesRepository.findByIdAndDeletedAtIsNull(id).map(employees -> {
                dto.setUpdatedAt(LocalDateTime.now());
                this.employeesMapper.updateToDtoFromEntity(dto, employees);
                this.employeesRepository.save(employees);
                return ResponseDto.<EmployeesDto>builder()
                        .data(dto)
                        .success(true)
                        .message("OK")
                        .build();
            }).orElse(
                    ResponseDto.<EmployeesDto>builder()
                            .code(-1)
                            .message("Employees is not found!")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }

    @Override
    public ResponseDto<EmployeesDto> delete(Integer id) {
        try {
            return this.employeesRepository.findByIdAndDeletedAtIsNull(id).map(employees -> {
                employees.setDeletedAt(LocalDateTime.now());
                this.employeesRepository.save(employees);
                return
                        ResponseDto.<EmployeesDto>builder()
                                .data(this.employeesMapper.toDto(employees))
                                .success(true)
                                .message("OK")
                                .build();

            }).orElse(
                    ResponseDto.<EmployeesDto>builder()
                            .code(-1)
                            .message("Employees is not found!")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message(String.format("While database deleting error : %s", e.getMessage()))
                    .code(-1)
                    .build();
        }
    }
}
