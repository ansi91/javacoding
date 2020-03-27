package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class Proc_emp_update2 {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	CallableStatement cstmt = null;
	
	public void empUpdate2() {
		int deptno=0;
		int result=0;
		try {
			con= dbMgr.getConnection();
			con.setAutoCommit(false);
			cstmt = con.prepareCall("{call proc_emp_update2(?)}");
			String sdeptno=JOptionPane.showInputDialog("부서번호를 입력하세요");
			deptno=Integer.parseInt(sdeptno);
			cstmt.setInt(1, deptno);
			result=cstmt.executeUpdate();
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
		dbMgr.freeConnection(con, cstmt);
		}
	}
	
	
	public static void main(String[] args) {
//	Proc_emp_update2 peu=	new Proc_emp_update2();
//		peu.empUpdate2();
		new Proc_emp_update2().empUpdate2();
		
	
	
	}

}
