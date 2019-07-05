package com.repository;

import com.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    List<Vacation> findByEmpId(int id);
    List<Vacation> findByStatus(String status);
}
