package oracle.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

public class PlSqlTest {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	CallableStatement cstmt = null;
	
	ResultSet rs = null;
	public void empSalUpdate() {
		int result=0;
		try {
			con = dbMgr.getConnection();
//			con.setAutoCommit(false);
			cstmt = con.prepareCall("{call proc_ssal(?,?)}");
			final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("사원번호를 입력하세요");
			final String v_empno = br.readLine();
			cstmt.setInt(1, Integer.parseInt(v_empno));
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR); // out msg니까 registerOutParameter를 쓴다
			result = cstmt.executeUpdate();
			// con.commit();
			// con.rollback();
			System.out.println("result:" + cstmt.getString(2));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		final PlSqlTest pst = new PlSqlTest();
		pst.empSalUpdate();
	}

}
