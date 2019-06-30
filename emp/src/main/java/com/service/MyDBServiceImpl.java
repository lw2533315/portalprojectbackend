package com.service;

import com.Repository.CustomRepository;
import com.model.TimeSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyDBServiceImpl implements MyDBService {
    @Autowired
    private CustomRepository customRepository;

    @Override
    public TimeSheet findTimeSheetByUsername(String username) {
        return null;
    }
}
