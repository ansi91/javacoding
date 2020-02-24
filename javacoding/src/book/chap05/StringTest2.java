package book.chap05;

public class StringTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "apple";
		//s1.replace('p', '1'); 값이 안변한다
		s1=s1.replace('p', '1'); //좋은 방법이 아니다 StringBuilder를 사용 하도록 하자
		System.out.println(s1);	
		//s1두개가 만들어져요. 그러니까 같은 타입의 변수 두개를 관리한다
		
		StringBuilder sb = new StringBuilder("hello");
		sb.append(" world"); //원본에 붙여쓰기를 한 경우에 해당되므로 메모리 사용을 절약가능
		System.out.println(sb);
	}

}
