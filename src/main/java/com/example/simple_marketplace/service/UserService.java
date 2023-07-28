package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.repository.UserRepository;
import com.example.simple_marketplace.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<UserDto,Integer> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
       try {  dto.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(this.userMapper.toEntity(dto));
        return ResponseDto.<UserDto>builder()
                .message("OK")
                .success(true)
                .data(dto)
                .build();
    }
       catch (Exception e) {
        return ResponseDto.<UserDto>builder()
                .message("While database saving error : "+e.getMessage())
                .code(-3)
                .build();

    }
    }

    @Override
    public ResponseDto<UserDto> get(Integer id) {
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id).map(user ->
            ResponseDto.<UserDto>builder()
                    .message("OK")
                    .success(true)
                    .data(this.userMapper.toDto(user))
                    .build()).orElse(
                            ResponseDto.<UserDto>builder()
                                    .message("User is not found!")
                                    .code(-1)
                                    .build()

            );
        }
        catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database getting error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer id) {
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id).map(user -> {
                user.setUpdatedAt(LocalDateTime.now());
                this.userMapper.updateToDtoFromEntity(dto,user);
                this.userRepository.save(user);
                return ResponseDto.<UserDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.userMapper.toDto(user))
                        .build();
            }).orElse(
                    ResponseDto.<UserDto>builder()
                            .message("User is not found!")
                            .code(-1)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database update error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<UserDto> delete(Integer id) {
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id).map(user -> {
                user.setDeletedAt(LocalDateTime.now());
                this.userRepository.save(user);
                return ResponseDto.<UserDto>builder()
                        .message("OK")
                        .success(true)
                        .data(this.userMapper.toDto(user))
                        .build();
            }).orElse(
                    ResponseDto.<UserDto>builder()
                            .message("User is not found!")
                            .code(-1)
                            .build()
            );
        }
        catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database deleting error : "+e.getMessage())
                    .code(-3)
                    .build();

        }
    }
}
