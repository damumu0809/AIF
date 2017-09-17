package util;

import java.util.Properties;  

import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.Message.RecipientType;  
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
//邮件发送工具类 
  
public class MailUtils {  
    public static void sendMail(String to,String code){  
          
        /** 
         * 1.获取session 
         * 2.创建一个代码邮件的对象message 
         * 3.发送邮件Transport 
         */  
        /** 
         * 1.获得连接对象 
         */  
        Properties props=new Properties();  
     // 开启debug调试 
    //    props.setProperty("mail.debug", "true"); 
        // 发送服务器需要身份验证 
        props.setProperty("mail.smtp.auth", "true"); 
        // 设置邮件服务器主机名 
        props.setProperty("mail.host", "smtp.163.com"); 
        // 发送邮件协议名称 
        props.setProperty("mail.transport.protocol", "smtp"); 
        
        Session session=Session.getDefaultInstance(props, new Authenticator(){  
  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                // TODO Auto-generated method stub  
                return new PasswordAuthentication("18224026886@163.com","shouquanma163");  
            }  
              
        });  
        //2.创建邮件发送对象  
        Message message=new MimeMessage(session);  
        //3.设置发件人  
        try {  
            message.setFrom(new InternetAddress("18224026886@163.com"));  
            //设置收件人  
            message.addRecipient(RecipientType.TO, new InternetAddress(to));  
            //标题  
            message.setSubject("农户金平台注册验证码");  
            message.setContent("您好，欢迎注册农互金平台！您本次验证码为"+ code, "text/html;charset=UTF-8");  
            // 3.发送邮件:  
            Transport.send(message);  
        } catch (AddressException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (MessagingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  
