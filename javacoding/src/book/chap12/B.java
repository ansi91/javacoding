package book.chap12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
/*
 * 메소드 오버로딩은 파라미터의 갯수나 파라미터의 타입이 달라야 한다.
 * 이것은 자바언어가 같은 이름의 메소드를 여러개 운영할 수 있도록 지원하기 위해서 추가된 규칙이다
 */
public class B {

	void methodA(Collection<String> col) {
		System.out.println("methodA(Collection)호출성공 #14");
		
	}
//	void methodA(List<String> li) {
//		System.out.println("methodA(List)호출성공 #18"); //이것도 막으면 #14로 실행됨 
//	}
	
//	void methodA(ArrayList<String> al) {
//		System.out.println("methodA(ArrayList)호출 성공 #22"); 주석을 막으면 #18번으로 간다 하위 인터페이스에서 상위 인터페이스에 들어 갈 수 있다. 반대는 불가
//	}
	
	void methodA(Vector<String> v) {
		System.out.println("methodA(Vector)호출 성공 #26");
	}
}
