package com.saraya.booking;

import com.saraya.exception.WecareException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping("users/{userId}/booking/{coachId}")
    public ResponseEntity<Boolean> bookAppointment(@PathVariable String userId, @PathVariable String coachId, @RequestParam String slot, @RequestParam LocalDate dateOfAppointment) throws WecareException {
        boolean response = service.bookAppointment(userId, coachId, dateOfAppointment, slot);
        return ResponseEntity.ok(response);
    }

    @PutMapping("booking/{bookingId}")
    public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Integer bookingId, @RequestParam String slot, @RequestParam LocalDate dateOfAppointment) throws WecareException {
        boolean response = service.rescheduleAppointment(bookingId,dateOfAppointment, slot);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("booking/{bookingId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Integer bookingId){
        service.cancelAppointment(bookingId);
        return ResponseEntity.ok().build();
    }
}
