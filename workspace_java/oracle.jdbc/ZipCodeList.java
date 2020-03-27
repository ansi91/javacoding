package Oracle.jdbc;

import java.util.Vector;

public class ZipCodeList {

	public static void main(String[] args) {

		Vector<String> v = new Vector<String>();
		v.add("사과");
		v.add("딸기");
		v.add(1,"바나나"); //끼워넣기
		Vector<Object> v2 = new Vector<Object>();
		//Vector v2 = new Vector(); //<Object>(제네릭을)를 생략해도 형변환을 해줘야한다
		v2.add("사과1");
		v2.add("딸기2");
		v2.add(1,"바나나3"); //끼워넣기
		v2.remove(0);
		//v2.remove(1);
		for(int i=0;i<v2.size();i++) {
			//String f = v2.get(i); 타입이 맞지 않아서 담을수 없다 String<==>Object
			String f = (String)v2.get(i);
			System.out.println("V:"+v2.get(i));
		}
	}

}
