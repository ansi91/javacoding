package study.prac;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
Connection con = null;
public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
public static final String _URL = "jdbc:oracle:thin:@192.168.0.7:1521:orcle11";
public static final String _USER = "bank";
public static final String _PW = "tiger";

private static DBConnectionMgr dbMgr = null;

//싱글톤 생성 하는법
//private로 되어있는 DBConnectionMgr을 접근하기 위해 public 메소드로 만든다
public static DBConnectionMgr getInstance(){

	if(dbMgr==null) {
		dbMgr = new  DBConnectionMgr();
	}
	
	return dbMgr;
}

//물리적으로 떨어져 있는 오라클 서버와 연결통로 만들기
public Connection getConnection() {
	try {
		Class.forName(_DRIVER);
		con=DriverManager.getConnection(_URL,_USER,_PW);
	} catch (ClassNotFoundException se) {
		System.out.println("드라이버 클래스를 찾을수 없어요");
	}catch(Exception e) {
		System.out.println("예외 발생");
	}
	
	return con;
}
//사용한 자원 반납 - 반납은 생성한 역순으로 반납한다
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
		System.out.println("에러발생");
		}
	
	}

public void freeConnection(Connection con, PreparedStatement pstmt) {
	try {
		if(pstmt!=null) {
			pstmt.close();
		}
		if(con!=null) {
			con.close();
		}	
	} catch (Exception e) {
		System.out.println("에러발생");
	}
	

  }// end of freeConnection 2parameter
}// end of class
