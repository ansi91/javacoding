package book.chap12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class A {

	//클래스 A에서 메소드 4개를 호출 해보자
	
	public static void main(String[] args) {
		B b = new B();
		ArrayList<String> al = new ArrayList<String>();
		Vector <String> v = 	   new Vector<>();
		List<String> list   = 	   new ArrayList<String>(); //싱글스레드안전    -동기화 구현이 안되어 있다. - 속도빠름
		List<String> list2 = 	   new Vector<String>();  //멀티스레드에서 안전 - 동기화 구현 -속도 느림 (도서관, 단톡방,예약 서비스)
		b.methodA(list);
		b.methodA(list2);
		b.methodA(al);
		b.methodA(v);
		
		
	}
}
