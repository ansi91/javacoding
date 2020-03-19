package book.chap12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.util.DBConnectionMgr;
import oracle.jdbc.JDBCTest;
import oracle.jdbc.ZipCodeVO;

/*implements 뒤에 오는 이름은 모두 인터페이스
인터페이스는 추상메소드만 가지고 있다. 메소드 뒤에 세미콜론으로 끝남.
void (int) methodA();
인터페이스는 단독으로 인스턴스화 할수 없다
ItemListener item = new ItemListener(); 불법
ItemListener item = new ZipcodeSearchApp(); 합법
인터페이스는 반드시 구현체 클래스가 있어야 한다
구현체 클래스가 되기 위한 필요조건은 반드시 추상메소드를 구현해주어야 한다 @Override
*/
public class ZipCodeSearchApp implements ActionListener,FocusListener,ItemListener {
	String zdos[] = null;
	JComboBox jcb_zdo = null;  
	
	String zdo=null;
	
	Connection 			con 	= null;
	
	PreparedStatement 	pstmt 	= null;
	
	ResultSet 		rs  		= null;
	JTextField 		jtf_dong 	= new JTextField(15);
	JButton 		jbtn_search = new JButton("조회");
	JButton 		jbtn_del 	= new JButton("삭제");
	
	String 			cols[]      = {"주소","우편번호"};
	String 		    data[][] 	= new String[0][2];
	
	DefaultTableModel dtm_zip	= new DefaultTableModel(data,cols);
	
	JTable		    jt_zip 		= new JTable(dtm_zip);
	JScrollPane     jsp_zip		= new JScrollPane(jt_zip);
	
	JTableHeader    jth_zip		= new JTableHeader();
	JFrame			jf_zip		= new JFrame();//운영체제위에 창을 띄운다.
	JPanel 			jp_north	= new JPanel();//속지를 만들어 준다.
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public ZipCodeSearchApp() {

		
		zdos=getZDOList();
		jcb_zdo= new JComboBox(zdos);
		
	}
	public ZipCodeSearchApp(String str, int i) {
	}
	
	public List<Map<String,Object>> refreshData(String zdo,String myDong) {
		con = dbMgr.getConnection();
	
		System.out.println("refreshData호출 성공"+myDong + ","+zdo);
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT address, zipcode FROM zipcode_t WHERE 1=1 ");
		
		
		if(zdo!=null && zdo.length()>0) {
			sql.append(" AND ZDO=?");
			}
		if(myDong!=null && myDong.length()>0) {
			sql.append(" AND dong LIKE '%'||?||'%' ");
			}
		
		int i=1;
		List<Map<String,Object>> addrList = new ArrayList<Map<String,Object>>();
		try {
			pstmt = con.prepareStatement(sql.toString());
			if(zdo!=null && zdo.length()>0) {
				pstmt.setString(i++, zdo);
				}
			
			if(myDong!=null && myDong.length()>0) {
				pstmt.setString(i++, myDong);
			}
			rs = pstmt.executeQuery();
			//Object[] keys= {"address","zipcode"};
			 Map<String,Object> rMap = null;
		

			while(rs.next()) {//커서 이동, 커서이동
				rMap = new HashMap<String, Object>();
				rMap.put("address",rs.getString("address"));
				rMap.put("zipcode",rs.getInt("zipcode"));
				addrList.add(rMap);
			}
			System.out.println("asdfasdasdasdasd");
				
			System.out.println("addrList.size():"+addrList.size());
			if(addrList.size()>0) {//조회된 결과가 있니?
				while(dtm_zip.getRowCount()>0) {
					dtm_zip.removeRow(0);
				}
	
				for(int x=0;x<addrList.size();x++) {
			
					Vector oneRow = new Vector();
					Map<String,Object> map = addrList.get(x);
					int j=0;
					oneRow.add(0,map.get("address"));
					oneRow.add(1,map.get("zipcode"));
//					oneRow.add(j++,addrList.get(x).get(keys[j-1]));	
//					oneRow.add(j++,addrList.get(x).get(keys[j-1]));	
					dtm_zip.addRow(oneRow);
				}
			}
		} catch (SQLException se) {
			System.out.println("[[query]]"+sql);
		} catch(Exception e) {
			System.out.println("[[Exception]]"+e);
		}
		return addrList;
	}

	public void initDisplay() {
		System.out.println("initDisplay 호출 성공");
		jcb_zdo.addItemListener(this);
		
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(new Color(22,22,100));
		jth_zip.setForeground(Color.white);
		jth_zip.setFont(new Font("맑은고딕",Font.BOLD,16));
		jt_zip.setGridColor(Color.BLUE);//그리드 색상
		
		jt_zip.setRowHeight(20);
	
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(350);
		
		jt_zip.setSelectionBackground(new Color(186,186,241));
		jt_zip.setSelectionForeground(new Color(22,22,100));
	
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.setBackground(Color.red);
		jp_north.add(jcb_zdo);
		jp_north.add(jtf_dong);
		jp_north.add("East",jbtn_search);
		jp_north.add(jbtn_del);
		jbtn_del.addActionListener(this);
		jbtn_search.addActionListener(this);
		jbtn_search.requestFocus();
		jtf_dong.addActionListener(this);
		jf_zip.setTitle("우편번호 검색");
		
		jf_zip.add("North",jp_north);
		jf_zip.add("Center",jsp_zip);
		jf_zip.setDefaultCloseOperation(jf_zip.EXIT_ON_CLOSE);
		jf_zip.setSize(600, 500);
		jf_zip.setLocationRelativeTo(null);
		jf_zip.setVisible(true);
	}

	public String[] getZDOList() {
		String zdos[] = null;
		
		StringBuilder sb = new StringBuilder("");
	
		
	sb.append("SELECT '전체' zdo FROM dual        ");
	sb.append("UNION ALL                          ");
	sb.append("SELECT zdo                         ");
	sb.append("FROM (                             ");
	sb.append("            	SELECT distinct zdo   ");
	sb.append("                FROM zipcode_t     ");
	sb.append("                ORDER BY zdo asc   ");
	sb.append("           )                                ");
		            
		try {
			con = dbMgr.getConnection();	
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<String>();
			while(rs.next()) {
				String zdo=rs.getString("zdo");
				v.add(zdo);
				zdos = new String[v.size()];
				v.copyInto(zdos);
				this.zdo=v.get(0);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return zdos;
	}
	
	
	//메인메소드
	public static void main(String[] args) {
		ZipCodeSearchApp zipApp = new ZipCodeSearchApp();
		zipApp.initDisplay();
		
	}
	//
	@Override
	public void actionPerformed(ActionEvent ae) {
	
		Object obj = ae.getSource();
		if((obj == jbtn_search)||(obj == jtf_dong)) {
			String myDong = jtf_dong.getText();
			
			
		
			refreshData(zdo,myDong);			
		}else if(obj == jbtn_del) {
			
			int index[] = jt_zip.getSelectedRows();
			for(int row:index) {
				JOptionPane.showMessageDialog(jf_zip, row);
			}
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		
		
	}
	@Override
	public void focusLost(FocusEvent e) {
	
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if(obj==jcb_zdo) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
					System.out.println(zdos[jcb_zdo.getSelectedIndex()]);
				zdo = zdos[jcb_zdo.getSelectedIndex()];
				
			}
		}
		
	}
}//////////end of ZipCodeSearchApp





