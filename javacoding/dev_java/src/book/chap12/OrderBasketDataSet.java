package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class OrderBasketDataSet {
	
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	
	public ArrayList<OrderVO> getList(){ 
		ArrayList<OrderVO> al = new ArrayList<OrderVO>(); 
		OrderVO obVO = new OrderVO();
		obVO.setIndate_vc("2020-02-19");
		obVO.setT_qty(150);
		obVO.setT_price(560000);
		al.add(obVO);
		
		obVO=null;
		obVO= new OrderVO();
		obVO.setIndate_vc("2020-02-20");
		obVO.setT_qty(105);
		obVO.setT_price(360000);
		al.add(obVO);
		obVO=null;
		obVO= new OrderVO();
		obVO.setIndate_vc("총계");
		obVO.setT_qty(255);
		obVO.setT_price(920000);
		al.add(obVO);
	
		for(OrderVO obVO2 : al) {
			System.out.print(obVO2.getIndate_vc()+"   "+obVO2.getT_qty()+"   "+obVO2.getT_price()+"\n");
		}
			return al;
	}
	
	public ArrayList<OrderVO> getList2(){ 
		ArrayList<OrderVO> al = new ArrayList<OrderVO>(); 
		OrderVO obVO = new OrderVO();
		StringBuilder sb = new StringBuilder("");
		
		sb.append("SELECT 										");
		sb.append(" decode(b.rno,1,a.indate_vc,2,'총계') indate_vc, sum(qty_nu) t_qty,sum(qty_nu*price_nu) t_price    									");
		sb.append(" FROM t_orderbasket a,                                                                                                                                    		 ");
		sb.append(" (SELECT rownum rno FROM dept WHERE rownum <3)b                                                                                              ");
		sb.append(" GROUP BY decode(b.rno,1,a.indate_vc,2,'총계')                                                                                                             ");
		sb.append(" ORDER BY decode(b.rno,1,a.indate_vc,2,'총계')                                                                                                                 "); 
		
		try {
			con = dbMgr.getConnection();
			pstmt =con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			//VO는 한번에 한개 로우만 담을 수 있어요 두 개 로우는 안되죠.
			//VO에는 변수 하나에 한 개 값만 담는 변수를 선언했기 때문이죠. 
			
			OrderVO obVO1 =null;
			while(rs.next()) {
				//오라클에서 꺼낸 값은 rs로 꺼내고
				//위에서 꺼낸 값은 obVO에 선언된 변수중 indate_vc변수에 값을 담아 주세요
				//왜 setter메소드로 자꾸만 담는지? private로 설정해서 위,변조를 할수 없게 한다
				obVO1 = new OrderVO();
				obVO1.setIndate_vc(rs.getString("indate_vc"));
				obVO1.setT_qty(rs.getInt("t_qty"));
				obVO1.setT_price(rs.getInt("t_price"));
				al.add(obVO1);
			}
				
			//자바에러는 이클립스에서 잡고 sql에러는 토드에서 잡자.
		} catch (SQLException se) { //오라클에서 발생되는 에러메세지 잡기
			System.out.println("[sql]" + se.toString()); 
			//System.out.println("[sql]" + sb.toString());
		}	catch(Exception e) {//자바 전체적으로 발생되는 에러 잡기
		
		e.printStackTrace();
		}
			return al; // al=null일수도 있다는걸 체크해보자
	}
	
	
	
	public static void main(String[] args) {
		//ArrayList<Integer> al2 = new ArrayList<Integer>();
		//main method를 직접 컨트롤 할 수 없다 -JVM
		
	
		
	}

}
