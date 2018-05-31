package com.taotao.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.taotao.annotation.DataSource;
import com.taotao.dao.UserDao;
import com.taotao.dao.entity.User;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService
{
    

    @Override
    public User getUser(int id)
    {
        
        return baseMapper.selectById(id);
    }
    
}
