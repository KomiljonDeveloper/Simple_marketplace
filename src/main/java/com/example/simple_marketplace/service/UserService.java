package com.example.simple_marketplace.service;

import com.example.simple_marketplace.dto.ReportsDto;
import com.example.simple_marketplace.dto.ResponseDto;
import com.example.simple_marketplace.dto.SimpleCrud;
import com.example.simple_marketplace.dto.UserDto;
import com.example.simple_marketplace.modul.User;
import com.example.simple_marketplace.repository.UserRepository;
import com.example.simple_marketplace.service.mapper.UserMapper;
import com.example.simple_marketplace.utils.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<UserDto, Integer> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRepositoryImpl userRepositoryImpl;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        try {
            dto.setCreatedAt(LocalDateTime.now());


            this.userRepository.save(this.userMapper.toEntity(dto));
            return ResponseDto.<UserDto>builder()
                    .message("OK")
                    .success(true)
                    .data(dto)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database saving error : " + e.getMessage())
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
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database getting error : " + e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer id) {
        try {
            return this.userRepository.findByIdAndDeletedAtIsNull(id).map(user -> {
                user.setUpdatedAt(LocalDateTime.now());
                this.userMapper.updateToDtoFromEntity(user, dto);
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
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database update error : " + e.getMessage())
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
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("While database deleting error : " + e.getMessage())
                    .code(-3)
                    .build();

        }
    }

    public ResponseDto<List<UserDto>> getAll() {
        List<UserDto> collect = this.userRepository.findAllByDeletedAtIsNull().stream().map(this.userMapper::toDto
        ).toList();
        if (!collect.isEmpty()) {
            return ResponseDto.<List<UserDto>>builder()
                    .message("OK")
                    .success(true)
                    .data(collect)
                    .build();
        }
        return ResponseDto.<List<UserDto>>builder()
                .message("Users nor found!")
                .code(-1)
                .build();
    }

    public ResponseDto<Page<UserDto>> getAllByPage(Integer page, Integer size) {
        Page<User> userPage = this.userRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        Page<UserDto> map = userPage.map(this.userMapper::toDto);
        if (map.isEmpty()) {
            return ResponseDto.<Page<UserDto>>builder()
                    .message("Users not found!")
                    .code(-5)
                    .build();
        }
        return ResponseDto.<Page<UserDto>>builder()
                .message("Ok")
                .success(true)
                .data(map)
                .build();
    }


    public ResponseDto<Page<UserDto>> searchByBasic(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        Page<UserDto> map = this.userRepository.searchByBasic(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("firstname"),
                params.get("lastname"),
                params.get("email"),
                params.get("username"),
                PageRequest.of(page, size)
        ).map(this.userMapper::toDto);

        if (map.isEmpty()) {
            return ResponseDto.<Page<UserDto>>builder()
                    .message("Users are not found?")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(map)
                .build();

    }

    public ResponseDto<Page<UserDto>> searchByAdvanced(Map<String, String> params) {
        return Optional.of(this.userRepositoryImpl.advancedSearch(params).map(this.userMapper::toDto)).map(users ->
                ResponseDto.<Page<UserDto>>builder()
                        .success(true)
                        .message("OK")
                        .data(users)
                        .build()
        ).orElse(
                ResponseDto.<Page<UserDto>>builder()
                        .message("User is not found!")
                        .code(-1)
                        .build());


    }

}
