package division.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestView extends JFrame {
	//////////////////전역변수//////////////////
	TestSouth ts = new TestSouth();
	//TestSouth ts2 = new TestSouth();
	//ts를 넘기면 TestSouth만 누릴 수 있지만 this를 넘기면 TestView와 TestSouth 모두를 누를 수 있다.
	TestEvent te = new TestEvent(this);
	JPanel jp_north = new JPanel();
	JButton jbtn_change = new JButton("변경");
	
	/////////////////전역변수 끝////////////////
	
	///////////////////생성자///////////////////
	public TestView() {
		initDisplay();
	}
	
	//////////////////생성자 끝/////////////////
	
	//화면 처리
	public void initDisplay() {
		//코드의 가독성이 좋아짐.
	ts.jtf_msg.addActionListener(te);
	jbtn_change.addActionListener(te);
	jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
	jp_north.add(jbtn_change);
	this.setTitle("클래스 쪼개기");
	this.add("North",jp_north);
	this.add("South",ts.jtf_msg)	;
	this.setSize(500, 300);
	this.setVisible(true);
	}
	
	
	//메인 메소드
	public static void main(String[] args) {
		new TestView();
		
		
	}

}
