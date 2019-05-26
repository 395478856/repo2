package cn.itheima.shop.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itheima.shop.entity.Product;
import cn.itheima.shop.service.ProductService;

public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductService productService;
	
	public String findAllProduct() throws Exception {
		List<Product> hotProductList=productService.findAllProduct();
		List<Product> newProductList=productService.findAllProduct();
		if(hotProductList!=null){
			ActionContext.getContext().put("hotProductList", hotProductList);
		}
		if(newProductList!=null){
			ActionContext.getContext().put("newProductList", newProductList);
		}
		return "toIndex";
	}

	
}
