package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.ProductDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.repository.ProductRepository;
import com.example.simple_marketplace.service.mapper.ProductMapper;
import com.example.simple_marketplace.utils.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements SimpleCrud<ProductDto, Integer> {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryImpl;

    @Override
    public ResponseDto<ProductDto> create(ProductDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            this.productRepository.save(this.productMapper.toEntity(dto));
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("OK")
                    .data(dto)
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<ProductDto> get(Integer id) {
        try {
            return this.productRepository.findByProductIdAndDeletedAtIsNull(id).map(product ->
                    ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.productMapper.toDto(product))
                    .build())

                    .orElse(ResponseDto.<ProductDto>builder()
                    .message("Product is not found!")
                    .code(-1)
                    .build());
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<ProductDto> update(ProductDto dto, Integer id) {
        try {
            return this.productRepository.findByProductIdAndDeletedAtIsNull(id).map(product -> {
                product.setUpdatedAt(LocalDateTime.now());
                this.productMapper.updateToDtoFromEntity(product, dto);
                this.productRepository.save(product);
                return ResponseDto.<ProductDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.productMapper.toDto(product))
                        .build();
            }).orElse(ResponseDto.<ProductDto>builder()
                    .message("Product is not found!")
                    .code(-1)
                    .build());
        }catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .build();
        }    }

    @Override
    public ResponseDto<ProductDto> delete(Integer id) {
        try {
            return this.productRepository.findByProductIdAndDeletedAtIsNull(id).map(product -> {
                      product.setDeletedAt(LocalDateTime.now());
                      this.productRepository.save(product);
                       return ResponseDto.<ProductDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.productMapper.toDto(product))
                                .build();
                    })
                    .orElse(ResponseDto.<ProductDto>builder()
                            .message("Product is not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("While database deleting error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ProductDto>> getAllByPage(Integer page, Integer size) {
        Page<ProductDto> map = this.productRepository.findAllByPage(PageRequest.of(page, size)).map(this.productMapper::toDto);
        if (map.isEmpty()) {
            return  ResponseDto.<Page<ProductDto>>builder()
                    .code(-1)
                    .message("Products are not found!")
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();
    }

    public ResponseDto<Page<ProductDto>> searchByBasic(Map<String, String> params) {
        int page  = 0,size = 0;
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        Page<ProductDto> productDto = this.productRepository.searchByBasic(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("name"),
                PageRequest.of(page, size)
        ).map(this.productMapper::toDto);
        if (productDto.isEmpty()){
            return ResponseDto.<Page<ProductDto>>builder()
                    .code(-1)
                    .message("Products not found!")
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .message("oK")
                .success(true)
                .data(productDto)
                .build();
    }

    public ResponseDto<Page<ProductDto>> searchByAdvanced(Map<String, String> params) {

       return Optional.of(this.productRepositoryImpl.searchByAdvanced(params).map(this.productMapper::toDto)).map(t ->
               ResponseDto.<Page<ProductDto>>builder()
                       .success(true)
                       .message("OK")
                       .data(t)
                       .build()

       ).orElse( ResponseDto.<Page<ProductDto>>builder()
               .code(-1)
               .message("Product is not found!")
               .build());
    }
}
