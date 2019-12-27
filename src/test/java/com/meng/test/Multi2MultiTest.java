package com.meng.test;

import com.meng.dao.IAccount2Dao;
import com.meng.dao.IRoleDao;
import com.meng.dao.IUser2Dao;
import com.meng.dao.IUser3Dao;
import com.meng.entity.Account2;
import com.meng.entity.User2;
import com.meng.entity.User3;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:SqlMapConfig.xml"})
@SpringBootTest
public class Multi2MultiTest {

    InputStream in;
    SqlSession session;
    IUser3Dao userDao;
    IRoleDao roleDao;

    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();

        userDao = session.getMapper(IUser3Dao.class);
        roleDao = session.getMapper(IRoleDao.class);
    }

    @After
    public void after() throws IOException {
        session.commit();

        session.close();
        in.close();
    }

    @Test
    public void testFindAllUser(){
        List<User3> users = userDao.findAll();
        for(var user:users){
            System.out.println("---分割线---");
            System.out.println(user);
            var roles = user.getRoles();
            if(roles.size() > 0){
                for(var i:roles)
                    System.out.println(i);
            }
        }
    }

    @Test
    public void testFindAllRole(){
        var roles = roleDao.findAll();
        for(var role:roles){
            System.out.println("---分割线---");
            System.out.println(role);
            var users  = role.getUsers();
            if(users.size() > 0){
                for(var i:users)
                    System.out.println(i);
            }
        }
    }
}
