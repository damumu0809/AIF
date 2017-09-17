package domain;

import java.sql.Blob;
import java.util.Date;



public class User {
	
	//ע�������Ϣ
	private int uid;	//�û�id
	private String nickname; //�û���
	private String email;	//�û�ע������
	private String passwd;	//�û�����
	
	//��ϸ��Ϣ
	private Blob photo;	//�û�ͷ��
	private String sex;	//�û��Ա�
	private Date birth; //�û����� ������
	private String address; //�û���ַ
	private String introduction; //���˼��
	
	//�����֤
	private boolean identified = false; //�Ƿ���֤ ����֤Ϊtrue
	private String uname; //��ʵ����
	private String idno;  //���֤��
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public boolean isIdentified() {
		return identified;
	}
	public void setIdentified(boolean identified) {
		this.identified = identified;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	
}
