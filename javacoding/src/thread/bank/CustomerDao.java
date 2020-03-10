package thread.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.util.DBConnectionMgr;

public class CustomerDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	
	public Map<String,Object> login (String mem_id){
		Map<String,Object> rMap = null;
		StringBuilder sql = new StringBuilder();
		try {
		sql.append("SELECT acc_num,balence, mem_id, mem_name");
		sql.append(" FROM MEMBER                              ");
		sql.append(" WHERE mem_id=?                    ");
                                                      
		con = dbMgr.getConnection();
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, mem_id);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			rMap = new HashMap<String, Object>();
			rMap.put("acc_num", rs.getString("acc_num"));
			rMap.put("balence", rs.getString("balence"));
			rMap.put("mem_name", rs.getString("mem_name"));
			rMap.put("mem_id", rs.getString("mem_id"));
		}
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:"+e.toString());
		}
		return rMap;
	}
	public static void main(String[] args) {
		CustomerDao cd = new CustomerDao();
		Map<String, Object> rMap = cd.login("apple");
		System.out.println(rMap.get("mem_name"));
	}
	
}

/*ver 1.0 2020-03-05
 * 서버에서 접수처리
 * 사용자 아이디는 등록 처리 안됨
 * ver2.0
 * 서버에서 접속한 사용자 아이디 모두를 등록처리
 * ver3.0
 * bank테이블에 있어야만 접근 가능
 * 
 * 
 */
