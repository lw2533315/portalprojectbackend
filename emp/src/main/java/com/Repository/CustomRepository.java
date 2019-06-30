package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.TimeSheet;

@Repository
public interface CustomRepository extends JpaRepository<TimeSheet, String>{
    TimeSheet findBy

}
