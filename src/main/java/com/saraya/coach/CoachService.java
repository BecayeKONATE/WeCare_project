package com.saraya.coach;

import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WecareException;
import com.saraya.login.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachService {

    private final CoachRepository repository;

    public String createCoach(CoachDTO coachDTO){

        Coach coach = new Coach();

        coach.setName(coachDTO.getName());
        coach.setPassword(coachDTO.getPassword());
        coach.setGender(coachDTO.getGender());
        coach.setSpeciality(coachDTO.getSpeciality());
        coach.setMobileNumber(coachDTO.getMobileNumber());
        coach.setDateOfBirth(coachDTO.getDateOfBirth());

        repository.save(coach);

        return "Coach successfully created your id is " + coach.getCoachId();
    }

    public boolean loginCoach(LoginDTO loginDTO) throws WecareException {

        Optional<Coach> optional = repository.findByCoachId(loginDTO.getId());
        Coach coach = optional.get();

        if (coach != null && coach.getPassword().equals(loginDTO.getPassword())){
            return true;
        }
        throw new WecareException(ExceptionConstants.COACH_NOT_FOUND.toString());
    }

    public CoachDTO getCoachProfile(String coachId){

        Optional<Coach> optional = repository.findByCoachId(coachId);
        Coach coach = optional.get();

        if (coach != null){
            return new CoachDTO(
                    coach.getCoachId(),
                    coach.getName(),
                    coach.getPassword(),
                    coach.getGender(),
                    coach.getDateOfBirth(),
                    coach.getMobileNumber(),
                    coach.getSpeciality()
            );
        }
        return null;
    }

    public List<CoachDTO> showAllCoaches(){
        List<Coach> coaches = repository.findAll();
        List<CoachDTO> dtos = new ArrayList<>();

        for(Coach coach : coaches){
            CoachDTO dto = new CoachDTO(
                    coach.getCoachId(),
                    coach.getName(),
                    coach.getPassword(),
                    coach.getGender(),
                    coach.getDateOfBirth(),
                    coach.getMobileNumber(),
                    coach.getSpeciality()
                    );
            dtos.add(dto);
        }
        return dtos;
    }
}
