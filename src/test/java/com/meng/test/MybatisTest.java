package com.meng.test;

import com.meng.dao.IUserDao;
import com.meng.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
public class MybatisTest {

    @Test
    public void testFindAll() throws  Exception{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();

        IUserDao userDao = session.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for(User user:users)
            System.out.println(user);
    }

//    public static void main(String[] args) throws IOException {
//
//    }
}
