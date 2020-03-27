package thread.room;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//상속은 단일 이지만 인터페이스는 여러개로 할 수 있따.
public class TalkServer extends JFrame implements Runnable {
	TalkServerThread 			tst 		= null;
	List<TalkServerThread> 	globalList 	= null;
	ServerSocket 				server 		= null;
	Socket 							socket 		= null;
	JTextArea 						jta_log = new JTextArea(10,30);
	JScrollPane 					jsp_log = new JScrollPane(jta_log
																				,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
																				,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel							jp_north = new JPanel();
	JButton 						jbtn_log = new JButton("로그저장");
	String 							logPath="src\\thread\\talk\\";
	
	public void initDisplay() {
		//이미 JFrame을 상속 했고 , 인터페이스는 다중상속이 가능하지만 Runnable을 상속했다.
		//로그기록을 처리하기 위해 익명클래스로 jbtn_log버튼에 ActionLister를 처리한다.
		jbtn_log.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj ==jbtn_log) {//로그 저장 버튼을 누른거니?
					String fileName = "log_"+setTimer()+".txt"; 
					System.out.println(fileName);
					try {
						//자바는 모든 기능 사물들을 클래스로 설계하도록 유도한다.
						//파일명을 클래스로 만들어주는 API가 있다 -File
						File f = new File(logPath+fileName);
						//파일명만 생성될 뿐 내용까지 만들어 주지는 않는다.
						//내용 부분을 담는 별도의 클래스가 필요하다.
						PrintWriter pw = new PrintWriter(
												  new BufferedWriter( //필터클래스 - 카메라 필터를 생각하면 된다.
												  new FileWriter(f.getAbsolutePath()))); //절대경로
						//io패키지에는 단독으로 파일을 컨트롤할 수 있는 클래스(printWriter)가 있고
						//그 클래스에 연결해서 사용하는 필터 클래스(BufferedWriter)가 존재함.(기능을 향상해줌)
						pw.write(jta_log.getText());
						pw.close(); //사용한 입출력 클래스는 반드시 닫아준다 (열려있으면 보안문제가 생길 수 있으므로 close를 해준다)
					} catch (Exception e2) {
						e2.toString(); 
					}
				}
				
			}
		});
		
		
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));//속지 안에 버튼 추가하기
		jp_north.add(jbtn_log);
		jta_log.setBackground(Color.orange);
		this.add("North",jp_north);
		this.add("Center",jsp_log);
		this.setSize(500, 400);
		this.setVisible(true);
	
		
	}
	//서버소켓과 클라이언트측 소켓을 연결하기
	@Override
	public void run() {
		//서버에 접속해온 클라이언트 스레드 정보를 관리할 벡터 생성하기 
		globalList = new Vector<>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3000); //손님이 들어올 대문번호 3000번
			jta_log.append("Server Ready.........\n");
			while(!isStop) { //프로그램을 종료 할 때 까지 무한루프
				socket = server.accept(); //접속한 클라이언트를 받는다
				jta_log.append("client info:"+socket+"\n"); //접속해온 클라이언트의 정보를 로그창에 출력	
				TalkServerThread tst = new TalkServerThread(this); // TalkeServerThread를 TalkServer에 객체 주입해서 TalkServer에 주소번지를 넘겨준다
				tst.start(); //TalkServerThread는 Thread를 상속받았다 tst.start
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		Thread th = new Thread(ts);
		th.start();
	}


	/***************************************************************
	 * 로그저장 버튼을 누르면 연,월,일을 불러와서 파일명으로 텍스트 파일을 만든다.
	 *log_2020-02-13.txt
	 ***************************************************************/

	//연,월,일을 세팅해서 리턴해주는 함수
	public String setTimer() {
		Calendar cal = Calendar.getInstance();
		int yyyy = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		//삼항연산자로 연도,월,일이 10보다 작냐?  그러면 0을 붙여준다 exam) 2020-03-09 
		
		return (yyyy < 10 ? "0"+yyyy:""+yyyy)+"-"+
				   (month < 10 ? "0"+month:""+month)+"-"+
				   (day < 10 ? "0"+day:""+day);
	}

}
