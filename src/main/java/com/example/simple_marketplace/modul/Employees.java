package com.example.simple_marketplace.modul;

import jakarta.persistence.*;
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
@Entity
public class Employees {
    @Id
    @SequenceGenerator(name = "employee_sequence",sequenceName = "employee_seq",allocationSize = 1)
    @GeneratedValue(generator ="employee_sequence" )
    private Integer id;
    @OneToMany
    private List<User> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
