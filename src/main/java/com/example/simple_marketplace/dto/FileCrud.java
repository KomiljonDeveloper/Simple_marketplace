package com.example.simple_marketplace.dto;

import org.springframework.web.multipart.MultipartFile;

public interface FileCrud<T, X, Y> {
    ResponseDto<T> upload(X image);

    ResponseDto<T> download(Y id);

    ResponseDto<T> update(X image, Y id);

    ResponseDto<T> delete(Y id);
}
