package orcle.jdbc2;

public class DeptSimulation {

	DeptDAO dDAO = new DeptDAO();
	int result = 0; //입력결과|수정결과|삭제결과 확인
	//조회하기 테스트
	public void selectTest(DeptoVO dVO) {
		System.out.println("selectTest 호출 성공");
		DeptoVO dVOS[]=dDAO.deptList(dVO.getDeptno());
		for(DeptoVO rtVO : dVOS) {
			System.out.println(rtVO.getDeptno()+"  "+rtVO.getDname()+"  "+rtVO.getLoc());
		}
	}
	//등록하기 테스트
	public void insertTest(DeptoVO dVO) {
		System.out.println("insertTest 호출 성공");
		result = dDAO.deptInsert(dVO.getDeptno(), dVO.getDname(), dVO.getLoc());
		System.out.println("등록 성공 "+ result);
	}
	//수정하기 테스트
	public void updateTest(DeptoVO dVO) {
		System.out.println("updateTest 호출 성공");
		result =dDAO.deptUpdate(dVO.getLoc(), dVO.getDname(), dVO.getDeptno());
		System.out.println("수정 성공"+result);
	}
	//삭제하기 테스트
	public void deleteTest(DeptoVO dVO) {
		System.out.println("deleteTest 호출 성공");
		result=dDAO.deptDelete(dVO.getDeptno());
		System.out.println("삭제 성공"+result);
	}
	public static void main(String[] args) {
		//화면 없이 테스트 하기
		//화면이 아직 완성되지 않았으므로 통합테스트는 불가하다
		//하지만 단위테스트는 언제나 가능하다
		DeptSimulation ddt = new DeptSimulation();
		DeptoVO dVO = new DeptoVO();
		dVO.setDeptno(61);
		dVO.setDname("품질관리팀");
		dVO.setLoc("인천");
		ddt.insertTest(dVO);

		dVO = null;
		dVO = new DeptoVO();
		dVO.setDeptno(61);
		ddt.selectTest(dVO);
		dVO = null; //기존의 연결고리를 끊는다
		
		
		
		 
		dVO = new DeptoVO();
		dVO.setDeptno(61);
		dVO.setDname("유지보수팀");
		dVO.setLoc("서울");
		ddt.updateTest(dVO);
		
		dVO = null; 
		dVO = new DeptoVO();
		dVO.setDeptno(61);
		ddt.deleteTest(dVO);
		
	
	}

}
