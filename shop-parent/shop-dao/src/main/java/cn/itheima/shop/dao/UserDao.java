package cn.itheima.shop.dao;

import java.util.List;

import cn.itheima.shop.entity.User;

public interface UserDao {
	public List<User> selectAllUser();

	public void saveUser(User user);

	public User findUserByUserName(String username);

	public void updateUser(User user);
}
