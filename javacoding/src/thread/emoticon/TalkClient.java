package thread.emoticon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import jdk.nashorn.internal.scripts.JO;

public class TalkClient extends JFrame implements ActionListener {
	////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null;//말 하고 싶을 때
	ObjectInputStream 	ois		= null;//듣기 할 때
	LoginForm lf = null; //원본 사용
	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_emot	  = new JButton("이모티콘");
	JButton jbtn_create	  = new JButton("방생성");
	
	JButton jbtn_one	  = new JButton("1:1");
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("나가기");
	String cols[] 		  = {"대화명"};
	String data[][] 	  = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable			  jtb = new JTable(dtm);
	JScrollPane       jsp = new JScrollPane(jtb);
	JPanel jp_first 		= new JPanel();
	JPanel jp_first_south 	= new JPanel();
	JTextField jtf_msg = new JTextField(20);//south속지 center
	JButton jbtn_send  = new JButton("전송");//south속지 east
	//글꼴적용이나 스타일 추가를 하려면 스타일 클래스를 매핑해야함
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
	JTextPane jtp_display = new JTextPane(sd_display);
	JScrollPane jsp_display = new JScrollPane(jtp_display);
	Image back = null;
	String afterName=null;
	String message_one = null;
	String fontColor = "0";
	//이모티콘을 선택하자 마자 메시지 전송 처리 하려면 TalkClient에 주소번지를 넘겨야한다
	EmoticonView emov = new EmoticonView(this);
	
	
		//배경 이미지에 사용될 객체 선언 JTextArea에 페인팅
	public TalkClient() {
	
	}
	
	public TalkClient(LoginForm lf) {
		jbtn_create.addActionListener(this);
		jbtn_emot.addActionListener(this);
		jbtn_font.addActionListener(this);
		jtf_msg.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_one.addActionListener(this);
		this.lf = lf;
		initDisplay();
		init(); //서버소켓에 연결하기
	}
	public void initDisplay() {
		//사용자의 닉네임 받기
//		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요."); //닉네임을 입력 받는다 [전역변수]
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(3,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_emot);
		
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
//	
		Font font = new Font("굴림",Font.BOLD,25);
		jtp_display.setFont(font);
		//jtp_display.setLineWrap(true);
		jtp_display.setOpaque(false);
	
		
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setSize(800, 550);
		
		this.setVisible(true);
	}
	/**********************************************************
	 * 일반 메시지를 전송 구현
	 * @param msg 사용자가 입력한 메시지를 넘길 때
	 * @param imgChoice EmoticonView에서 이모티콘을 선택 했을 때 이미지 정보
	 */
	
	
	public void message_process(String msg, String imgChoice) {
		if(imgChoice!=null) {//이모티콘을 선택했다 lion11.png
			msg="이모티콘";
			try {
				oos.writeObject(201
						   +"#"+lf.nickName
						   +"#"+msg
				           +"#"+fontColor
						   +"#"+emov.imgChoice);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		else {//일반메시지를 입력했다.
			try {
				oos.writeObject(201
						   +"#"+lf.nickName
						   +"#"+msg
				           +"#"+fontColor
						   +"#"+"default");
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}				
		}
	}/////////////////////end of message_process/////////////////
	
	
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		TalkClient tc = new TalkClient();
		tc.initDisplay();
		tc.init();
	}
	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.7",3000);  //ip주소, port번호
			oos = new ObjectOutputStream(socket.getOutputStream()); 
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
			//서버에게 내가 입장한 사실을 알린다.(말하기)
			this.setTitle(lf.nickName+"님의 대화창");
			oos.writeObject(Protocol.LOGIN+"#"+lf.nickName);
			
			//서버에 말을 한 후 들을 준비를 한다.
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();  //입력한
	
			 if(jbtn_font == obj) {
			JDialog jdl_color = new JDialog();
			jdl_color.setSize(600, 500);
			JColorChooser jcc = new JColorChooser();
			ColorSelectionModel csm = jcc.getSelectionModel();
			ChangeListener cl = new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					Color color = jcc.getColor();
					fontColor = String.valueOf(color.getRGB());
					
				}
				
			};
			csm.addChangeListener(cl);
			jdl_color.add(jcc);
			jdl_color.setVisible(true);
		}else if(obj==jbtn_exit) {
			try {
				oos.writeObject(500+"#"+this.lf.nickName);
				//자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else	if(jtf_msg==obj) {
			message_process(msg,null);
			
		}else if(obj==jbtn_change) {
			try {
			
				afterName=JOptionPane.showInputDialog("변경할 닉네임을 입력하세요");
				if(afterName==null||afterName.trim().length()<1) {
					JOptionPane.showMessageDialog(this, "변경할 대화명을 입력하세요","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				oos.writeObject(Protocol.CHANGE+"#"+lf.nickName+"#"+afterName+"#"+lf.nickName+"의 대화명이"+afterName+"변경 되었습니다.");
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(obj==jbtn_one) {
			int row = jtb.getSelectedRow();
			if(row == -1) { // -1 end of file이라는 뜻
				JOptionPane.showMessageDialog(this, "상대를 선택하세요");
				return; // actionPerformed 탈출
			}else { //상대가 다른 사람이 아닌 나 자신일 때는 배제 한다
				String name = (String)dtm.getValueAt(row, 0);
				if(lf.nickName.equals(name)) {
					JOptionPane.showMessageDialog(this, "자기 자신을 선택 했어요. 다른 사람을 선택하세요");
					return;
				}
				//메세지 입력받기
				message_one = JOptionPane.showInputDialog(name+"님 전송할 메세지를 입력하세요");
				try {
					oos.writeObject(Protocol.ONE+"#"+lf.nickName+"#"+name+"#"+message_one);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			jtb.clearSelection();
			System.out.println(message_one);
			
			
		}else if(jbtn_emot==obj) {
			emov.initDisplay();
		}
			
		}
	}//////////////////////end of actionPerformed








