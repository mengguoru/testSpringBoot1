package com.meng.test;

import com.meng.dao.IAccount2Dao;
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

    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();

        userDao = session.getMapper(IUser3Dao.class);
        //accountDao = session.getMapper(IAccount2Dao.class);
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
}
