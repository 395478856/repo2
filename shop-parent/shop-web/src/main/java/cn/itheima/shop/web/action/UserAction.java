package cn.itheima.shop.web.action;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.Provider.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itheima.shop.email.MailSenderInfo;
import cn.itheima.shop.email.MailSenderUtil;
import cn.itheima.shop.entity.User;
import cn.itheima.shop.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private MailSenderInfo mailInfo;
	private User user = new User();
	private Map<String,Object> jsonMsg;
	
	// 用户注册
	public String register() throws Exception {
		boolean registerResult = false;
		if (user != null) {
			// 校验用户注册信息是否合法
			/*
			 * 后期写
			 */
			// 首次注册将用户的激活码设定为0，即未激活
			user.setState(0);
			// 首次注册为用户自动生成一个激活码，以便后期发邮件激活
			String code = UUID.randomUUID().toString().replace("-", "");
			user.setCode(code + code.replaceAll("a", "z"));
			// 注册用户
			registerResult = userService.saveUser(user);
		}
		if (registerResult) {
			// 发送激活码邮件
			mailInfo.setSubject("用户激活");
			mailInfo.setToAddress(user.getEmail());
			mailInfo.setContent("恭喜您注册成功！,请点击下面链接进行激活http://localhost:8080/shop/active.action?" + "username="
					+ user.getUsername() + "&code=" + user.getCode());
			MailSenderUtil.sendTextMail(mailInfo);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 用户激活
	public String active() throws Exception {
		boolean activeflag = false;
		if (user != null) {
			activeflag = userService.activeUser(user);
		}
		return SUCCESS;
	}

	//用户名校验
	public String checkUserName() throws Exception {
		boolean isExist = false;
		jsonMsg=new HashMap<>();
		if (user.getUsername() != null) {
			isExist = userService.checkUserName(user.getUsername());
		}
		jsonMsg.put("isExist", isExist);
//		System.out.println(jsonMsg);
		return "json";
	}

	//用户登录
	public String login()throws Exception {
		//检验用户是否存在
		int userState=userService.checkUserInfo(user);
		String result="toLogin";
		switch (userState) {
		case 0:
			//用户名不存在
			ActionContext.getContext().put("errorInfo", "用户名不存在");
			break;
		case -1:
			//用户存在，密码错误
			ActionContext.getContext().put("errorInfo", "密码错误");
			break;
		case 1:
			//用户存在，密码正确，并且激活
			result="toHome";
			User loginUser=userService.findUserByUserName(user.getUsername());
			ActionContext.getContext().getSession().put("loginUser", loginUser);
			break;
		case 2:
			//用户存在，密码正确，但未激活
			ActionContext.getContext().put("errorInfo", "用户未激活");
			break;
		default:
			//登录异常
			ActionContext.getContext().put("errorInfo","未知异常");
			break;
		}
		
		return result;
	}
	
	
	//退出登录
	public String loginOut()throws Exception {
		ActionContext.getContext().getSession().remove("loginUser");
		return "toLogin";
	}
	
	@Override
	public User getModel() {
		return this.user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMailInfo(MailSenderInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	public Map<String, Object> getJsonMsg() {
		return jsonMsg;
	}


}
