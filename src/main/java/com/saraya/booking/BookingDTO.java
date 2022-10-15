package com.saraya.booking;

import com.saraya.coach.Coach;
import com.saraya.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private int bookingId;
    private String userId;
    private String coachId;
    private String slot;
    private LocalDate appointmentDate;
}
