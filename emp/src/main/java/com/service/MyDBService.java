package com.service;

import com.model.TimeSheet;
import org.springframework.stereotype.Service;

@Service
public interface MyDBService {
    TimeSheet findTimeSheetByUsername(String username);
}
