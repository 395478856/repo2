package cn.itheima.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.shop.dao.UserDao;
import cn.itheima.shop.dao.impl.UserDaoImpl;
import cn.itheima.shop.entity.User;
import cn.itheima.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao = new UserDaoImpl();

	public List<User> selectAllUser() {
		System.out.println(userDao);
		return userDao.selectAllUser();
	}

	@Override
	public boolean saveUser(User user) {
		if (user != null) {
			userDao.saveUser(user);
			return true;
		}
		return false;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean activeUser(User user) {
		String username = user.getUsername();
		String activeCode = user.getCode();
		// 1.根据用户名查询出用户
		User findUser = userDao.findUserByUserName(username);
		// 2.将用户传的激活码和库中用户的激活码进行比较
		if (findUser != null) {
			if (findUser.getCode().equals(user.getCode())) {
				// 激活码和库中的一致
				// 设置激活状态为1，表激活
				findUser.setState(1);
				// 将数据更新到数据库
				userDao.updateUser(findUser);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkUserName(String username) {
		User findUser = userDao.findUserByUserName(username);
		if (findUser != null) {
			return true;
		}
		return false;
	}

	@Override
	public int checkUserInfo(User user) {
		if (user != null) {
			String username = user.getUsername();
			boolean isExist = this.checkUserName(username);
			if (isExist) {
				// 2.用户存在，检验密码是否正确
				User findUser = userDao.findUserByUserName(username);
				if (user.getPassword().equals(findUser.getPassword())) {
					// 2.1密码正确，将用户存入session域，跳转到主页
					// 判断用户是否激活
					if (findUser.getState() == 1) {
						return 1;
					} else {
						// 用户未激活
						return 2;
					}
				} else {
					// 2.2密码错误，返回错误信息，返回登录页面
					return -1;
				}
			} else {
				// 1.用户不存在，返回提示用户名错误，返回登录页面
				return 0;
			}
		}
		return -2;
	}

	@Override
	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}

}
