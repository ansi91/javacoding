package design.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConnectionMgr;


public class BookDAO {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public int bookDelete(BookVO pbVO) {
		int result = 0;
		System.out.println("bookDelete");
		result = 1;
		
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("DELETE FROM book20202 WHERE b_no=?");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pbVO.getB_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	//INSERT into book2020(b_no,b_name,b_author,b_publish,b_info)values(seq_book_no2.nextval,'1','1','1','1')
	//파라미터로 1을 네개 삽입해서 추가해봄. 4개가 필요함. 1 row inserted => 1실패하면 0
	
	
	public int bookInsert(BookVO pbVO) {
		System.out.println("bookInsert");
		int result=0;
		StringBuilder sql = new StringBuilder();
		try {
		sql.append("INSERT into book2020(b_no,b_name,b_author,b_publish,b_info)values(seq_book_no2.nextval,?,?,?,?) ");
		con= dbMgr.getConnection();
		pstmt = con.prepareStatement(sql.toString());
		int i =1;
		pstmt.setString(i++, pbVO.getB_name());
		pstmt.setString(i++, pbVO.getB_author());
		pstmt.setString(i++, pbVO.getB_publish());
		pstmt.setString(i++, pbVO.getB_info());
		result = pstmt.executeUpdate();
		System.out.println(result);
		} catch(SQLException se){
			System.out.println("[Exception]" + se.toString());
		}
		
			catch (Exception e) {
			System.out.println("[[Exception:]]" + e.toString());
		}
		return result;
	}

	public int bookUpdate(BookVO pbVO) {
		System.out.println("bookUpdate");
		return 0;
	}

	public BookVO bookDetail(BookVO pbVO) {
		System.out.println("bookDetail");
		BookVO rbVO = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT b_no, b_name, b_author, b_publish");
			sql.append(" ,b_info FROM book2020                         ");
			sql.append(" WHERE b_no=?");//2
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			//insert here
			pstmt.setInt(1, pbVO.getB_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rbVO = new BookVO();
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_name(rs.getString("b_name"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_info(rs.getString("b_info"));
			}
			System.out.println("rbVO:"+rbVO.getB_info());
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+ sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rbVO;
	}
	//전체 조회 구현
	public List<BookVO> bookList(BookVO pbVO) {
		System.out.println("bookList() 호출 성공");
		List<BookVO> bookList = new ArrayList<>();//bookList.size()=0
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT b_no, b_name, b_author, b_publish,b_info");
			sql.append("  FROM book2020                         ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			BookVO rbVO = null;
			while(rs.next()) {
				rbVO = new BookVO();
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_name(rs.getString("b_name"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_info(rs.getString("b_info"));
				bookList.add(rbVO);
			}
			System.out.println("bookList.size():"+bookList.size());
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+ sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}/////////////////////end of bookList
//	public static void main(String[] args) {
//		BookDAO bd = new BookDAO();
//		BookVO bVO = new BookVO();
//	}

}
