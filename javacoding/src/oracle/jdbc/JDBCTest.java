package oracle.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;



public class JDBCTest {
/*
 * 변수 이름 앞에 final이 붙으면 상수가 됨
 * 상수는 다른 값으로 재정의 불가함
 */
	//이 클래스를 읽어야 오라클 제품인것을 확인가능함

	public static final String _DRIVER="oracle.jdbc.driver.OracleDriver"; 
	//물리적으로 떨어져 있는 오라클 서버에 url정보 추가
	public static final String _URL = "jdbc:oracle:thin:@192.168.0.7:1521:orcl11";
	public static String _USER = "scott";
	public static String _PW = "tiger";
	//물리적으로 떨어져 있는 오라클 서버와 연결통로를 만들 때 사용하는 클래스
	Connection con =null;
	ResultSet rs =null;
	PreparedStatement pstmt =null;
	//java.lang폴더를 제외하고는 모두 다 import 해주어야 JVM이 그 클래스를 찾음.
	//오라클에 살고 있는 커서를 조작하는 클래스를 제공함
	//커서 위치에 로우가 존재하면 true, 조회된 결과가 없으면 false 리턴
	public String currentTime()throws Exception {//예외 상황이 발생할 수 있기 때문에 예외 처리를 해야한다
		Class.forName(_DRIVER);
		String sql = "select to_char(sysdate,'HH24:MI:SS') from dual"; 
		//아래 메소드가 호출되면 오라클 서버와 연결 통로를 갖게됨.
		//이 연결 통로를 통해서 select 문을 전령 클래스가 가지고 들어가야 함 반드시 먼저 커넥션을 하고 있어야 한다
		con = DriverManager.getConnection(_URL, _USER, _PW);	
		pstmt = con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString(1);
		
	}
		return "15시 50분";
	}
	public static void main(String[] args) throws Exception {
/*
 *java.lang 패키지에 클래스는 모두 찾지만 그 외 패키지는 찾을수 없다
 *Scanner scan = new Scanner(System.in); 
 */
	JDBCTest jt = new JDBCTest();
	String ctime = jt.currentTime();
	System.out.printf("현재시간은%s입니다 "+" ",ctime);
		
	}
}
