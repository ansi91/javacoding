package method.temperature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class SeoulTempDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	StringBuilder sb = new StringBuilder("");
	public SeoulTempDAO() {
		con=dbMgr.getConnection();
	}
	public DefaultTableModel SeoulTempSQL(String ddate, DefaultTableModel dtm_zip) {
		SeoulTempVO stVOS[] = null;
		SeoulTempVO stVO = null;
		SeoulTempDAO stDAO = new SeoulTempDAO();
		String sql = "";
		sql+="SELECT sdate,loc, atemp, mitemp, matemp";
		sql+=" FROM seoultemp";
		if(ddate!=null || ddate.length()>0) {
			sql+=" WHERE sdate LIKE '%' ||?|| '%'";
		}
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, ddate);
			rs=pstmt.executeQuery();
			Vector<SeoulTempVO> v = new Vector<SeoulTempVO>();
			while (rs.next()) {
				stVO = new SeoulTempVO();
				stVO.setDdate(rs.getString("sdate"));
				stVO.setLoc(rs.getInt("loc"));
				stVO.setAvg_tem(rs.getDouble("atemp"));
				stVO.setMin_tem(rs.getDouble("mitemp"));
				stVO.setMax_tem(rs.getDouble("matemp"));
				v.add(stVO);
			}
			stVOS = new SeoulTempVO[v.size()];
			v.copyInto(stVOS);//벡터 자료구조에 들어있는 정보를 복사하기
			System.out.println("v.size():"+v.size()+", "+stVOS.length);
			if(v.size()>0) {//조회된 결과가 있는가?
				//조회된 결과가 있다면 데이터를 DefaultTableModel에 담아주어야함.
				//그래야 JTable에서 그리드에 출력된 결과를 볼 수 있음
				//but 컬럼을 하나씩 일일이 초기화해줄 필요X
				for(int x=0;x<v.size();x++) {
					Vector oneRow = new Vector();
					oneRow.add(0, stVOS[x].getDdate());
					oneRow.add(1, stVOS[x].getLoc());
					oneRow.add(2, stVOS[x].getAvg_tem());
					oneRow.add(3, stVOS[x].getMin_tem());
					oneRow.add(4, stVOS[x].getMax_tem());
					dtm_zip.addRow(oneRow);
				}
			}
		} catch (SQLException se) {
			//테이블이 존재하지 않습니다.
			//혹은 부적합한 식별자-컬럼명 에러
			System.out.println("[[query]]"+sql);
		} catch (Exception e) {
			System.out.println("[[Exception]]"+e);
		}
		return dtm_zip;
	}
	public String[] getYearList() {
		con =dbMgr.getConnection();
					sb.append("SELECT distinct(to_char(to_date(sdate),'YYYY'))                           							 ");
					sb.append(" From seoultemp                                                             									 ");
					sb.append(" WHERE TO_CHAR(TO_DATE(sdate),'YYYY')>TO_CHAR(TO_DATE(sysdate),'YYYY')-11   ");
					sb.append(" ORDER BY TO_CHAR(TO_DATE(sdate),'YYYY'))                                   					  ");
		
	
		String years[] = null;
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<String>();
				while(rs.next()) {
					String sdate = rs.getString("sdate");
					v.add(sdate);
				}	
				years = new String[v.size()]; //years라는 String 배열을 v의 사이즈 만큼 생성해준다.
				v.copyInto(years);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
		return years;
	}
}