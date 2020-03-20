package thread.file;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import thread.talk2.TalkServerThread;

public class FileServer extends JFrame implements Runnable{
	ServerSocket 				server 		= null; //경합이 벌어짐 서버는 하나인데  접속해오는 사람이 계속 생김
	Socket 							socket 		= null;// 그 순간에는 하나	손님을 받는 시점은 하나이지만 다른손님이 오고 전체적으로 보면 여러명이다
	List<FileServerThread> globalList = null;
	//Map<String, FileServerThread> map = new HashMap<>();
	JTextArea 						jta_log = new JTextArea(10,30);
	JScrollPane 					jsp_log = new JScrollPane(jta_log
																				,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
																				,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	public FileServer() {}
		
	public void initDisplay() {
		this.setTitle("★★★★★서버로그★★★★★");
		this.add("Center",jsp_log);
		this.setSize(500, 400);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		FileServer fs = new FileServer();
		fs.initDisplay();
		Thread th = new Thread(fs);
		th.start();
	}


	@Override
	public void run() {
		//서버에 접속해온 클라이언트 스레드 정보를 관리할 벡터 생성하기 
		globalList = new Vector<>(); // 손님을 관리 하기 위한 벡터
		boolean isStop = false;
		try {
			server = new ServerSocket(3000); //손님이 들어올 대문번호 3000번
			jta_log.append("Server Ready.........\n");
			while(!isStop) { //프로그램을 종료 할 때 까지 무한루프
				socket = server.accept(); //접속한 클라이언트를 받는다
				jta_log.append("client info:"+socket+"\n"); //접속해온 클라이언트의 정보를 로그창에 출력
				//생성자 호출이 먼저야? run메소드 호출이 먼저야?[듣고 말하기]
				FileServerThread tst = new FileServerThread(this); // TalkeServerThread를 TalkServer에 객체 주입해서 TalkServer에 주소번지를 넘겨준다
				tst.start(); //TalkServerThread는 Thread를 상속받았다 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
