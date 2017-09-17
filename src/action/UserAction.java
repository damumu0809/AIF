package action;

import java.io.IOException;
import java.util.Date;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.User;
import service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//��֤��
	private String code;
	//���ֵ�½״̬
	private String stayIn;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private UserService userService;
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//ע�ᷢ����֤��
	public void sendCode() throws IOException {
		System.out.println("userAction sendCode");
		
		//��������
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=UTF-8"); 
				
		//����ҵ������
		String result =  userService.sendCode(user);
		System.out.println(result);
		if(result.equals("0")) {
			//��ע��
			response.getWriter().write("��������ע�ᣡ");
			
		} else{
			//����֤����������session
			ActionContext.getContext().getSession().put("email", user.getEmail());
			ActionContext.getContext().getSession().put("code", result);
			
			response.getWriter().write("�ѷ�����֤�������䣬�뼰ʱ����");			
		}
	}

	//ע��
	public String register() {
		System.out.println("userAction register");
		
		//�ж��������֤��
		String emailString = (String) ActionContext.getContext().getSession().get("email");
		String codeString = (String) ActionContext.getContext().getSession().get("code");
		ActionContext.getContext().getSession().remove("email");
		ActionContext.getContext().getSession().remove("code");
		System.out.println(emailString);
		System.out.println(codeString);
		System.out.println(user.getEmail());
		System.out.println(code);
		
		if(user.getEmail().equals(emailString) && code.equals(codeString)){
			//����ҵ���
			if (userService.register(user) == 0){
				//��¼email nickname��session
				System.out.println("ע��ɹ�");
				ActionContext.getContext().getSession().put("email", user.getEmail());
				ActionContext.getContext().getSession().put("nickname", user.getNickname());
				
				//��ת��������
				return "register_success";
			} else {
				addActionError("ע��ʧ�ܣ�");
				return "register_input";
			}
			
		} else {
			addActionError("��֤�����");
			return "register_input";
		}
	}
	
	//��½
	public String login() {
		System.out.println("userAction login");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//���֮ǰ��session �� Cookie 
		Cookie cookie = new Cookie("userCookie","none");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		ActionContext.getContext().getSession().remove("email");
		ActionContext.getContext().getSession().remove("code");
		
		//����ҵ���
		User exitUser = userService.findByEmailAndPasswd(user);
		if(exitUser == null) {
			this.addActionError("�û������������");
			return "login_input";
		} else {
			System.out.println(exitUser);
			//���ֵ�½״̬
			if(stayIn.equals("stayin")) {
				
				//�½�һ��Cookie������û��������룬��&���ָ���
	            cookie = new Cookie("userCookie", exitUser.getEmail()+"&"+exitUser.getNickname());
	            //����Cookie�Ĵ��ʱ�䣨���������ܣ�
	            cookie.setMaxAge(2*7*24*60*60);
	            //���Cookie
	            response.addCookie(cookie);
			} 
			
			//������û�������session
			ActionContext.getContext().getSession().put("email", exitUser.getEmail());
			ActionContext.getContext().getSession().put("nickname", exitUser.getNickname());
			//��ת����ҳ
			return "login_success";
		}
	}
	
	//�������뷢����֤��
		public void sendCodeReset() throws IOException {
			System.out.println("userAction sendCodeReset");
			
			//��������
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setContentType("text/html;charset=UTF-8"); 
					
			//����ҵ������
			String result =  userService.sendCode(user);
			System.out.println(result);
			if(result.equals("0")) {
				//δע��
				response.getWriter().write("����ע�ᣡ");
				
			} else{
				//����֤����������session
				ActionContext.getContext().getSession().put("email", user.getEmail());
				ActionContext.getContext().getSession().put("code", result);
				
				response.getWriter().write("�ѷ�����֤�������䣬�뼰ʱ����");			
			}
		}
	
	//��������
	public String resetPasswd() {
		System.out.println("userAction resetPasswd");
		
		//�ж��������֤��
				String emailString = (String) ActionContext.getContext().getSession().get("email");
				String codeString = (String) ActionContext.getContext().getSession().get("code");
				ActionContext.getContext().getSession().remove("email");
				ActionContext.getContext().getSession().remove("code");
				System.out.println(emailString);
				System.out.println(codeString);
				System.out.println(user.getEmail());
				System.out.println(code);
				
				if(user.getEmail().equals(emailString) && code.equals(codeString)){
					//����ҵ���
					if (userService.resetPasswd(user) == 0){
						//��¼email nickname��session
						System.out.println("�޸ĳɹ�");
											
						//��ת��½ҳ��
						return "resetPasswd_sucess";
					} else {
						addActionError("�޸�ʧ�ܣ�");
						return "resetPasswd_input";
					}
					
				} else {
					addActionError("��֤�����");
					return "resetPasswd_input";
				}
	}
}
