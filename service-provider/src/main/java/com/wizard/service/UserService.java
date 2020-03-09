package com.wizard.service;

import com.wizard.mapper.UserMapper;
import com.wizard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id){
        return this.userMapper.selectByPrimaryKey(id);
    }
}
