package dbprac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

public class DeptTest {
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		DeptDao dd = new DeptDao();
		dbMgr.getConnection();
		dd.selectALL();
		

		

	}

}
