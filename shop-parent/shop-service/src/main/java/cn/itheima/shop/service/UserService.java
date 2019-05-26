package cn.itheima.shop.service;

import java.util.List;

import cn.itheima.shop.entity.User;

public interface UserService {
	public List<User> selectAllUser();

	public boolean saveUser(User user);

	public boolean activeUser(User user);

	public boolean checkUserName(String username);

	public int checkUserInfo(User user);

	public User findUserByUserName(String username);

	}
