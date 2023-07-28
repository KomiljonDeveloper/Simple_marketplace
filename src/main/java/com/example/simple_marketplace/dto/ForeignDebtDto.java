package com.example.simple_marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Company name is cannot be empty or null!")
    private String companyName;
    @NotBlank(message = "Full name name is cannot be empty or null!")
    private String fullName;
    @NotBlank(message = "First phone number is cannot be empty or null!")
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private List<ProductDto> products;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
