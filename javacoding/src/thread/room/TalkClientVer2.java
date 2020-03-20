package thread.room;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import thread.room.TalkClientThread;
//나는 oos와 ois를 어느 클래스에 생성해야 하는지 전혀 감이 없다. ->TalkClient, TalkServerThread
//Login처리는 LoginForm에서 진행되므로 TalkClientVer2에서 생성하면 될 것이다.
public class TalkClientVer2 extends JFrame {
	JTabbedPane 					jtp 	= new JTabbedPane();
	//JPanel 								wr 	= new JPanel(); //대기자 방
	WaitRoom 						wr 	= new WaitRoom(this); //oos, ois 때문에 this를 넘긴다
	MessageRoom				 	mr 	= new MessageRoom(this);
	SettingRoom						sr  	= new SettingRoom(this);
	//JPanel 								mr 	= new JPanel(); //단톡 방
	
	Socket		    					mySocket = null;
	ObjectInputStream 			ois	=null;
	ObjectOutputStream 		oos	= null;
	final static String _IP	  =	"192.168.0.7";
	final static int 	   _PORT =5001;  //0~65575포트 사용가능 / 1000번대는 운영체제에서 사용
	//대화명을 담는 변수
	String nickName = null;
	LoginForm lf = null;
	
	public TalkClientVer2() {
	}
	
	public TalkClientVer2(LoginForm lf) {
		this.lf = lf;
		nickName = lf.nickName; //로그인 화면에서 결정된 대화명으로 동기화.
		initDisplay();
		
		//화면 처리후 서버 소켓 접속하기
		connect_process();
	}
	
	public void connect_process() {
		this.setTitle(nickName+"님의 대화창");
		try { //네트워크 통신을 해야 하니까 에러처리를 위해 사용
			mySocket = new Socket(_IP,_PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new  ObjectInputStream(mySocket.getInputStream());
			oos.writeObject(Protocol.WAIT+"#"+nickName+"#"+"대기");
			
			//말하고 듣기
			//내가 한 말도 서버를 경유하여 듣는다(Thread run) -꼭 기억할 것.
			TalkClientThread tct = new TalkClientThread(this);
			
		} catch (Exception e) {

		}
	}

	public void initDisplay() {
		this.getContentPane().setLayout(null); //기본은 BorderLayout
		jtp.addTab("대기실", wr);
		jtp.addTab("단톡방", mr);
		jtp.addTab("설정", sr);
		
		
		this.getContentPane().setBackground(new Color(158,217,164));
		jtp.setBounds(5 ,4 ,620 ,530);
		this.getContentPane().add(jtp);
		this.setTitle("단톡방Ver2.0");
		this.setSize(650, 580);
		this.setVisible(true);
		//jtp.setSelectedIndex(1);
	}
	public static void main(String[] args) {
		TalkClientVer2 tc = new TalkClientVer2(new LoginForm());
		
	}

}
