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
  
//�ʼ����͹����� 
  
public class MailUtils {  
    public static void sendMail(String to,String code){  
          
        /** 
         * 1.��ȡsession 
         * 2.����һ�������ʼ��Ķ���message 
         * 3.�����ʼ�Transport 
         */  
        /** 
         * 1.������Ӷ��� 
         */  
        Properties props=new Properties();  
     // ����debug���� 
    //    props.setProperty("mail.debug", "true"); 
        // ���ͷ�������Ҫ�����֤ 
        props.setProperty("mail.smtp.auth", "true"); 
        // �����ʼ������������� 
        props.setProperty("mail.host", "smtp.163.com"); 
        // �����ʼ�Э������ 
        props.setProperty("mail.transport.protocol", "smtp"); 
        
        Session session=Session.getDefaultInstance(props, new Authenticator(){  
  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                // TODO Auto-generated method stub  
                return new PasswordAuthentication("18224026886@163.com","shouquanma163");  
            }  
              
        });  
        //2.�����ʼ����Ͷ���  
        Message message=new MimeMessage(session);  
        //3.���÷�����  
        try {  
            message.setFrom(new InternetAddress("18224026886@163.com"));  
            //�����ռ���  
            message.addRecipient(RecipientType.TO, new InternetAddress(to));  
            //����  
            message.setSubject("ũ����ƽ̨ע����֤��");  
            message.setContent("���ã���ӭע��ũ����ƽ̨����������֤��Ϊ"+ code, "text/html;charset=UTF-8");  
            // 3.�����ʼ�:  
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
