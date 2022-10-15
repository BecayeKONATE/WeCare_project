package com.saraya.booking;

import com.saraya.coach.Coach;
import com.saraya.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    private String userId;
    private String coachId;
    private String slot;
    private LocalDate appointmentDate;

}
