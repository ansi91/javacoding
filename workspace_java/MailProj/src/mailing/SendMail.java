package mailing;
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
public class SendMail {
	public static void main(String[] args) {
		String smtpServer = "smtp.naver.com";
		int smtpPort =465;
		final String sendId = "balgoon24";
		final String sendPass = "!3ahffkdy";
		String sendEmailAddress = sendId+"@naver.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.smtp.ssl.trust", smtpServer);
		String receiveEmailAddress = "chii4617@gmail.com";
		String subject = "안녕하세요. 이순신입니다.";
		String content = "학습용 이메일보내기 연습입니다.";
		Session session5 = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(sendId, sendPass);
			}
		});
		try{
			Message mimeMessage = new MimeMessage(session5);
			mimeMessage.setFrom(new InternetAddress(sendEmailAddress));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(content);
			Transport.send(mimeMessage);
			System.out.print("메일이 전송됐습니다."); 
		} catch (AddressException e) { 
			e.printStackTrace(); 
		} catch (MessagingException e) { 
			e.printStackTrace(); 
		} 
	}
}
