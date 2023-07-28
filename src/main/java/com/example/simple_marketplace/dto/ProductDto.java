package com.example.simple_marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer productId;
    @NotBlank(message = "Prod name name is cannot be empty or null!")
    private String prod_name;
    private Double received_price;
    private Double selling_price;
    @NotNull(message = "Prod mass is cannot be empty or null!")
    private Double prod_mass;
    @NotNull(message = "Amount is cannot be empty or null!")
    private Double amount;
    private Integer imageId;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
