package com.service;

import com.model.TimeSheet;
import com.model.Vacation;

import javax.transaction.Transactional;


public interface UpdateTableService {
    public void updateTimesheet(TimeSheet timeSheet);

    void addVacation(Vacation vacation, String username);

    void updatePassword(int id, String token, String password);

    void deleteUsedTokenFrom(int id);
}
