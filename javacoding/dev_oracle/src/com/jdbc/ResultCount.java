package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultCount {

	public static void main(String[] args) {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		
		
		
		try {
			con = dbMgr.getConnection();
			sql.append("SELECT empno FROM emp");
			pstmt = con.prepareStatement(sql.toString()
															,ResultSet.TYPE_SCROLL_SENSITIVE //비순차적으로 커서를 이동한다
															,ResultSet.CONCUR_UPDATABLE); 	  //ResultSet이 저장되서 필요한 정보를 얻을 수 있다.
			rs=pstmt.executeQuery(); //SELECT 문 요청시 사용
			rs.last(); //기본적으로 first에 있는 cursor를 맨 마지막으로 이동
			int rowcount = rs.getRow();
			System.out.println("Total row:"+rowcount);
			
			rs.first();
			System.out.println(rs.next());
			rs.last();
			System.out.println(rs.next());

			
			System.out.println(rs.absolute(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
