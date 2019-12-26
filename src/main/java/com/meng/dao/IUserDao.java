package com.meng.dao;

import com.meng.entity.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
