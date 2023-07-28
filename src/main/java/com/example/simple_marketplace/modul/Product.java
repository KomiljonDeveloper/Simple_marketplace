package com.example.simple_marketplace.modul;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence",sequenceName = "product_seq",allocationSize = 1)
    @GeneratedValue(generator = "product_sequence")
    private Integer productId;
    private String prod_name;
    private Double received_price;
    private Double selling_price;
    private Double prod_mass;
    private Double amount;
    @OneToOne
    private Image image;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
