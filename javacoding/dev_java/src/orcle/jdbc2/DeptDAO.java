package orcle.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class DeptDAO {
	
Connection 					con 		= null;
PreparedStatement 		    pstmt		= null;
ResultSet						rs 			= null;
DBConnectionMgr			dbMgr    =  DBConnectionMgr.getInstance();



//SELECT deptno, dname, loc FROM dept WHERE deptno=10;
//SELECT deptno, dname, loc FROM dept WHERE deptno>10 AND dname=?
//부서 집합에서 조회하는 메소드 선언하기
			public DeptoVO[] deptList(int deptno) {
				//조회 결과가 n건 이므로 객체 배열로 받아야 한다
				DeptoVO[] dvos= null;
				//쿼리문을 작성할 때 String 대신 StringBuilder 를 사용해야한다
				StringBuilder 					sb			= new StringBuilder("");
				sb.append(" SELECT deptno, dname, loc FROM dept WHERE deptno=?");
				try {
					con 	= dbMgr.getConnection();
					pstmt = con.prepareStatement(sb.toString());
					pstmt.setInt(1, deptno);// 물음표가 있을때
					
					rs = pstmt.executeQuery();
					DeptoVO dVO=null;
					Vector <DeptoVO> v = new Vector<>();
					while(rs.next()) {
						dVO=new DeptoVO();
						dVO.setDeptno(rs.getInt("deptno"));
						dVO.setDname(rs.getString("dname"));
						dVO.setLoc(rs.getString("loc"));
						v.add(dVO);
					}
					dvos = new DeptoVO[v.size()];
					v.copyInto(dvos);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {//에러가 발생 하더라도 자원 반납은 무조건 꼭 해주세요 
							//사용한 자원은 반납할것
					dbMgr.freeConnection(con, pstmt, rs);
				}
				return dvos;
			}
			/*
			 * 메소드 오버로딩
			 * 메소드 타입이나 파라미터의 갯수가 달라야 한다
			 */
			public DeptoVO[] deptList(int deptno,String dname) {
				
				DeptoVO[] dvos= null; //조회결과를 객체 배열에 받아서 리턴해준다 
				return dvos;
			}

		//INSERT INTO dept(deptno, dname, loc) values(?,?,?);
		//신규 부서 정보를 등록하는 메소드 선언하기
		public int deptInsert(int deptno, String dname, String loc) {
			int result=0; //0이면 실패 1이면 성공
			StringBuilder 					sb			= new StringBuilder("");
			sb.append(" INSERT INTO dept(deptno, dname, loc) values(?,?,?)");
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				int i=0;
				pstmt.setInt(++i, deptno);// 물음표가 있을때
				pstmt.setString(++i, dname);
				pstmt.setString(++i, loc);
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally { 
				dbMgr.freeConnection(con, pstmt);
			}
			return result;
		}
		//UPDATE SET dname=? , loc=? WHERE deptno=?
		//기존부서정보를 수정하는 메소드 선언하기
		public int deptUpdate( String loc, String dname,int deptno ) {
			int result=0;
			StringBuilder 					sb			= new StringBuilder("");
			sb.append(" UPDATE dept SET dname=? , loc=? WHERE deptno=?");
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				int i=0;
				pstmt.setString(++i, dname);
				pstmt.setString(++i, loc);
				pstmt.setInt(++i, deptno);// 물음표가 있을때
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally { 
				dbMgr.freeConnection(con, pstmt);
			}
			return  result;
		}
		//DELETE FROM dept WHERE deptno=?;
		//사라진 부서 정보를 반영하는 메소드 선언하기
		public int deptDelete(int deptno) {
			int result=0;
			StringBuilder 					sb			= new StringBuilder("");
			sb.append(" DELETE FROM dept WHERE deptno=?");
			
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				int i=0;
				pstmt.setInt(++i, deptno);// 물음표가 있을때
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally { 
				dbMgr.freeConnection(con, pstmt);
			}
			//delete문에 에러가 발생했을 때 delete문을 출력하는 문장을 작성할 수 있는데
			//이때 변수 sb를 사용할 수 있다 | 없다. {} 중괄호 때문에 지역변수 처럼 되버림
			return result;
		}	
		
}