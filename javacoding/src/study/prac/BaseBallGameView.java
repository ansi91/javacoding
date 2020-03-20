package study.prac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseBallGameView {
	JFrame  	  jf_bbgame 	 = new JFrame();
	//JMenuBar는 JFrame안에 메뉴바를 추가하기
	JMenuBar jmb_bbgame = new JMenuBar();
	//JMenu JMenuBar안에 들어갈 메뉴 추가하기
	JMenu 	  jm_game		 = new JMenu("게임");
	//JMenuItem은 Jmenu아래에 들어갈 메뉴내용들...
	JMenuItem jmi_next 	 = new JMenuItem("다음겜");
	JMenuItem jmi_clear 	 = new JMenuItem("지우기");
	JMenuItem jmi_dap 	 = new JMenuItem("정답");
	JMenuItem jmi_exit 	 = new JMenuItem("나가기");
	JMenu 	  jm_info		 = new JMenu("도움말");
	JTextArea jta_display    = new JTextArea("");
	JScrollPane jsp_display = new JScrollPane(jta_display);
	JTextField jtf_input		 = new JTextField();
	JButton jbtn_next 		 = new JButton("다음겜");
	JButton jbtn_clear 		 = new JButton("지우기");
	JButton jbtn_dap 		 = new JButton("정 답");
	JButton jbtn_exit 		 = new JButton("나가기");
	//JTextArea와 JTextField를 붙일 속지를 추가하기
	JPanel 	jp_center 		= new JPanel();
	//버튼 4개를 붙일 속지 추가하기
	JPanel 	jp_east 		= new JPanel();
	BaseBallGameLogic bbLogic = new BaseBallGameLogic(); //
	
	public BaseBallGameView() {
		//생성자 안에서 메소드를 호출하면 인스턴스화 없이도 호출이 가능하다
		bbLogic.ranCom();
		initDisplay();
	}
	
	///////////////////////////화면처리 시작//////////////////////////////////////
	public void initDisplay() {
		//이벤트 소스와 이벤트 처리 클래스 매핑
	/*이전 버전에서는  friday0131소스 이벤트 처리를 직접 하였다. ActionListener를 했다.
	 * 디폴트 생성자는 jvm이 만들어 줄 수 있어야 왜냐하면 파라미터가 없기 때문이죠
	 * 파라미터가 있는 생성자는 내가 대신해 줄 수 없다. 왜냐하면 내 생각을 JVM이 알 수 없으니까 
	 */BaseBallGameEvent bbEvent = new BaseBallGameEvent(this); //****
		jbtn_exit.addActionListener(bbEvent);
		jtf_input.addActionListener(bbEvent);
		jbtn_next.addActionListener(bbEvent);
		jmi_exit.addActionListener(bbEvent);
		jbtn_clear.addActionListener(bbEvent);
		jbtn_dap.addActionListener(bbEvent);
		jp_center.setLayout(new BorderLayout());
		jp_center.add("Center",jsp_display);
		jp_center.add("South",jtf_input);
		jp_east.setLayout(new GridLayout(4,1));
		jbtn_next.setBackground(Color.ORANGE);
		
		jbtn_clear.setBackground(Color.ORANGE);
		jbtn_dap.setBackground(Color.ORANGE);
		jbtn_exit.setBackground(Color.ORANGE);
		//jbtn_exit.setForeground(new Color(255,255,255));
		jp_east.add(jbtn_next);
		jp_east.add(jbtn_clear);
		jp_east.add(jbtn_dap);
		jp_east.add(jbtn_exit);
		jf_bbgame.add("Center",jp_center);
		jf_bbgame.add("East",jp_east);
		
		///메뉴바 추가 시작
		jm_game.add(jmi_next);
		jm_game.add(jmi_clear);
		jm_game.add(jmi_dap);
		jm_game.add(jmi_exit);
		
		jmb_bbgame.add(jm_game);
		jmb_bbgame.add(jm_info);
		jf_bbgame.setJMenuBar(jmb_bbgame);
		
		//메뉴바 끝
		jf_bbgame.setTitle("야구숫자게임");
		jf_bbgame.setSize(300, 200);
		jf_bbgame.setVisible(true);
	}
/////////////////////////화면처리 끝//////////////////////////////////////
	
	
	public static void main(String[] args) {
		  new BaseBallGameView();

	}

}