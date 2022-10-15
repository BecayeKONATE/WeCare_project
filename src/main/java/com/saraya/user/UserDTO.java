package com.saraya.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;
    @NotNull(message = "can not be null")
    @Size(max = 50, min = 3, message = "name should be between 3 to 50 digits")
    private String name;
    @NotNull(message = "can not be null")
    @Size(max = 10, min = 5, message = "password should be between 5 to 10 digits")
    private String password;
    private char gender;
    @Past
    private LocalDate dateOfBirth;
    @NotNull(message = "can not be null")
    @Size(max = 10, min = 10, message = "enter a valid phone number")
    private long mobileNumber;
    @Email(message = "enter a valid email")
    private String email;
    @NotNull(message = "can not be null")
    @Size(max = 6, min = 6, message = "pin should be 6 digits")
    private int pincode;
    @NotNull(message = "can not be null")
    @Size(max = 20, min = 3)
    private String city;
    @NotNull(message = "can not be null")
    @Size(max = 20, min = 3)
    private String state;
    @NotNull(message = "can not be null")
    @Size(max = 20, min = 3)
    private String country;
}
