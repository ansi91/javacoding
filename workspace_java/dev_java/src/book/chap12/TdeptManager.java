package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class TdeptManager extends JFrame {
	String cols[]= {"사원ID","사원 이름","부서명"};
	String data[][] = new String [0][3];
	DefaultTableModel dtm_order = new DefaultTableModel(data,cols);	 
	JTable jtb_order = new JTable(dtm_order);
	JScrollPane jsp_order = new JScrollPane(jtb_order);
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public TdeptManager() {
		
	}
	
	public ArrayList<TempVO> getDeptList(){ 
		ArrayList<TempVO> al = new ArrayList<TempVO>(); 
		TdeptVO tdVO = new TdeptVO();
		StringBuilder sb = new StringBuilder("");
		
	sb.append("	SELECT emp_id,emp_name,dept_name       ");
	sb.append(" FROM temp,tdept                         ");
	sb.append(" WHERE TEMP.dept_code = tdept.dept_code  ");
		
		try {
			con = dbMgr.getConnection();
			pstmt =con.prepareStatement(sb.toString());
		
			
			TempVO tpVO1 =null;
			while(rs.next()) {
			
				tpVO1 = new TempVO();
				tpVO1.setEmp_id(rs.getInt("emp_id"));
				tpVO1.setEmp_name(rs.getString("emp_name"));
		//		tpVO1.setDept_name(rs.getString("dept_name"));
				al.add(tpVO1);
			}
				
			//자바에러는 이클립스에서 잡고 sql에러는 토드에서 잡자.
		} catch (SQLException se) { //오라클에서 발생되는 에러메세지 잡기
			System.out.println("[sql]" + se.toString()); 
		}	catch(Exception e) {//자바 전체적으로 발생되는 에러 잡기
		
		e.printStackTrace();
		}
			return al; 
	}
	
	
	public void initDisplay() {
		this.setTitle("사원정보 조회");
		this.add("Center",jsp_order);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		TdeptManager tm = new TdeptManager();
		tm.initDisplay();
		tm.getDeptList();
	}

}
