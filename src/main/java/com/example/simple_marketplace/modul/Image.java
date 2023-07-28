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
public class Image {
    @Id
    @SequenceGenerator(name = "image_sequence",sequenceName = "image_seq",allocationSize = 1)
    @GeneratedValue(generator = "image_sequence")
    private Integer id;
    private String name;
    private String path;
    private String type;
    private Double size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
