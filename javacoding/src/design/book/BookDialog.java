package design.book;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDialog extends JDialog implements ActionListener {
/*
 * 자녀창에서 저장(입력) 성공했을때 부모창의 refreshData를 호출해야 한다.
 * 그런데 원본을 재사용해야 하므로 set메소드의 파라미터로 받아서 사용할 것이다.
 * 다른 메소드에서 ba를 사용해야 하니까 전역변수로 선언한 다음 초기화를 반드시 할것.	
 */
	BookApp ba = null;
	boolean isView = false;
	String title = null;//입력
	//인스턴스화를 하면 생성자 호출이 일어남.
	JLabel jlb_name = new JLabel("도서명");
	JTextField jtf_name = new JTextField(20);
	JLabel jlb_author = new JLabel("저자");
	JTextField jtf_author = new JTextField(20);
	JLabel jlb_publish = new JLabel("출판사");
	JTextField jtf_publish = new JTextField(20);
	JLabel jlb_info = new JLabel("책 정보");
	JTextArea jta_info = new JTextArea(8,25);
	JPanel jp_center = new JPanel();
	JPanel jp_south  = new JPanel();
	JButton jbtn_save 	= new JButton("저장");
	JButton jbtn_close 	= new JButton("닫기");
	JScrollPane jsp = new JScrollPane(jp_center);
	JScrollPane jsp_info = new JScrollPane(jta_info);
	BookVO rbVO=null;
	
	public BookDialog() {
		jbtn_save.addActionListener(this);
		jbtn_close.addActionListener(this);
	}
	//입력과 수정시에는 컬럼값을 수정 가능하도록 하고
	//조회시에는 불가능하게 하는 메소드를 선언해 봐요.
	public void setEditable(boolean isOk) {
		jtf_name.setEditable(isOk);
		jtf_author.setEditable(isOk);
		jtf_publish.setEditable(isOk);
		jta_info.setEditable(isOk);
	}
	/****************************************************************
	 * 
	 * @param title 다이얼로그창 제목
	 * @param isView 다이얼로그창 뷰 여부
	 * @param editable 입력 컴포넌트 수정 여부
	 * @param rMap 조회결과를 담은 주소번지
	 ***************************************************************/
	public void set(String title, boolean isView, boolean editable, Map<String,Object> rMap, BookApp ba)
	{
		this.ba = ba;
		setValue(rMap);
		setEditable(editable);
		this.setTitle(title);
		initDisplay();
		this.setVisible(isView);
	}	
	public void set(String title, boolean isView
			      , boolean editable, BookVO rbVO, BookApp ba) {
		this.ba = ba;
		this.rbVO = rbVO;
		setValue(rbVO);
		setEditable(editable);
		this.setTitle(title);
		initDisplay();
		this.setVisible(isView);
	}
	/********************************************************************************
	 * BookApp에서 조회된 한 건을 BookDialog에 초기화함.
	 * @param rmap 조회된 한 건을 Map으로 받은 경우
	 * 
	 **********************************************************************************/
	public void setValue(Map<String,Object> rmap) {
	//입력을 위한 화면 설정 - 모든값을 빈문자열로 셋팅한다.
		if(rmap == null) {
			setB_name("");
			setB_author("");
			setB_publish("");
			setB_info("");
		}
	//상세조회와 수정시는  파라미터로 받은 값으로 셋팅한다.
	//처음 설계시에는 맵으로 했던걸 어제 bVO로 추가 처리함
		else {
			setB_name(rmap.get("b_name").toString());
			setB_author(rmap.get("b_author").toString());
			setB_publish(rmap.get("b_publish").toString());
			setB_info(rmap.get("b_info").toString());
		}
	}	
	//조회된 결과를 BookDialog에 초기화 하기
	//새로 입력하는 경우에는 빈 문자열로 초기화 하기
	
	
	/********************************************************************************
	 * BookApp에서 조회된 한 건을 BookDialog에 초기화함.
	 * @param rbVO 조회된 한 건을 VO패턴으로 받은 경우
	 * 
	 **********************************************************************************/
	private void setValue(BookVO rbVO) {
		//입력을 위한 화면 설정 - 모든값을 빈문자열로 셋팅한다.
		if(rbVO == null) {
			setB_name("");
			setB_author("");
			setB_publish("");
			setB_info("");
			
			
		}
	//상세조회와 수정시는  파라미터로 받은 값으로 셋팅한다.
		else {
			
			setB_name(rbVO.getB_name());
			setB_author(rbVO.getB_author());
			setB_publish(rbVO.getB_publish());
			setB_info(rbVO.getB_info());
		}
	}
	public void initDisplay() {
		//TextArea에 자동 줄바꿈 처리해보기
		jta_info.setLineWrap(true);
		//속지에 레이아웃이 FlowLayout이었던 것을 null로 초기화 함
		jp_center.setLayout(null);
		jp_south.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_close);
		//화면에 배치할때 setBounds(x좌표,y좌표,가로길이,세로길이) 앞에 두자리가 시작점 세번째, 네번째가 가로세로 결정  
		jlb_name.setBounds(20, 20, 100, 20);
		jtf_name.setBounds(120, 20, 150, 20);
		jlb_author.setBounds(20, 45, 100, 20);
		jtf_author.setBounds(120, 45, 150, 20);
		jlb_publish.setBounds(20, 70, 100, 20);
		jtf_publish.setBounds(120, 70, 150, 20);
		jlb_info.setBounds(20, 95, 100, 20);
		jsp_info.setBounds(120, 95, 300, 160);
		jp_center.add(jlb_name);
		jp_center.add(jtf_name);
		jp_center.add(jlb_author);
		jp_center.add(jtf_author);
		jp_center.add(jlb_publish);
		jp_center.add(jtf_publish);
		jp_center.add(jlb_info);
//		jp_center.add(jta_info);
		jp_center.add(jsp_info);
		this.add("Center",jsp);
	//	this.add("Center",jsp_info);
		this.add("South",jp_south);
		
		this.setSize(500, 450);
		//this.setVisible(true);
		//부모창에서 선택한 버튼에 따라 화면을 제어한다.- 변수
	}

	//각 컬럼의 값들을 설정하거나 읽어오는 getter/setter메소드 입니다.
	public String getB_name() {	return jtf_name.getText();}
	public void setB_name(String title) { jtf_name.setText(title);}
	public String getB_author() {	return jtf_author.getText();}
	public void setB_author(String author) { jtf_author.setText(author);}
	public String getB_publish() {	return jtf_publish.getText();}
	public void setB_publish(String publish) { jtf_publish.setText(publish);}
	public String getB_info() {	return jta_info.getText();}
	public void setB_info(String info) { jta_info.setText(info);}
	
//	public static void main(String[] args) {
//		BookDialog bd = new BookDialog();
//		bd.set("입력",true,true,HashMap<>(),null);
//		//bd.initDisplay();
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();//이벤트 소스의 라벨
		//JOptionPane.showMessageDialog(ba, "이벤트 소스 라벨:"+command);
		//저장버튼을 누른거니?
		if("저장".equals(command)) {
			//insert here - 입력 인지 수정인지 어떻게 구분하지?
			
			if(rbVO!=null) {//수정처리
				
			}else {//입력처리
				int result = 0;
				BookVO pbVO = new BookVO();
				pbVO.setB_name(getB_name()); //입력한 도서명 가져오기
				pbVO.setB_author(getB_author());//저자 이름
				pbVO.setB_publish(getB_publish());//출판사
				pbVO.setB_info(getB_info());	  //책 정보
				result = ba.bDao.bookInsert(pbVO);
				JOptionPane.showConfirmDialog(ba, "Result" +result);
			}
			
			ba.refreshData();
			this.dispose();
		}
		//닫기 버튼을 눌렀니?
		else if("닫기".equals(command)) {
			this.dispose();
		}
	}
}






