package com.service;

import com.model.User;
import org.springframework.stereotype.Component;

@Component //记住这个实现类也要加入容器，不然在controller不能使用
public class AuthServiceImpl implements AuthService {


    @Override
    public String empLogin(User user) {
        //need to do ,if server die
        return "not find the method";
    }


}
