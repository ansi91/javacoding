package dbprac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import study.prac.DBConnectionMgr;

public class DeptDao {

	 Connection con = null;
	 PreparedStatement pstmt=null;
	 ResultSet rs = null;
	
	 
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public void selectALL() {
		StringBuilder sql = new StringBuilder();
		sql.append("select *	 FROM dept");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc 		= rs.getString("loc");
			System.out.println("deptno" + deptno);
			System.out.println("dname" + dname);
			System.out.println("deptno" + loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	
}
