package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.LoanerDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.repository.LoanerRepository;
import com.example.simple_marketplace.service.mapper.LoanerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanerService implements SimpleCrud<LoanerDto, Integer> {
    private final LoanerRepository loanerRepository;
    private final LoanerMapper loanerMapper;

    @Override
    public ResponseDto<LoanerDto> create(LoanerDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            this.loanerRepository.save(this.loanerMapper.toEntity(dto));
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("OK")
                    .data(dto)
                    .build();


        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<LoanerDto> get(Integer id) {
        try {
            return this.loanerRepository.findByIdAndDeletedAtIsNull(id).map(loaner ->
                ResponseDto.<LoanerDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.loanerMapper.toDto(loaner))
                        .build()
            ).orElse(ResponseDto.<LoanerDto>builder()
                    .code(-1)
                    .message("Loaner is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<LoanerDto> update(LoanerDto dto, Integer id) {
        try {
            return this.loanerRepository.findByIdAndDeletedAtIsNull(id).map(loaner ->{
                loaner.setUpdatedAt(LocalDateTime.now());
                this.loanerRepository.save(loaner);
                return ResponseDto.<LoanerDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.loanerMapper.toDto(loaner))
                        .build();
                    }
            ).orElse(ResponseDto.<LoanerDto>builder()
                    .code(-1)
                    .message("Loaner is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<LoanerDto> delete(Integer id) {
        try {
            return this.loanerRepository.findByIdAndDeletedAtIsNull(id).map(loaner ->{
                loaner.setDeletedAt(LocalDateTime.now());
                this.loanerRepository.save(loaner);
                return ResponseDto.<LoanerDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.loanerMapper.toDto(loaner))
                        .build();
                    }
            ).orElse(ResponseDto.<LoanerDto>builder()
                    .code(-1)
                    .message("Loaner is not found!")
                    .build());
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("While database deleting error : %s", e.getMessage()))
                    .build();
        }
    }
}
