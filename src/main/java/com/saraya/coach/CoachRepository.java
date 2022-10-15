package com.saraya.coach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach,String> {

    Optional<Coach> findByCoachId(String id);
}
