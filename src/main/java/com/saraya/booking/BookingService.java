package com.saraya.booking;

import com.saraya.coach.CoachRepository;
import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WecareException;
import com.saraya.user.UserRepository;
import com.saraya.utility.MailUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MailUtility mail;
    private final CoachRepository coachRepository;

    public boolean bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) throws WecareException {
        Booking booking = bookingRepository.findAllBookings(userId, appointmentDate, slot);
        if (booking == null){

            Booking booking1 = new Booking();
            booking1.setUserId(userId);
            booking1.setSlot(slot);
            booking1.setCoachId(coachId);
            booking1.setAppointmentDate(appointmentDate);

            bookingRepository.save(booking1);

            String userName = userRepository.findByUserId(userId).get().getName();
            String coachName = coachRepository.findByCoachId(coachId).get().getName();
            String email = userRepository.findByUserId(userId).get().getEmail();

            mail.sendSchedulingEmail(userName,coachName, email, booking1.getBookingId(), slot, appointmentDate);
            return true;
        }
        throw new WecareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
    }

    public List<BookingDTO> findBookingByUserId(String userId){
        List<Booking> bookings = bookingRepository.findBookingByUserId(userId, LocalDate.now());
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for(Booking bk : bookings){
            BookingDTO dto = new BookingDTO();
            dto.setUserId(bk.getUserId());
            dto.setCoachId(bk.getCoachId());
            dto.setSlot(bk.getSlot());
            dto.setAppointmentDate(bk.getAppointmentDate());
            bookingDTOS.add(dto);
        }
        return bookingDTOS;
    }

    public List<BookingDTO> findBookingByCoachId(String userId){
        List<Booking> bookings = bookingRepository.findBookingByCoachId(userId, LocalDate.now());
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for(Booking bk : bookings){
            BookingDTO dto = new BookingDTO();
            dto.setUserId(bk.getUserId());
            dto.setCoachId(bk.getCoachId());
            dto.setSlot(bk.getSlot());
            dto.setAppointmentDate(bk.getAppointmentDate());
            bookingDTOS.add(dto);
        }
        return bookingDTOS;
    }

    public boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) throws WecareException {
        Booking booking = bookingRepository.getReferenceById(bookingId);
        String userId = bookingRepository.getReferenceById(bookingId).getUserId();
        String coachId = bookingRepository.getReferenceById(bookingId).getUserId();
        Booking booking1 = new Booking(bookingId,userId,coachId,slot,appointmentDate);
        Booking allBookings = bookingRepository.findAllBookings(userId, appointmentDate, slot);
        if (allBookings == null) {
            bookingRepository.save(booking1);

            String userName = userRepository.findByUserId(userId).get().getName();
            String coachName = coachRepository.findByCoachId(coachId).get().getName();
            String email = userRepository.findByUserId(userId).get().getEmail();

            mail.sendReschedulingEmail(userName, coachName, email, bookingId, slot, appointmentDate);
            return true;
        }
        throw new WecareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
    }

    public void cancelAppointment(Integer bookingId){
        Booking booking = bookingRepository.findByBookingId(bookingId).get();
        bookingRepository.delete(booking);

        String userId = booking.getUserId();
        String coachId = booking.getCoachId();
        String userName = userRepository.findByUserId(userId).get().getName();
        String coachName = coachRepository.findByCoachId(coachId).get().getName();
        String email = userRepository.findByUserId(userId).get().getEmail();
        mail.sendCancellingEmail(userName, coachName, email, bookingId, booking.getSlot(), booking.getAppointmentDate());
    }
}
