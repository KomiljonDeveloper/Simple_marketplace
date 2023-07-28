package com.example.simple_marketplace.dto;

import com.example.simple_marketplace.modul.Image;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Integer id;
    @NotBlank(message = "Firstname is cannot be null or empty!")
    private String firstName;
    @NotBlank(message = "Lastname is cannot be null or empty!")
    private String lastName;
    @NotBlank(message = "Middle name is cannot be null or empty!")
    private String middleName;
    @NotBlank(message = "Username is cannot be null or empty!")
    private String username;
    @NotBlank(message = "Borrow Name is cannot be null or empty!")
    private String borrowName;
    @NotBlank(message = "Phone Number is cannot be null or empty!")
    private String phoneNumber;
    @NotBlank(message = "Passport Series is cannot be null or empty!")
    private String passportSeries;
    @NotBlank(message = "First address is cannot be null or empty!")
    private String firstAddress;
    @Email(message = "This email is invalid!")
    private String email;
    private String secondAddress;
    @Max(value = 25,message = "This age is invalid ! age = (15<age<25)")@Min(value = 15,message = "This age is invalid ! age = (15<age<25)")
    private  Integer age;
    @NotNull(message = "Monthly price is cannot be empty or null!")
    private Double monthlyPrice;
    private List<LoanerDto> loaners;
    private Image imageId;
    @NotNull(message = "Birthday is cannot be null or empty ")
    private String birthday;
    private LocalDateTime workingTime;
    private LocalDateTime workingDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
