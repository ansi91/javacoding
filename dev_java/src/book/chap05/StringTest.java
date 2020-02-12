package book.chap05;

public class StringTest {
	/*
	 * 쿼리문을 작성할때 String을 사용하면 안되는 이유에 대해 말할 수 있다.
	 * String은 원본이 절대로 바뀌지 않는다
	 * 따라서 변경하려면 반드시 새로운변수에 그 값을 다시 담아야한다
	 * 이렇게 되면 같은 이름의 변수가 n번만큼 생성되므로 비효율적이다.
	 * 따라서 이럴때는 StringBuilder를 사용한다
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1="apple";
		String s2="apple";
		String s3= new String("apple");
		String s4=	new String("apple");
		
		System.out.println(s1==s2); //주소번지가 같은지 비교한다
		System.out.println(s3==s4); // new String으로 3,4번을 생성해서 주소번지가 서로 다르다 
		System.out.println(s3.equals(s4)); // 주소번지가 가르키는 값이 같은지 비교한다
		
		System.out.println(s1==s3);
		
	}

}
