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
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_seq",allocationSize = 1)
    @GeneratedValue(generator = "user_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String borrowName;
    private String phoneNumber;
    private String passportSeries;
    private String firstAddress;
    private String secondAddress;
    private String email;
    private String monthlyPrice;
    @OneToMany
    private List<Loaner> loaners;
    @OneToOne
    private Image imageId;
    private String birthday;
    private Integer workingTime;
    private Integer workingDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
