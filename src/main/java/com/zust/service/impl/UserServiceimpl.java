package com.zust.service.impl;

import com.zust.dao.UserMapper;
import com.zust.domain.Entities.User;
import com.zust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(User user) {
        String password = user.getPassword();
        Integer id = user.getId();
        User user1 = userMapper.selectById(id);
        if (user1==null){
            throw new RuntimeException("账号不存在");
        }
        if (user1.getPassword().equals(password)){
            return user1;
        }else {
            throw new RuntimeException("密码错误");
        }
    }
}
