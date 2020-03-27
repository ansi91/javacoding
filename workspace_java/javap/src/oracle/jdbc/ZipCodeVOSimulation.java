package oracle.jdbc;

import com.vo.ZipCodeVO;
public class ZipCodeVOSimulation {

	void methodA() {
		ZipCodeVO zip[] = new ZipCodeVO[2];
		
		ZipCodeVO zcVO = new ZipCodeVO();
		
		//삼각형 안에 zipCode변수에 값 넣기
		zcVO.setZipcode(13579); //우편번호를[#8번 인스턴스화] 각각 담았다. 삼각형에 13579라고 적었다고 생각하면 된다
		zip[0]=zcVO; //사각형에 삼각형 넣기    13579라고 적힌 삼각형을 사각형 모양에 넣었다.
		zcVO = new ZipCodeVO();                               
		zcVO.setZipcode(77777); //우편번호를[#13번 인스턴스화] 각각 담았다. 
		zip[1]=zcVO;
		//메소드 호출
		printZcVos(zip); //메소드를 호출해서  파라미터로 주소번지[] 넘겨준다
	}
										
	static void printZcVos(ZipCodeVO zcVOS[]) {
		for(ZipCodeVO zcVO:zcVOS) {
			System.out.println(zcVO.getZipcode());
		}
	}
	
	
	public static void main(String[] args) {

		ZipCodeVO zcVO = new ZipCodeVO();
		ZipCodeVOSimulation zsVO = new ZipCodeVOSimulation();
		zcVO.setUid_no(10); //0->10으로 초기화 (셋팅)
		zcVO.setUid_no(30); //10->30으로 초기화 (셋팅)
		int uid_no = zcVO.getUid_no();
		System.out.println(uid_no); //10->30
		
		
		zcVO.setAddress("서울시 금천구 가산동");
		String address = zcVO.getAddress();
		System.out.println(address);
		
		zcVO.setAptname("LH아파트");
		String apt = zcVO.getAptname();
		System.out.println(apt);
		zcVO.setZipcode(123456);
		int zipcode = zcVO.getZipcode();
		System.out.println(zipcode);
		zcVO.setDong("부평동");
		String dong = zcVO.getDong();
		System.out.println(dong);
		zsVO.methodA();
	}

}
