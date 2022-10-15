package com.saraya.user;

import com.saraya.booking.BookingDTO;
import com.saraya.booking.BookingService;
import com.saraya.exception.ErrorMessage;
import com.saraya.exception.WecareException;
import com.saraya.login.LoginDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO userDTO, Errors errors) throws WecareException {

        String response = "";

        if (errors.hasErrors()) {
            response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            ErrorMessage error = new ErrorMessage();
            error.setMessage(response);
            return ResponseEntity.ok(error);
        }
        else
        {
            response = userService.createUser(userDTO);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) throws WecareException {
        boolean longinInfo = userService.loginUser(loginDTO);
        return ResponseEntity.ok(longinInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String id){
        UserDTO dto = userService.getUserProfile(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/booking/{userId}")
    public List<BookingDTO> showMyAppointments( @PathVariable String userId){
        return bookingService.findBookingByUserId(userId);
    }
}
