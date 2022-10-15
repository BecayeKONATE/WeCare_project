package com.saraya.coach;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CoachDTO {

    private String coachId;
    @NotNull(message = "should not be null")
    @Size(min = 3, max = 50)
    private String name;
    @NotNull(message = "should not be null")
    @Size(min = 5, max = 10)
    private String password;
    private char gender;
    @Past
    private LocalDate dateOfBirth;
    @NotNull(message = "should not be null")
    @Size(min = 10, max = 10)
    private long mobileNumber;
    @NotNull(message = "should not be null")
    @Size(min = 3, max = 50)
    private String speciality;
}
