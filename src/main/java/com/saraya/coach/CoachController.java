package com.saraya.coach;

import com.saraya.booking.BookingDTO;
import com.saraya.booking.BookingService;
import com.saraya.exception.ErrorMessage;
import com.saraya.exception.WecareException;
import com.saraya.login.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService service;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity createCoach(@RequestBody CoachDTO coachDTO, Errors errors){
        String response = "";
        if (errors.hasErrors()) {
            response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            ErrorMessage error = new ErrorMessage();
            error.setMessage(response);
            return ResponseEntity.ok(error);
        }else {
            response = service.createCoach(coachDTO);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO loginDTO) throws WecareException {
        boolean response = service.loginCoach(loginDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable String coachId){
        CoachDTO dto = service.getCoachProfile(coachId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/booking/{coachId}")
    public List<BookingDTO> showMyAppointments(@PathVariable String coachId){
        return bookingService.findBookingByCoachId(coachId);
    }
}
