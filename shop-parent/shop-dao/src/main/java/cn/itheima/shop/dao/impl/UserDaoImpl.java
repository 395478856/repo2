package cn.itheima.shop.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cn.itheima.shop.dao.UserDao;
import cn.itheima.shop.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public List<User> selectAllUser() {
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User");
		return userList;
	}

	@Override
	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findUserByUserName(String username) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find("from User where username=?", username);
		if (userList.size() != 0) {
			return (User) userList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}
}
