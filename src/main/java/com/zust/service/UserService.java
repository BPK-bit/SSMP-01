package com.zust.service;

import com.zust.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User login(User user);
}
