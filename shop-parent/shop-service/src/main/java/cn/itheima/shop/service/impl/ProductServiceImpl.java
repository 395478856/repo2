package cn.itheima.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itheima.shop.dao.ProductDao;
import cn.itheima.shop.dao.impl.ProductDaoImpl;
import cn.itheima.shop.entity.Product;
import cn.itheima.shop.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Resource(name="productDao")
	private ProductDao productDao;

	@Override
	public List<Product> findAllProduct() {
		return productDao.findAllProduct();
	}

	

}
