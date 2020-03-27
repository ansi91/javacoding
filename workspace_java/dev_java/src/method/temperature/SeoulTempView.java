package method.temperature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;

public class SeoulTempView implements ActionListener {
	//선언부
	JTextField jtf_date = new JTextField("날짜를 입력하시오.");
	JButton jbtn_search = new JButton("조회");
	String cols[] = {"날짜","지점","평균기온(℃)","최저기온(℃)","최고기온(℃)"};
	String data[][] = new String[4][3];
	String years[] = null; //오라클서버 경유해서 반환받는 값으로 초기화
	//String month[] = null; //오라클서버 경유해서 반환받는 값으로 초기화
	DefaultTableModel dtm_zip = new DefaultTableModel(data,cols);
	JTable jt_zip = new JTable(dtm_zip);
	JScrollPane jsp_zip = new JScrollPane(jt_zip);
	JTableHeader jth_zip = new JTableHeader();
	JFrame jf_zip = new JFrame();//운영체제위에 창을 띄운다.
	JPanel jp_north = new JPanel();//속지를 만들어준다
	JComboBox jcb_year = null;
	//JComboBox jcb_month = null;
	SeoulTempDAO stDao = new SeoulTempDAO();
	//생성자
	public SeoulTempView() {
		//오라클 서버 경유하기
		years=stDao.getYearList();
		
		//오라클 서버 경유하고 나서 받은 리턴값으로 콤보벅수 인스턴스화 하기
		jcb_year = new JComboBox(years);

		
		//생성자에서 메소드 호출할 수 있다.
		initDisplay();
	}
	//새로고침 기능 - SELECT
	public void refreshData(String ddate) {
		jtf_date.setText("");
		SeoulTempDAO stDAO = new SeoulTempDAO();
		dtm_zip.setNumRows(0);
		dtm_zip=stDAO.SeoulTempSQL(ddate, dtm_zip);
	}
	//화면처리부
	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(new Color(100,255,100));
		jth_zip.setForeground(Color.BLACK);
		jth_zip.setFont(new Font("맑은 고딕",Font.BOLD,16));
		jt_zip.setGridColor(Color.blue);
		jt_zip.setRowHeight(20);
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(250);
		jt_zip.setSelectionBackground(new Color(40,200,180));
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.setBackground(Color.red);
		jp_north.add(jcb_year);
		//	jp_north.add(jcb_month);
		jp_north.add(jtf_date);
		jp_north.add(jbtn_search);
		jbtn_search.addActionListener(this);
		jtf_date.addActionListener(this);
		jtf_date.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				jtf_date.setText("날짜를 입력하시오");				
			}
			@Override
			public void focusGained(FocusEvent e) {
				jtf_date.setText("");	
			}
		});
		jf_zip.setTitle("서울날씨기록");
		jf_zip.add("North",jp_north);
		jf_zip.add("Center",jsp_zip);
		jf_zip.setSize(600,500);
		jf_zip.setVisible(true);
	}
	
	
	
	//메인 메소드
	public static void main(String[] args) {
		new SeoulTempView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if((obj == jbtn_search)||(obj == jtf_date)) {
			//자바에서는 같은 이름의 메소드를 정의할 수 있다. 단 파라미터의 갯수가 다르거나
			//파라미터의 타입이 반드시 달라야한다.
			refreshData(jtf_date.getText());
		}
	}
}
