package cn.itheima.shop.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itheima.shop.entity.User;
import cn.itheima.shop.service.UserService;
import cn.itheima.shop.service.impl.UserServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml","classpath:applicationContext-trans.xml"})
public class UserServiceTest {

	@Autowired
	private UserService service;
	@Test
	public void test() {
		List<User> userList = service.selectAllUser();
		System.out.println(userList);
	}

}
