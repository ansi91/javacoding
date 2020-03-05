package thread.bank;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import design.book.TimeServerThread;
//인터페이스를 추가하면 반드시 구현체 클래스가 되기 위해서 추상메소드를 재정의 해야한다. - 규칙
//run 메소드를 반드시 오버라이딩을 해야한다.
//이 메소드는 무슨 기능을 할까? - 기다려[Thread.sleep(1000)], 듣기[ois.readObject()] 말하기[oos.writeObject("메세지")]
//스레드 클래스의 run메소드는 어떻게 호출하지?
public class ServerBank extends JFrame implements Runnable{
	/////////////////////////////전역변수 선언 //////////////////////////////////////////
	Socket socket = null;
	int port= 3000;
	ServerSocket server = null;	
	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log
			                             ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			                             ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	
///////////////////////////전역변수 선언 끝 //////////////////////////////////////////
	
	//메인메소드는 entry point이다, 메인 스레드 라고도 한다 (경합이 벌어진다)
	//화면처리와 서버 개통하기
	public static void main(String[] args) {
		ServerBank sb = new ServerBank();
		//sb.start(); error 왜냐하면 Thread를 상속받지 않았으니까 - 나는 스레드가 아님. 불법
		//해결방법 -> Thread를 인스턴스화 하고 생성자에 구현체 클래스를 넣어준다.
		sb.initDisplay();
		Thread th = new Thread(sb);
		th.start();
		
	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(this, "run호출 성공-스레드 가동 중");
		try {
			server = new ServerSocket(port);//가게 문 열고 기다리는 중....손님이 언제 올까(ip,port)
		} catch (Exception e) {
			e.printStackTrace();
		}//////////////end of try
		jta_log.append("Server Bank started successfully...\n");
		while(true) {//무한루프 - while탈출 불가
			try {
		//클라이언트측에서 접속해온 정보를 client소켓에게 넘김.		
				socket = server.accept();//대기
				jta_log.append("New Client connected...."+socket.toString()+"\n");
				//tst = new TimeServerThread(this);
				//	tst.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}
	
	public void initDisplay() {
		this.setTitle("ServerBank 로그창");
		this.add("Center",jsp_log);
		this.setSize(500, 400);
		this.setVisible(true);
	}

}
