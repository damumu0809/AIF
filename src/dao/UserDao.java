package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.User;

public class UserDao extends HibernateDaoSupport {


	public boolean checkEmail(User user) {
		// ��ѯ��email�Ƿ���ע��
		System.out.println("userDao checkEmail");
	//	System.out.println(user.getEmail());
		String hql = "from User where email = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getEmail());
		if(list.size()>0) {
			//��ע��
			return false;
		} else {
			//δע��
			return true;
		}
	}

	public void register(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao register");
		//��ӵ����ݿ�
		this.getHibernateTemplate().save(user);
	}

	public User findByEmailAndPasswd(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao findByEmailAndPasswd");
		
		//��ѯ���ݿ�
		String hql = "from User where email = ? and passwd = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getEmail(),user.getPasswd());
		if(list.size()>0) {
			//����
			return list.get(0);
		} else {
			//�û������������
			return null;
		}
	}

	public void resetPasswd(User user) {
		// TODO Auto-generated method stub
		System.out.println("userDao resetPasswd");
		//���ݿ��޸�����
		this.getHibernateTemplate().update(user);
	}

}
