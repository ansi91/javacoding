	package design.book;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;
//jbtn_sel.setToolTipText("상세보기)
public class BookApp extends JFrame implements ActionListener {
	//DB커넥션 연결하기
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	static BookApp ba =null;
	//이미지 경로 추가
	String imgPath = "src\\design\\book\\";
	URL bookURL = getClass().getResource("book.gif");
	ImageIcon bicon = new ImageIcon(bookURL);
	
	JFrame jf_book = new JFrame();
	JPanel jp_north = new JPanel();
	JPanel jp_south = new JPanel();
	JToolBar jtbar = new JToolBar();
	JButton jbtn_db = new JButton("DB연결");
	JButton jbtn_all = new JButton("전체 조회");
	JButton jbtn_sel = new JButton();
	JButton jbtn_insert = new JButton();
	JButton jbtn_update = new JButton();
	JButton jbtn_delete = new JButton();
	JLabel jlb_time = new JLabel("현재시간",JLabel.CENTER);
	JMenuBar 	jmb_book = 		new JMenuBar();
	JMenu 		 	jm_file 	  = 		new JMenu("File");
	JMenuItem 	jmi_open = 		new JMenuItem("Open File");
	JSeparator  js_file 		=      new JSeparator();
	JMenuItem  jmi_dbCon  =   new JMenuItem("Connect DB");
	JMenuItem 	jmi_exit = 		new JMenuItem("Exit");
	JMenu 		 	jm_edit	  = 		new JMenu("Edit");
	JMenuItem 	jmi_all = 			new JMenuItem("전체조회");
	JMenuItem 	jmi_sel = 			new JMenuItem("상세조회");
	JMenuItem 	jmi_insert = 		new JMenuItem("입력하기");
	JMenuItem 	jmi_update = 	new JMenuItem("수정하기");
	JMenuItem 	jmi_delete = 	new JMenuItem("삭제하기");
	String title=null;
	String cols[] = {"도서번호","도서명","저자","출판사"};
	String data[][] = new String [0][4];
	
	
	DefaultTableModel dtm_book = new DefaultTableModel(data,cols);
	JTable jtb_book = new JTable(dtm_book);
	JScrollPane jsp_book = new JScrollPane(jtb_book);
	
	//파라미터가 없는 생성자는 디폴트로 지원해주지만 있는 경우는 예측불가이므로 지원 불가함
	//인스턴스화 하면 생성자 호출이 일어남
	BookController btrl = new BookController(this);
	BookDialog bd = new BookDialog();
	BookDAO bDao = new BookDAO();
	
