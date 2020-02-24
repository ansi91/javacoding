package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

public class DBConnectionTst {
	
	void methodA(DBConnectionMgr dbMgr1) {
		System.out.println(this+","+dbMgr1);
	}
	
	
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("에러"+e);
		}
		
	} //end of freeConnection
	
	public static void main(String[] args) {
		DBConnectionTst dbTest = new DBConnectionTst();
		DBConnectionMgr dbMgr1 = DBConnectionMgr.getInstance();
		DBConnectionMgr dbMgr2 = DBConnectionMgr.getInstance();
		dbTest.methodA(dbMgr1); 
		if(dbMgr1==dbMgr2) {
			System.out.println("true");
		}else {
			System.out.println("false");
		} //end of if
		

	} // end of main

	

	
} // end of class
/*
   DBConnectionMgr은 여러 업무에서 공통으로 사용하는 클래스 입니다.
   사용한 자원(Connection,PreparedStateMent,ResultSet)은 반드시 반납을 하도록합니다.
   동시 접속자가 많은 시스템에서 자원사용은 곧 메모리랑 직결되므로
   서버가 다운되거나 시스템 장애 발생에 원인이 됩니다. 
 */
