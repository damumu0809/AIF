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
		
		//����DAO�����
		boolean result = userDao.checkEmail(user);
		if(result == true) {
			//������֤��
			String str = Double.toString(Math.random()).substring(3, 9);
			System.out.println("������֤��" + str);
			
			//�����ʼ�
			MailUtils.sendMail(user.getEmail(), str);
			
			//����֤�뷵��
			return str;
			
		} else {
			//��ע��
			return "0";
		}
	}
	
	public String sendCodeReset(User user) {
		System.out.println("userService sendCodeRest");
		
		//����DAO�����
		boolean result = userDao.checkEmail(user);
		if(result == true) {
			return "0";
			
		} else {
			//��ע��
			
			//������֤��
			String str = Double.toString(Math.random()).substring(3, 9);
			System.out.println("������֤��" + str);
			
			//�����ʼ�
			MailUtils.sendMail(user.getEmail(), str);
			
			//����֤�뷵��
			return str;
		}
	}

	public int register(User user) {
		System.out.println("userService register");
		
		//�ж����ݸ�ʽ���˴�ʡ��
		
		//�������
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		System.out.println(md.toMd5(passwdString));
		user.setPasswd(md.toMd5(passwdString));
		
		//����DAO��
		userDao.register(user);
		
		return 0;
	}


	public User findByEmailAndPasswd(User user ) {
		System.out.println("userService findByEmailAndPasswd");
		
		//��������м���
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		user.setPasswd(md.toMd5(passwdString));
		
		//����DAO��
		return userDao.findByEmailAndPasswd(user);
	}


	public int resetPasswd(User user) {
		// TODO Auto-generated method stub
System.out.println("userService resetPasswd");
		
		//�ж����ݸ�ʽ���˴�ʡ��
		
		//�������
		String passwdString = user.getPasswd();
		Mademd5 md = new Mademd5();
		System.out.println(md.toMd5(passwdString));
		user.setPasswd(md.toMd5(passwdString));
		
		//����DAO��
		userDao.resetPasswd(user);
		
		return 0;
	}	
	
	
}
