package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.BasketDto;
import com.example.simple_marketplace.dto.CategoryDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.modul.Category;
import com.example.simple_marketplace.repository.CategoryRepository;
import com.example.simple_marketplace.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService implements SimpleCrud<CategoryDto, Integer> {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseDto<CategoryDto> create(CategoryDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.categoryMapper.toDto(this.categoryRepository.save(this.categoryMapper.toEntity(dto))))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<CategoryDto> get(Integer id) {
        try {
            return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(id).map(category ->
                    ResponseDto.<CategoryDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.categoryMapper.toDto(category))
                            .build()
            ).orElse(ResponseDto.<CategoryDto>builder()
                    .message("Category not found!")
                    .code(-1)
                    .build());

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<CategoryDto> update(CategoryDto dto, Integer id) {
        try {
            return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(id).map(category -> {
                dto.setUpdatedAt(LocalDateTime.now());
                this.categoryMapper.updateToDtoFromEntity(dto, category);
                return ResponseDto.<CategoryDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.categoryMapper.toDto(this.categoryRepository.save(category)))
                        .build();
            }).orElse(ResponseDto.<CategoryDto>builder()
                    .message("Category not found!")
                    .code(-1)
                    .build());

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<CategoryDto> delete(Integer id) {
        try {
           return this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(id).map(category -> {
                category.setDeletedAt(LocalDateTime.now());
                this.categoryRepository.save(category);
                return ResponseDto.<CategoryDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.categoryMapper.toDto(category))
                        .build();

            }).orElse(ResponseDto.<CategoryDto>builder()
                            .code(-1)
                            .message("Category not found!")
                    .build());

        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("While database deleting error : %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<Page<CategoryDto>> getAllByPage(Integer page, Integer size) {
        Page<CategoryDto> map = this.categoryRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size)).map(this.categoryMapper::toDto);
        if (map.isEmpty()) {
            return  ResponseDto.<Page<CategoryDto>>builder()
                    .code(-1)
                    .message("Categories are not found!")
                    .build();
        }
        return ResponseDto.<Page<CategoryDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();
    }
}
