package oracle.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Vector;

/*
 * main()->userInput[동이름 결정]->getZipCodeList('당산동')
 */


public class ZipCodeSearch {
	/*선언부
	드라이버 클래스가 필요하다.-JDBCTest에서 꺼내쓰자
	URL정보도 JDBCTest에서 꺼내 쓸 수 있다.
	user와 pw는 생략할 수 있다. -왜? 
	JDBCTest._USER="hr"; static만 있으니까 계정이름 변경 가능함
	JDBCTest._DRIVER="";final이 있으니까 불가함.
	오라클 서버와 연결통로 만들기를 메소드를 꺼내보세요.*/
	
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; //select문을 날리기 위해서
	
	/*메소드 뒤에 Exception을 붙이는건 드라이브 클래스를 잘못 작성하여 
	에러가 아닌 런타임 에러 즉 ClassNotFoundException을 맞을 수 있기 때문에 선언 하였다.*/
	public Connection getConnection() throws Exception{
		System.out.println("connection 호출 성공 ");
		Class.forName(JDBCTest._DRIVER);
		con = DriverManager.getConnection(JDBCTest._URL
																, JDBCTest._USER
																, JDBCTest._PW);
		return con;
	}
	//예외처리를 내가 하지 않고 호출한곳에 던져준다
	public ZipCodeVO[] getZipCodeList(String dong)throws Exception {//오라클 서버에게 select문 전달
		System.out.println("getZipCodeList 호출 성공" + dong);
		ZipCodeVO zcVOS[] =null;
		ZipCodeVO zcVO = new ZipCodeVO();
			
		//오라클 서버에 요청을 보내기
		//서버에 요청 하기 전에 사용자로 부터 동이름을 먼저 입력 받아야한다.
		String sql ="";
		sql+="SELECT address, zipcode";
		sql+=" FROM zipcode_t";
		sql+=" WHERE dong LIKE '%'||?||'%'"; //조회결과가 3건일 경우 
		
		getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dong); //? 들어갈 동이름이 결정됨
		rs=pstmt.executeQuery(); //오라클 서버에게 처리를 요청함
		Vector<ZipCodeVO> v = new Vector<>();
		while(rs.next()) { //커서 이동 , 커서 이동
		//	System.out.println("while문 :" + rs.next()); //커서 이동
			zcVO = new ZipCodeVO();
			zcVO.setAddress(rs.getString("address"));
			zcVO.setZipcode(rs.getInt("zipcode"));
			v.add(zcVO);
			
		}
		zcVOS = new ZipCodeVO[v.size()];
		v.copyInto(zcVOS);//벡터 자료 구조에 들어 있는 정보를 복사하기
		
		//System.out.println("while문 탈출");
		//zcVO.uid_no=10; //ZipCodeVO에서 접근자를 private로 해놔서 에러가 뜬다. [문법에러] 왜? -> 웹이나 앱에서 동시사용자가 많을때 변조되면 안되기 때문에.
		//	zcVO.setUid_no(10); // getter setter를 통해서 초기화를 해야한다
		printZipCode(zcVOS); //메소드 호출시 타입표시 안함
		return zcVOS;
	}
	
	
	
	//조회된 우편번호 목록을 출력해보기
	public void printZipCode(ZipCodeVO zcVOS[]) {
		for(ZipCodeVO zVO:zcVOS) {
			System.out.println(zVO.getAddress()+"          "+zVO.getZipcode());
		}
		
	}
	
	
	public String inputDong() {
		String dong=null;
		Scanner scan = new Scanner(System.in);
		dong = scan.nextLine();
		return dong;
		}
	
	/* #23(가장 먼저 호출 -entry pointer -main 스레드)->#24(변수선언:지역변수)->#27(인스턴스화)
		#28(메소드를 호출)->#10(파라미터X리턴O)->#12스캐너->13->14(입력받은 값 확정)->#27(리턴값으로 받음 리턴값이므로 변수에 받아야함)
	*/
	public static void main(String[] args)throws Exception {
		String dong =null;
		System.out.println("동을 입력해주세요");
		ZipCodeSearch zcs = new ZipCodeSearch();
		dong=zcs.inputDong();
		if(dong==null) {
			System.out.println("반드시 동을 입력해야 합니다");
		}else {
			System.out.println("당신은 " + dong+"을 입력하셨습니다");
		ZipCodeVO zcVOS[] =	zcs.getZipCodeList(dong);
		}
	 }
}
