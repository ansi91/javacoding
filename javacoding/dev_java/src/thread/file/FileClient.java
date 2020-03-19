package thread.file;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class FileClient extends JFrame  {
	
	Socket 							socket 		= null;
	JTextArea 						jta_log = new JTextArea(10,30);
	JScrollPane 					jsp_log = new JScrollPane(jta_log
																				,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
																				,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public void initDisplay() {
		
	this.setTitle("★★★★★클라이언트★★★★★");
	this.add("Center",jsp_log);
	this.setSize(500, 400);
	this.setVisible(true);
	}
	
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.7",3000);  //ip주소, port번호
			
			//서버에 말을 한 후 들을 준비를 한다.
			FileClientThread tct = new FileClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString());
		}
	}
	
	
	public static void main(String[] args) {
		FileClient fc = new FileClient();
		fc.initDisplay();
		fc.init();
	
	}
}
