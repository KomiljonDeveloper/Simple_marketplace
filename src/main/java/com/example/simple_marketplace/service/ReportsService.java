package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.repository.ReportsRepository;
import com.example.simple_marketplace.service.mapper.ReportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportsService implements SimpleCrud<ReportsDto,Integer> {
    private final ReportsRepository reportsRepository;
    private final ReportsMapper reportsMapper;
    @Override
    public ResponseDto<ReportsDto> create(ReportsDto dto) {
        try {
        dto.setCreatedAt(LocalDateTime.now());
        this.reportsRepository.save(this.reportsMapper.toEntity(dto));
        return ResponseDto.<ReportsDto>builder()
                .message("OK")
                .success(true)
                .data(dto)
                .build();
    }catch (Exception e) {
         return ResponseDto.<ReportsDto>builder()
                 .message("While database saving error : "+e.getMessage())
                 .code(-3)
                 .build();

        }
        }

    @Override
    public ResponseDto<ReportsDto> get(Integer id) {
        try {
            return this.reportsRepository.findByIdAndDeletedAtIsNull(id).map(reports ->
             ResponseDto.<ReportsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(this.reportsMapper.toDto(reports))
                    .build()
                 ).orElse(
                         ResponseDto.<ReportsDto>builder()
                                 .code(-1)
                                 .message("Reports is not found!")
                                 .build()
            );
        }catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("While database getting error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<ReportsDto> update(ReportsDto dto, Integer id) {
        try {
            return this.reportsRepository.findByIdAndDeletedAtIsNull(id).map(reports -> {
                reports.setUpdatedAt(LocalDateTime.now());
                this.reportsMapper.updateToDtoFromEntity(dto,reports);
                this.reportsRepository.save(reports);
                return ResponseDto.<ReportsDto>builder()
                        .message("OK")
                        .success(true)
                        .data(dto)
                        .build();
            }).orElse(
                    ResponseDto.<ReportsDto>builder()
                            .code(-1)
                            .message("Reports is not found!")
                            .build()
            );
        }catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("While database update error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<ReportsDto> delete(Integer id) {
        try {
            return this.reportsRepository.findByIdAndDeletedAtIsNull(id).map(reports -> {
                reports.setDeletedAt(LocalDateTime.now());
                this.reportsRepository.save(reports);
              return   ResponseDto.<ReportsDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.reportsMapper.toDto(reports))
                        .build();
            } ).orElse(
                    ResponseDto.<ReportsDto>builder()
                            .code(-1)
                            .message("Reports is not found!")
                            .build()
            );
        }catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("While database deleting error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<Page<ReportsDto>> getAllByPage(Integer page, Integer size) {
        Page<ReportsDto> map = this.reportsRepository.findAllByPage(PageRequest.of(page, size)).map(this.reportsMapper::toDto);
        if (map.isEmpty()) {
            return  ResponseDto.<Page<ReportsDto>>builder()
                    .code(-1)
                    .message("Reports are not found!")
                    .build();
        }
        return ResponseDto.<Page<ReportsDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();


    }
}
