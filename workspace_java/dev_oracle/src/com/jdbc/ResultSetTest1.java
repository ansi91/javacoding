package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//최근에 와서 RDBMS제품을 대신 할 수 있는 다른 제품들이 많이 나왔다. - 무겁고 고가이다
//경량 데이터베이스, NoSQL, 빅데이터 혹은 클라우드 시스템 -Mpp기반 열중심의 처리에 최적화된 db
//오라클은 행중심의 처리
//ORM솔루션이 제공(MyBatis[구글-오픈소스-쿼리문작성-xml문서-디버깅편리], iBatis, Hibernate[쿼리문이 없다], JDO......)
//DB연동 코드의 30%이상이 줄어든다.
//반복 되는 코드를 많이 줄여준다.
public class ResultSetTest1 {
	public ResultSetTest1() {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		try {
			con = dbMgr.getConnection();
			sql.append("SELECT empno,ename FROM emp");
			pstmt = con.prepareStatement(sql.toString()
															,ResultSet.TYPE_SCROLL_INSENSITIVE //비순차적으로 커서를 이동한다 - 동기화가 안되서 추가로 입력한게 반영이 안된다.
															,ResultSet.CONCUR_UPDATABLE);
			rs=pstmt.executeQuery();
			//데이터 추가하기
			rs.moveToInsertRow();//추가 가능한 로우로 커서 이동
			rs.updateInt(1, 1002);
			rs.updateString(2, "이성계");
			rs.insertRow();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+""+rs.getString("ename"));
			}
			if(!rs.isBeforeFirst()) {
				rs.beforeFirst();
			}
			rs.first();
			System.out.println("======>"+rs.getInt("empno"));
			while(rs.next()) {
				if(rs.getInt("empno")==1001) {
					rs.deleteRow();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ResultSetTest1();
	}

}
