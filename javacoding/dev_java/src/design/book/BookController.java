package design.book;

import java.util.List;

public class BookController {
	private static final String _DEL = "delete";//삭제하기
	private static final String _SEL = "detail";//상세조회
	private static final String _INS = "insert";//입력하기
	private static final String _UPD = "update";//수정하기
	private static final String _ALL = "all";//전체조회
	BookApp ba = null;
	BookTest bt =null;
	public BookController(BookApp ba) {
		this.ba = ba;
	}
	public BookController(BookTest bt) {
		this.bt = bt;
	}
	
	public BookVO send(BookVO pbVO) {
		BookVO rbVO = new BookVO();
		String command = pbVO.getCommand();
		//insert here
		if(_DEL.equals(command)) { //삭제
		//DELETE FROM book2020 WHERE b_no=1	
			int result = 0;
			result = ba.bDao.bookDelete(pbVO);
			rbVO.setResult(result);
		}
		else if(_INS.equals(command)) { //입력
		//INSERT INTO book2020(b_no, b_name, b_author, b_publish, b_info)
		//VALUES(?,?,?,?,?)	
			int result = 0;
			result = ba.bDao.bookInsert(pbVO);
			rbVO.setResult(result);
		}
		else if(_UPD.equals(command)) { //수정하기
		//UPDATE book2020 SET b_name=?, b_author=?, b_publish=?
		// WHERE b_no=2	
			int result = 0;
			result = ba.bDao.bookUpdate(pbVO);
			rbVO.setResult(result);
		}
		else if(_SEL.equals(command)) { //상세조회
		//SELECT b_no, b_name, b_author, b_publish FROM book2020
		// WHERE b_no=3	
			rbVO = ba.bDao.bookDetail(pbVO);
		}
		return rbVO;
	}///////////////////end of send
	//전체 조회
	public List<BookVO> sendALL(BookVO pbVO){
		System.out.println("sendALL 호출 성공");
		List<BookVO> bList = null;
		String command = pbVO.getCommand();
		if(_ALL.equals(command)) {
			bList = ba.bDao.bookList(pbVO);			
		}
		return bList;
	}///////////////////end of sendALL
}
