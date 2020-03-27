package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.oracore.OracleType;

public class ProcDeptList {
	String url = "jdbc:oracle:thin:@192.168.0.7:1521:orcl11";
	String user = "scott";
	String pw = "tiger";
	Connection con = null; //java.sql.*
	//프로시저를 call할 때는 CallableStatement를 사용한다
	CallableStatement cstmt =null;
	OracleCallableStatement ocst = null;
	ResultSet cursor = null;
	int lotto[] = new int[6];
		public List<Map<String,Object>> deptList(){
			List<Map<String,Object>> dList = new ArrayList<Map<String,Object>>();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url, user, pw);
				cstmt = con.prepareCall("{call proc_deptList(?)}");
				cstmt.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt.execute();
				ocst = (OracleCallableStatement)cstmt;
				 cursor = ocst.getCursor(1);
				
				Map<String,Object> rMap=null;
				while(cursor.next()) {
					rMap = new HashMap<String, Object>();
					rMap.put("deptno",cursor.getInt(1));
					rMap.put("dname",cursor.getString(2));
					dList.add(rMap);
					
				}
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}finally {
				System.out.println("finally");
				try {
					if(cursor!=null)cursor.close();
					if(ocst!=null)ocst.close();
					if(cstmt!=null)cstmt.close();
					if(con!=null)con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		return dList;
		
		} 
			
		
		
		public static void main(String[] args) {
			ProcDeptList pdt = new ProcDeptList();
		
		
			
			ProcDeptList pdl = new ProcDeptList();
			List<Map<String,Object>> dList = null;
			dList=pdl.deptList();
			if(dList == null) {
				System.out.println("조회결과가 없습니다");
				
			}else {
				for(Map<String,Object> rMap:dList) {
					System.out.println(rMap.get("deptno")+","+rMap.get("dname"));
				}
			}
	}

}
