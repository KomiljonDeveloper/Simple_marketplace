package com.example.simple_marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForeignDebtDto {
    private Integer id;
    private String companyName;
    private String fullName;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private List<ProductDto> products;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
