package design.book;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookApp extends JFrame implements ActionListener {
	static BookApp ba =null;
	JFrame jf_book = new JFrame();
	JPanel jp_north = new JPanel();
	JPanel jp_south = new JPanel();
	JButton jbtn_all = new JButton("전체 조회");
	JButton jbtn_sel = new JButton("상세조회");
	JButton jbtn_insert = new JButton("입력");
	JButton jbtn_update = new JButton("수정");
	JButton jbtn_delete = new JButton("삭제");
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER);
	String title=null;
	
	//파라미터가 없는 생성자는 디폴트로 지원해주지만 있는 경우는 예측불가이므로 지원 불가함
	//인스턴스화 하면 생성자 호출이 일어남
	BookDialog bd = new BookDialog();
	

	
	/*
	 * BookMain을 인스턴스화 할 때 전역변수에 선언된 BookDialog도 같이 인스턴스화 한다
	 * 이때 파라미터로 넘어간 Boolean,String은 값이 이미 결정된 상태이므로 아무리 버튼을 바꾸어도 title값을 변하지 않는다. 생성자와 타이틀 변경 타이밍이 맞지 않는다\
	 * 실제로 타임서버로 부터 시간정보를 듣기는 timeClient에서 진행되지만 생성자의 파라미터를 통해서 BookApp jlb_time의 
	 */
		TimeClient tc = null;
	
	
	public void initDisplay() {
		//아래코드가 JFrame의 자원을 회수함
		//부모자원이 회수될 때 JDialog도 같이 회수됨
		tc = new TimeClient(jlb_time);
		tc.start();
		this.setTitle("도서관리시스템");
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jp_north.add(jbtn_all);
		jp_north.add(jbtn_sel);
		jp_north.add(jbtn_insert);
		jp_north.add(jbtn_update);
		jp_north.add(jbtn_delete);
		jp_south.add(jlb_time);
		this.add("South",jp_south);
		this.add("North",jp_north);
		this.setSize(700, 700);
		this.setVisible(true);
		jbtn_all.addActionListener(this);
		jbtn_sel.addActionListener(this);
		jbtn_insert.addActionListener(this);
		jbtn_update.addActionListener(this);
		jbtn_delete.addActionListener(this);
		
	}
	public void refreshData() {
	System.out.println("refreshData 호출성공");	
	}
	
	public static void main(String[] args) {
		TimeServer ts = new TimeServer();
		ts.initDisplay();//화면을 그리고 난 뒤 스레드 대기를 타도록 해야함.
		Thread th = new Thread(ts);
		th.start();//스레드의 run메소드를 호출하는 메소드
		ba = new BookApp();
		ba.initDisplay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	
		
	
		
		
		if(obj == jbtn_insert) {
			bd.jtf_title.setText("");
			bd.set(jbtn_insert.getText(),true,true,null,ba);
		}
		else if(obj == jbtn_update) {//수정시에는 먼저 기본정보를 조회하고 화면이동
			Map<String,Object> rMap = null;
			rMap = new HashMap<>();
			rMap.put("b_title","자바의 정석");				//select처리 한 후 한 개 로우를 받아서 Map에 저장
			bd.set(jbtn_update.getText(),true,true,rMap,ba);
			
			
		}else if(obj == jbtn_delete) {
			System.out.println("삭제 호출 성공");
			
		}else if (obj==jbtn_sel) {
			bd.set(jbtn_sel.getText(),true,false,null,null);
		}
	}

}
