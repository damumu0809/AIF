package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.User;

public class UserDao extends HibernateDaoSupport {


	public boolean checkEmail(User user) {
		// 查询该email是否已注册
		System.out.println("userDao checkEmail");
	//	System.out.println(user.getEmail());
		String hql = "from User where email = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getEmail());
		if(list.size()>0) {
			//已注册
			return false;
		} else {
			//未注册
			return true;
		}
	}

	public void register(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao register");
		//添加到数据库
		this.getHibernateTemplate().save(user);
	}

	public User findByEmailAndPasswd(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao findByEmailAndPasswd");
		
		//查询数据库
		String hql = "from User where email = ? and passwd = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getEmail(),user.getPasswd());
		if(list.size()>0) {
			//存在
			return list.get(0);
		} else {
			//用户名或密码错误
			return null;
		}
	}

	public void resetPasswd(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao resetPasswd");
		//数据库修改密码
		this.getHibernateTemplate().update(user);
	}

}
