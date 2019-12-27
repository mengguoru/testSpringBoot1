package com.meng.test;

import com.meng.dao.IUser2Dao;
import com.meng.dao.IUserDao;
import com.meng.entity.Account2;
import com.meng.entity.User;
import com.meng.entity.User2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:SqlMapConfig.xml"})
@SpringBootTest
public class One2MultiTest {

    InputStream in;
    SqlSession session;
    IUser2Dao userDao;

    @Before
    public void init() throws  Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();

        userDao = session.getMapper(IUser2Dao.class);
    }

    @After
    public void after() throws IOException {
        session.commit();

        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User2> users = userDao.findAll();
        for(User2 user:users){
            System.out.println("---分割线---");
            System.out.println(user);
            List<Account2> accounts = user.getAccounts();
            if(accounts.size() > 0){
                for(Account2 a:accounts)
                    System.out.println(a);
            }

        }

    }
}
