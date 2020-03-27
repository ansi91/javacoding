package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatementTest1{

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@192.168.0.7:1521:orcl11";
		String user = "scott";
		String pw = "tiger";
		Connection con = null; //java.sql.*
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con =DriverManager.getConnection(url, user, pw);
			System.out.println("con:" + con);
			System.out.println("사원번호를 입력하세요");
			Scanner scan = new Scanner(System.in);
			int u_empno = scan.nextInt();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ename, job FROM emp");
			sql.append(" WHERE empno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, u_empno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("ename:"+rs.getString(1)+"job:"+rs.getString(2));
				
			}else {
				System.out.println("조회 결과가 없습니다");
			}
			
		} catch (ClassNotFoundException e) {
			 System.out.println("드라이버 클래스를 찾을 수 없습니다.");
			 
		}catch(Exception e) {
			//statck 영역에 쌓여 있는 에러 메세지를 history를 출력 - line번호까지 출력
			e.printStackTrace();
			
		}finally {
			System.out.println("정상적으로 처리가 되었을 때도 실행됨");
		}
		
		try {
			if(rs !=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("여기");
	}

}



