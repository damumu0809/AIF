package service;

import org.springframework.transaction.annotation.Transactional;

import util.MailUtils;

import com.ndktools.javamd5.Mademd5;
import com.opensymphony.xwork2.ActionContext;

import dao.UserDao;
import domain.User;

@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public String sendCode(User user) {
		System.out.println("userService sendCode");
		
		//调用DAO层的类
		boolean result = userDao.checkEmail(user);
		if(result == true) {
			//生成验证码
			String str = Double.toString(Math.random()).substring(3, 9);
			System.out.println("生成验证码" + str);
			
			//发送邮件
			MailUtils.sendMail(user.getEmail(), str);
			
			//将验证码返回
			return str;
			
		} else {
			//已注册
			return "0";
		}
	}
	
	public String sendCodeReset(User user) {
		System.out.println("userService sendCodeRest");
		
		//调用DAO层的类
		boolean result = userDao.checkEmail(user);
		if(result == true) {
			return "0";
			
		} else {
			//已注册
			
			//生成验证码
			String str = Double.toString(Math.random()).substring(3, 9);
			System.out.println("生成验证码" + str);
			
			//发送邮件
			MailUtils.sendMail(user.getEmail(), str);
			
			//将验证码返回
			return str;
		}
	}

	public int register(User user) {
		System.out.println("userService register");
		
		//判断数据格式，此处省略
		
		//密码加密
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		System.out.println(md.toMd5(passwdString));
		user.setPasswd(md.toMd5(passwdString));
		
		//调用DAO层
		userDao.register(user);
		
		return 0;
	}


	public User findByEmailAndPasswd(User user ) {
		System.out.println("userService findByEmailAndPasswd");
		
		//对密码进行加密
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		user.setPasswd(md.toMd5(passwdString));
		
		//调用DAO层
		return userDao.findByEmailAndPasswd(user);
	}


	public int resetPasswd(User user) {
		// TODO Auto-generated method stub
System.out.println("userService resetPasswd");
		
		//判断数据格式，此处省略
		
		//密码加密
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		System.out.println(md.toMd5(passwdString));
		user.setPasswd(md.toMd5(passwdString));
		
		//调用DAO层
		userDao.resetPasswd(user);
		
		return 0;
	}	
	
	
}
