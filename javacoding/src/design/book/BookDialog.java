package design.book;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BookDialog extends JDialog implements ActionListener{
	/*
	 * 자녀창에서 저장(입력) 성공했을때 부모창의 refreshData를 호출해야 한다
	 * 그런데 원본을 재사용 해야 하므로 set메소드의 파라미터로 받아서 사용할 것이다.
	 * 다른 메소드에서 ba를 사용해야 하니까 전역변수로 선언한 다음 초기화를 반드시 할것.
	 */
	BookApp ba =null;
	boolean isView = false;
	String title = null;
	JLabel jlb_title = new JLabel("책제목");
	JTextField jtf_title = new JTextField(20);
	JPanel jp_south = new JPanel();
	JPanel jp_center = new JPanel();
	JButton jbtn_save = new JButton("저장");
	JButton jbtn_close = new JButton("닫기");
	JScrollPane jsp = new JScrollPane(jp_center);
	public BookDialog() {
		jbtn_close.addActionListener(this);
		jbtn_save.addActionListener(this);
		}
	public void setEditable(boolean isOk) {
		jtf_title.setEditable(isOk);
	}
/*****************************************************************************************************************
 * 
 * @param title 다이얼로그창 제목
 * @param isView 다이얼로그창 뷰 여부
 * @param editable 입력 컴포넌트 수정 여부
 * @param rMap 조회 결과를 담은 주소번지
 *******************************************************************************************************************/
	
	public void set(String title, boolean isView,boolean editable,Map<String,Object> rMap, BookApp ba) {
		this.ba = ba;
		setValue(rMap);
		setEditable(editable);
		this.setTitle(title);
		initDisplay();
		this.setVisible(isView);
//		if(rMap!=null) {
//		jtf_title.setText(rMap.get("b_title").toString());	
//	}
	}
	public void setValue(Map<String,Object>rmap) {
		//입력을 위한 화면 설정 - 모든 값을 빈 문자열로 셋팅한다
			if(rmap == null) {
				setB_title("");
			}
		//상세조회와 수정시는 파라미터로 받은 값으로 셋팅한다
			else {
				setB_title(rmap.get("b_title").toString());
			}
		
	}
	
	//각 컬럼의 값들을 설정하거나 읽어오는 getter/setter 메소드입니다.
	public String getB_title() { 	return jtf_title.getText();}
	public void setB_title(String title) { jtf_title.setText(title); }
	

	//입력과 수정시에는 컬럼값을 수정 가능하도록 하고
	//조회시에는 불가능하게 하는 메소드를 선언해 보자
	public void initDisplay() {
		
		jp_center.setLayout(null);
		jp_center.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_close);
		jlb_title.setBounds(20,20,100,20);
		jtf_title.setBounds(120, 20, 150, 20);
		jp_center.add(jlb_title);
		jp_center.add(jtf_title);
		this.setSize(500, 500);
		this.add("Center",jsp);
		this.add("South",jp_south);
	
		
		//부모창에서 선택한 버튼에 따라 화면을 제어한다 -변수
//		BookDialog bd = new BookDialog();
//		bd.isView = true;

		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); //이벤트 소스의 라벨
//		JOptionPane.showMessageDialog(ba,"이벤트 소스 라벨:" + command);
		//저장 버튼을 누른거니?
		if("저장".equals(command)) {
			this.dispose();
		//insert here - 입력인지 수정인지 어떻게 구분하지? 
			if(title.equals("입력")) { 
				
			}
			
			ba.refreshData();
		}else if("닫기".equals(command)) {
			this.dispose();
		}
		
	}
	
}
