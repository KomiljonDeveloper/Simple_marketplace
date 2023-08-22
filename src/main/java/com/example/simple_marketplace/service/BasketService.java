package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.BasketDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.modul.Basket;
import com.example.simple_marketplace.repository.BasketRepository;
import com.example.simple_marketplace.service.mapper.BasketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService implements SimpleCrud<BasketDto, Integer> {
    private final BasketMapper basketMapper;
    private final BasketRepository basketRepository;

    @Override
    public ResponseDto<BasketDto> create(BasketDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());
            this.basketRepository.save(this.basketMapper.toEntity(dto));
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(dto)
                    .build();

        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("While database saving error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<BasketDto> get(Integer id) {
        try {
            return this.basketRepository.findByIdAndDeletedAtIsNull(id).map(basket ->
                    ResponseDto.<BasketDto>builder()
                            .success(true)
                            .message("OK")
                            .data(this.basketMapper.toDto(basket))
                            .build()
            ).orElse(ResponseDto.<BasketDto>builder()
                    .message("Basket not found!")
                    .code(-1)
                    .build());
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("While database getting error : %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<BasketDto> update(BasketDto dto, Integer id) {
        try {
            return this.basketRepository.findByIdAndDeletedAtIsNull(id).map(basket -> {
                dto.setUpdatedAt(LocalDateTime.now());
                this.basketMapper.updateToDtoFromEntity(dto, basket);
                this.basketRepository.save(basket);
                return ResponseDto.<BasketDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.basketMapper.toDto(basket))
                        .build();
            }).orElse(
                    ResponseDto.<BasketDto>builder()
                            .message("Basket not found!")
                            .code(-1)
                            .build()
            );
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("While database update error : %s", e.getMessage()))
                    .build();

        }
    }
    @Override
    public ResponseDto<BasketDto> delete(Integer id) {
        try {
            return this.basketRepository.findByIdAndDeletedAtIsNull(id).map(basket -> {
                basket.setDeletedAt(LocalDateTime.now());
                return ResponseDto.<BasketDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.basketMapper.toDto(this.basketRepository.save(basket)))
                        .build();
            }).orElse(ResponseDto.<BasketDto>builder()
                    .message("Basket not found!")
                    .code(-1)
                    .build());
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("While database deleting error : %s",e.getMessage()))
                    .build();
        }

    }

    @Override
    public ResponseDto<Page<BasketDto>> getAllByPage(Integer page, Integer size) {

        Page<BasketDto> map = this.basketRepository.findAllByPage(PageRequest.of(page, size)).map(this.basketMapper::toDto);

        if (map.isEmpty()) {
            return  ResponseDto.<Page<BasketDto>>builder()
                    .code(-1)
                    .message("Baskets are not found!")
                    .build();
        }
        return ResponseDto.<Page<BasketDto>>builder()
                .message("OK")
                .success(true)
                .data(map)
                .build();
    }
}
