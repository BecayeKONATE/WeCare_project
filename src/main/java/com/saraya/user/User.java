package com.saraya.user;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "user-generator")
    @GenericGenerator(name = "user-generator", strategy = "com.saraya.user.UserIdGenerator")
    private String userId;
    private String name;
    private String password;
    private char gender;
    private LocalDate dateOfBirth;
    private long mobileNumber;
    private String email;
    private int pincode;
    private String city;
    private String state;
    private String country;
}
