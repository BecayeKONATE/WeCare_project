package com.saraya.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Optional<Booking> findByUserId(String userId);
    @Query("select b from Booking b where b.userId = :userId and b.appointmentDate >= :today")
    List<Booking> findBookingByUserId(String userId, LocalDate today);
    @Query("select b from Booking b where b.coachId = :coachId and b.appointmentDate >= :today")
    List<Booking> findBookingByCoachId(String coachId, LocalDate today);
    @Query("select b from Booking b where b.userId = :userId and b.appointmentDate = :appointmentDate and b.slot = :slot")
    Booking findAllBookings(String userId, LocalDate appointmentDate, String slot);

    Optional<Booking> findByBookingId(int bookingId);
}
