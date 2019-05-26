package cn.itheima.shop.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itheima.shop.entity.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User loginUser=(User) ActionContext.getContext().getSession().get("loginUser");
		if(loginUser!=null){
			//用户已经登录
			//放行
			return invocation.invoke();
		}else{
			//用户未登录
			return "toLogin";
		}
	}

	

}
