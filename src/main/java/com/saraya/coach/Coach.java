package com.saraya.coach;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Coach {

    @Id
    @GeneratedValue(generator = "coach-generator")
    @GenericGenerator(name = "coach-generator", strategy = "com.saraya.coach.CoachIdGenerator")
    private String coachId;
    private String name;
    private String password;
    private char gender;
    private LocalDate dateOfBirth;
    private long mobileNumber;
    private String speciality;

}
