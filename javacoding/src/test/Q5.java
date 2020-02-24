package test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Q5 {
	public static void main(String[] args) {
		String smtpServer = "smtp.naver.com";
		int smtpPort =465;
//		보내는 사람 정보
		final String sendId = "balgoon24";//네이버 id
		final String sendPass = "!3ahffkdy";//네이버 pw
		String sendEmailAddress = sendId+"@naver.com";//메일 주소
//		System.out.println(sendEmailAddress);
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.smtp.ssl.trust", smtpServer);
//		받는 사람 정보
		String receiveEmailAddress = "chii4617@gmail.com";
		String subject = "안녕하세요. 이순신입니다.";
		String content = "학습용 이메일보내기 연습입니다.";
		
		//아이디와 비밀번호로 인증검사하기
		Session session5 = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(sendId, sendPass);
			}
		});
		//session2.setDebug(true);
		try{
			Message mimeMessage = new MimeMessage(session5);
			mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(content);
			Transport.send(mimeMessage);
			System.out.print("message sent successfully..."); 	
		} catch (AddressException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (MessagingException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	}
}
