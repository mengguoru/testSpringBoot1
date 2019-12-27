package com.meng.dao;

import com.meng.entity.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();

    /**
     * 根据id查找User
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据传入id数组查找
     * @param ids
     * @return
     */
    List<User> findByIds(List<Integer> ids);

    /**
     * 根据用户名模糊查找
     * @param uname
     * @return
     */
    List<User> findByName(String uname);

    /**
     * 条件查找
     * @param user
     * @return
     */
    List<User> findByCondition(User user);

    int total();

    void save(User user);

    void update(User user);

    void delete(Integer userId);
}
