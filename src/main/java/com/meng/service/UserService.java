package com.meng.service;

import com.meng.dao.IUserDao;
import com.meng.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserService {
    private static SqlSessionFactory sqlSessionFactory;
    private static IUserDao iUserDao;

    public UserService() throws IOException {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(is);
        is.close();
    }

    public List<User>  findAll(){
        SqlSession session = sqlSessionFactory.openSession();
        iUserDao = session.getMapper(IUserDao.class);
        List<User> users = iUserDao.findAll();
        session.commit();
        session.close();

        return users;
    }
}
