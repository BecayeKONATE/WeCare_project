package com.saraya.user;

import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WecareException;
import com.saraya.login.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public String createUser(UserDTO userDTO){
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        user.setPincode(userDTO.getPincode());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());

        repository.save(user);

        return "user created successfully your user id is " + user.getUserId();
    }

    public boolean loginUser(LoginDTO loginDTO) throws WecareException {

        Optional<User> optional = repository.findByUserId(loginDTO.getId());
        User user = optional.get();

        if(user.getUserId().equals(loginDTO.getId()) && user.getPassword().equals(loginDTO.getPassword())){
            return true;
        }
        throw new WecareException(ExceptionConstants.USER_NOT_FOUND.toString());
    }

    public UserDTO getUserProfile(String id){

        User user = repository.findByUserId(id).get();
        if (user != null){
            return new UserDTO(user.getUserId(),
                    user.getName(),
                    user.getPassword(),
                    user.getGender(),
                    user.getDateOfBirth(),
                    user.getMobileNumber(),
                    user.getEmail(),
                    user.getPincode(),
                    user.getCity(),
                    user.getState(),
                    user.getCountry()
            );
        }
        return null;
    }
}
