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
	
	//验证码
	private String code;
	//保持登陆状态
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

	//注册发送验证码
	public void sendCode() throws IOException {
		System.out.println("userAction sendCode");
		
		//返回数据
		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=UTF-8"); 
				
		//调用业务层的类
		String result =  userService.sendCode(user);
		System.out.println(result);
		if(result.equals("0")) {
			//已注册
			response.getWriter().write("该邮箱已注册！");
			
		} else{
			//将验证码和邮箱存入session
			ActionContext.getContext().getSession().put("email", user.getEmail());
			ActionContext.getContext().getSession().put("code", result);
			
			response.getWriter().write("已发送验证码至邮箱，请及时查收");			
		}
	}

	//注册
	public String register() {
		System.out.println("userAction register");
		
		//判断邮箱和验证码
		String emailString = (String) ActionContext.getContext().getSession().get("email");
		String codeString = (String) ActionContext.getContext().getSession().get("code");
		ActionContext.getContext().getSession().remove("email");
		ActionContext.getContext().getSession().remove("code");
		System.out.println(emailString);
		System.out.println(codeString);
		System.out.println(user.getEmail());
		System.out.println(code);
		
		if(user.getEmail().equals(emailString) && code.equals(codeString)){
			//调用业务层
			if (userService.register(user) == 0){
				//记录email nickname到session
				System.out.println("注册成功");
				ActionContext.getContext().getSession().put("email", user.getEmail());
				ActionContext.getContext().getSession().put("nickname", user.getNickname());
				
				//跳转个人中心
				return "register_success";
			} else {
				addActionError("注册失败！");
				return "register_input";
			}
			
		} else {
			addActionError("验证码错误！");
			return "register_input";
		}
	}
	
	//登陆
	public String login() {
		System.out.println("userAction login");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//清除之前的session 和 Cookie 
		Cookie cookie = new Cookie("userCookie","none");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		ActionContext.getContext().getSession().remove("email");
		ActionContext.getContext().getSession().remove("code");
		
		//调用业务层
		User exitUser = userService.findByEmailAndPasswd(user);
		if(exitUser == null) {
			this.addActionError("用户名或密码错误！");
			return "login_input";
		} else {
			System.out.println(exitUser);
			//保持登陆状态
			if(stayIn.equals("stayin")) {
				
				//新建一个Cookie，存放用户名和密码，用&做分隔符
	            cookie = new Cookie("userCookie", exitUser.getEmail()+"&"+exitUser.getNickname());
	            //设置Cookie的存活时间（这里是两周）
	            cookie.setMaxAge(2*7*24*60*60);
	            //添加Cookie
	            response.addCookie(cookie);
			} 
			
			//邮箱和用户名存入session
			ActionContext.getContext().getSession().put("email", exitUser.getEmail());
			ActionContext.getContext().getSession().put("nickname", exitUser.getNickname());
			//跳转到主页
			return "login_success";
		}
	}
	
	//重置密码发送验证码
		public void sendCodeReset() throws IOException {
			System.out.println("userAction sendCodeReset");
			
			//返回数据
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setContentType("text/html;charset=UTF-8"); 
					
			//调用业务层的类
			String result =  userService.sendCode(user);
			System.out.println(result);
			if(result.equals("0")) {
				//未注册
				response.getWriter().write("请先注册！");
				
			} else{
				//将验证码和邮箱存入session
				ActionContext.getContext().getSession().put("email", user.getEmail());
				ActionContext.getContext().getSession().put("code", result);
				
				response.getWriter().write("已发送验证码至邮箱，请及时查收");			
			}
		}
	
	//重置密码
	public String resetPasswd() {
		System.out.println("userAction resetPasswd");
		
		//判断邮箱和验证码
				String emailString = (String) ActionContext.getContext().getSession().get("email");
				String codeString = (String) ActionContext.getContext().getSession().get("code");
				ActionContext.getContext().getSession().remove("email");
				ActionContext.getContext().getSession().remove("code");
				System.out.println(emailString);
				System.out.println(codeString);
				System.out.println(user.getEmail());
				System.out.println(code);
				
				if(user.getEmail().equals(emailString) && code.equals(codeString)){
					//调用业务层
					if (userService.resetPasswd(user) == 0){
						//记录email nickname到session
						System.out.println("修改成功");
											
						//跳转登陆页面
						return "resetPasswd_sucess";
					} else {
						addActionError("修改失败！");
						return "resetPasswd_input";
					}
					
				} else {
					addActionError("验证码错误！");
					return "resetPasswd_input";
				}
	}
}