	public void eventMapping() {
		jmi_dbCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					dbActionPerformed(e);
			}
		});
		
		jbtn_db.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					dbActionPerformed(e);
			}
		});
		
	}
	
	
	
	
	protected void dbActionPerformed(ActionEvent e) {
		System.out.println("db연동 호출 성공"); 
		Connection con = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		con = dbMgr.getConnection();
		System.out.println(con.toString());
		
		
	}
	
	

	/*
	 * BookMain을 인스턴스화 할 때 전역변수에 선언된 BookDialog도 같이 인스턴스화 한다
	 * 이때 파라미터로 넘어간 Boolean,String은 값이 이미 결정된 상태이므로 아무리 버튼을 바꾸어도 title값을 변하지 않는다. 생성자와 타이틀 변경 타이밍이 맞지 않는다\
	 * 실제로 타임서버로 부터 시간정보를 듣기는 timeClient에서 진행되지만 생성자의 파라미터를 통해서 BookApp jlb_time의 
	 */
	
		TimeClient tc = null;
	
	
	public void initDisplay() {
		//아래코드가 JFrame의 자원을 회수함
		//부모자원이 회수될 때 JDialog도 같이 회수됨
		jm_file.setMnemonic('F');
		jm_edit.setMnemonic('E');
		jm_file.add(jmi_open);
		jm_file.add(jmi_dbCon);
		jm_file.add(js_file);
		jm_file.add(jmi_exit);	
		jmb_book.add(jm_file);
		
		jm_edit.add(jmi_all);
		jm_edit.add(jmi_sel);
		jm_edit.add(jmi_insert);
		jm_edit.add(jmi_update);
		jm_edit.add(jmi_delete);
		jmb_book.add(jm_edit);
		jbtn_sel.setToolTipText("상세조회");
		jbtn_insert.setToolTipText("입력");
		jbtn_update.setToolTipText("수정");
		jbtn_delete.setToolTipText("삭제");
		
		
		this.setJMenuBar(jmb_book);
		
		tc = new TimeClient(jlb_time);
		tc.start();
		this.setTitle("도서관리시스템");
		
		jbtn_sel.setIcon(new ImageIcon(imgPath+"detail.gif"));
		
		jbtn_insert.setIcon(new ImageIcon(imgPath+"new.gif"));
		jbtn_update.setIcon(new ImageIcon(imgPath+"update.gif"));
		jbtn_delete.setIcon(new ImageIcon(imgPath+"delete.gif"));
		
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jtbar.add(jbtn_all);
		jtbar.add(jbtn_sel);
		jtbar.add(jbtn_insert);
		jtbar.add(jbtn_update);
		jtbar.add(jbtn_delete);
		jtbar.add(jbtn_db);
		jp_south.add(jlb_time);

		this.add("South",jp_south);
		this.add("North",jtbar);
		this.add("Center",jsp_book);
		this.setSize(700, 700);
		this.setVisible(true);
		this.setIconImage(bicon.getImage());
		jbtn_all.addActionListener(this);
		jbtn_sel.addActionListener(this);
		jbtn_insert.addActionListener(this);
		jbtn_update.addActionListener(this);
		jbtn_delete.addActionListener(this);
		jmi_all.addActionListener(this);
		jbtn_db.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void refreshData() {
		System.out.println("refreshData 호출성공");
		List<BookVO> bookList = null;
		BookVO pbVO = new BookVO();
		pbVO.setCommand("all");
		bookList = btrl.sendALL(pbVO);
		//기존에 조회된 결과를 출력한 화면은 삭제처리한다.
		while(dtm_book.getRowCount()>0) {//bookList.size()숫자와 동일
			dtm_book.removeRow(0);//계속 row수만큼 반복하면서 첫번째 row를 계속 지워준다
		}
		
		System.out.println(bookList.size());
		//삭제한 후 다시 출력
		for(int i=0;i<bookList.size();i++) {
			BookVO bVO = bookList.get(i);
			Vector<Object> v =new Vector<Object>();
			v.add(bVO.getB_no());
			v.add(bVO.getB_name());
			v.add(bVO.getB_author());
			v.add(bVO.getB_publish());
			v.add(bVO.getB_info());
			//한개 row는 Vector에 담고 그 Vector를 for문 안에서 반복 추가 해줌
			dtm_book.addRow(v); //JTable에 담는것이 아니다 JTable은 양식일 뿐이고 실제 데이터를 갖는 클래스는 DefaultTableModel이다 -DataSet 지원함.
		}
	}	
	
	public static void main(String[] args) {
		ba.setDefaultLookAndFeelDecorated(true);
		TimeServer ts = new TimeServer();
		ts.initDisplay();//화면을 그리고 난 뒤 스레드 대기를 타도록 해야함.
		Thread th = new Thread(ts);
		th.start();//스레드의 run메소드를 호출하는 메소드
		 ba =  new BookApp();
		 ba.initDisplay();
		 ba.eventMapping(); //이벤트 연결 -익명 클래스
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 감지된 클래스의 주소번지 받기
		
		Object obj = e.getSource();
		//입력버튼을 누른거니?
		if(jbtn_insert==obj) {
			System.out.println("입력호출 성공");
			//insert here
			Map<String,Object> rMap = new HashMap<>();
			BookVO bVO=null;
			bd.set("입력", true, true, bVO, ba);
			//initDisplay를 호출한 이유는 setTitle("입력")과 setVisible(true)
			//때문이었다. 그런데 그  둘을 set메소드로 이전하였다.
		}
	
		
		else if(jbtn_update==obj) {//수정시에는 먼저 기본 정보를 조회하고 화면이동
			System.out.println("수정호출 성공");
			
			//select처리한 후 한 개 로우를 받아서 Map에 저장
			
			BookVO rbVO =null;
			BookVO pbVO = new BookVO(); //파라미터의 p
			pbVO.setCommand("detail");
			int index = -1;
			 index = jtb_book.getSelectedRow();
			if(index >=0) {//선택한 로우값이 있다.
				int b_no = Integer.parseInt(dtm_book.getValueAt(index, 0).toString());
				pbVO.setB_no(b_no);
			rbVO=	btrl.send(pbVO);
			}else {//선택한 로우가 없다.
				JOptionPane.showMessageDialog(this, "수정할 데이터를 선택하세요");
				return; //actionPerformed를 탈출함.
			}
			bd.set("수정",true,true,rbVO,ba);
		}
		else if(jbtn_sel==obj) {
			System.out.println("상세조회호출 성공");
			//insert here
			Map<String,Object> rMap = null;
			int indexs[] = jtb_book.getSelectedRows();
			if(indexs.length==0) {
				JOptionPane.showMessageDialog
				(this, "상세조회할 로우를 선하세요.");
				return;
			}
			else {
				int b_no = Integer.parseInt(
						dtm_book.getValueAt(indexs[0], 0).toString());
				//System.out.println("b_no : "+b_no);//2
				BookVO pbVO = new BookVO();
				pbVO.setCommand("detail");
				pbVO.setB_no(b_no);
				BookVO rbVO = btrl.send(pbVO);
				bd.set(jbtn_sel.getText(), true, false, rbVO, null);
			}
			//bd.set(jbtn_sel.getText(), true, false, rMap, null);
		}
		else if(jbtn_delete==obj) {
		
			System.out.println("삭제호출 성공");
			int indexs[] = jtb_book.getSelectedRows();
			if(indexs.length==0)
				JOptionPane.showMessageDialog(this,"삭제할 로우를 선택하세요");
			return;
		}else {
			
			
			List <Integer> bnos = new ArrayList<>();
			for(int i=0;i<dtm_book.getRowCount();i++) {
				if(jtb_book.isRowSelected(i)) {
					int b_no = Integer.parseInt(dtm_book.getValueAt(i,0).toString());
					bnos.add(b_no);
				}
			}
		BookVO pbVO = new BookVO();
		pbVO.setCommand("delete"); // command="delete"
		pbVO.setBnos(bnos);
		int result = 0;
		BookVO rbVO = new BookVO();
		rbVO = btrl.send(pbVO);
		result = rbVO.getResult();
		if(result>0) {
			JOptionPane.showMessageDialog(this, "삭제처리 되었습니다");
			refreshData();
		}
		
		else if(jbtn_all==obj) {
			System.out.println("전체 조회 호출 성공");
			refreshData();
		}else {
			JOptionPane.showMessageDialog(this, "실패했습니다");
		}
	}
	}
}
