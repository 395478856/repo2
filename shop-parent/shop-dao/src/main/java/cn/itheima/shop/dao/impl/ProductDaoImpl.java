package cn.itheima.shop.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itheima.shop.dao.ProductDao;
import cn.itheima.shop.entity.Product;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	@Override
	public List<Product> findAllProduct() {
		return (List<Product>) this.getHibernateTemplate().find("from Product");
	}

}
