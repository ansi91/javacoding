package Oracle.jdbc;

import java.util.Vector;

public class ZipCodeList2 {

	public static void main(String[] args) {

		Vector<ZipCodeVO> v =new Vector<ZipCodeVO>(); //아직 방이 생성되지 않았다.
		/*Vector<String> v2= new Vector<>();
		ZipCodeVO zcVO = new ZipCodeVO();
		zcVO.setAddress("서울시 마포구 공덕동");
		zcVO.setZipcode(21545);
		v.add(0,zcVO);
		v2.add(0,"파인애플");// 타입이 맞지 않아서 오류가 뜬다.
		
		System.out.println(v2.get(0));
		System.out.println(v.get(0).getZipcode());
		System.out.println(v.get(0).getAddress());
		ZipCodeVO zVO=v.get(0);
		int zipcode = zVO.getZipcode();
		System.out.println(zipcode);*/
		
		ZipCodeVO zcVO = null; //
		ZipCodeVO zcVOS[] = null; //아직 방크기를 정할 수 없다 배열의 형태로 선언
		int i =0;
		while(i<3) {
			zcVO = new ZipCodeVO(); //방 주소를 할당 하고 있다 0~2 ZipCodeVO를 인스턴스화 한다
			
			v.add(zcVO); //벡터 변수에다가 ZipCodeVO 객체를 넣어준다
			i++;
		}
		for(int x=0;x<v.size();x++) {
			//Vector에 0번째 들어있는 주소번지는 ZipCodeVO타입이다.
			ZipCodeVO zVO=v.get(x);
			System.out.println(zVO); 
			
		}
		zcVOS = new ZipCodeVO[v.size()]; //v.size()로 배열의 크기를 초기화 해줬다.
		System.out.println("배열의 크기는 "+zcVOS.length); //3
		//zcVOS가 가르키는 객체배열에 들어있는 정보를 출력해 보세요
		
		for(int y=0;y<zcVOS.length;y++) {
			zcVOS[y] = v.get(y);
			//v.copyInto(zcVOS);
			System.out.println(zcVOS[y]); //null, null, null
		}
		/*리턴 타입이 void이지만 값을 유지하게 할 수 있다.
		 * 파라미터로 넘긴 주소번지에 v에 저장되어 있는 주소번지를 복제 해주는 메소드
		*/
		
		
		//저 배열 세개 방 안에 ZipCodeVO가 초기화 될 수 있도록 코드를 작성해 보세요
		
		
		
	
		
	}

}
