package com.repository;

import com.model.Tasksheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksheetRepository extends JpaRepository<Tasksheet, Long > {
}
