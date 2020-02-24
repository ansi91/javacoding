package friday0207;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*이벤트 처리 순서
 *1.이벤트 소스를 지원하는 이벤트 처리 인터페이스를 implements 한다
 *선언만 되어있고 구현이 전혀 안되어 있는 메소드를 추상 메소드라 한다.
 *2.ActionListener가 선언하고 있는 추상메소드를 재정의 하기
 *3.jbtn_exit.addActionListener(this) 호출하여
 *  이벤트 소스와 이벤트 처리를 담당하는 클래스 연결하기
 */

public class BaseBallGame implements ActionListener{
	//화면과 관련된 코드 추가
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
	
	
	//화면과 관련된 코드 끝
	int com[] = new int[3];
	int my[] = new int[3];
	//디폴트 생성자 선언하기
	public BaseBallGame() {
		//생성자 안에서 메소드를 호출하면 인스턴스화 없이도 호출이 가능하다
		initDisplay();
	}
	
	//나가기 버튼 처리
	//메소드 중심의 코딩을 전개하시오. -재사용성을 높이는 코드를 작성하는 첫단계				
	public void exitGame() {
		System.exit(0);
	}
	//화면 처리하기
	public void initDisplay() {
		//이벤트 소스와 이벤트 처리 클래스 매핑
		jbtn_exit.addActionListener(this);
		jmi_exit.addActionListener(this);
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
	
	public void ranCom() {

		Random r = new Random();
		com[0] = r.nextInt(10);// 0.0~10.0;
		do {

			com[1] = r.nextInt(10);// 0.0~10.0;
		} while (com[0] == com[1]);

		do {
			com[2] = r.nextInt(10);// 0.0~10.0;

		} while ((com[0] == com[2]) || (com[1] == com[2]));
	}


	public String account(String user) {

		int temp = Integer.parseInt(user);
		my[0] = temp / 100; // 123
		my[1] = (temp % 100) / 10;
		my[2] = temp % 10;		
		int ball = 0, strike = 0;
		for (int i = 0; i < com.length; i++) {
			for (int j = 0; j < my.length; j++) {
				if (com[i] == my[j]) {// 내가 입력한 숫자 중에 컴퓨터에 그 숫자가 있니?
					if (i == j) {// 혹시 그 숫자가 자리도 일치 하는거야?
						strike++;
					} else {
						ball++;
					} // 스트라이크 확보
				} // 볼카운트 확보
			} // end of inner for
		} /// end of outter for
		if (strike == 3) {
			return "정답입니다. 축하합니다";
		}

		return strike + "스" + ball + "볼";
	}
	



	public static void main(String[] args) {

		BaseBallGame bbg = new BaseBallGame();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//버튼이 여러개니까 뭐가 눌러질지 몰라서 감지한 이벤트를 상위인 오브젝트로 받는다.
		//JVM이 감지한 이벤트 소스[jbtn_exit, jtf_input]로 부터 
		Object obj = e.getSource();
	
		//너 그만 할꺼야?		
		if(obj==jbtn_exit) {
			//자바가상머신과 이 어플하고의 연결고리를 끊음
			exitGame();
		}
		if(obj==jmi_exit) {
			//자바가상머신과 이 어플하고의 연결고리를 끊음
			exitGame();
		}
		
	}
}
