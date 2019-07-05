package com.repository;

import com.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    public Vacation findByVacationId(int id);

}
