package study.prac;
public class ParentTest {

	public static void main(String[] args) {
	
/*
 * 자바에서는 생성부의 이름으로 객체가 생성된다.
 * 따라서 부모클래스 타입으로 양쪽에 있는 메소드가 호출되면 아들타입에 정의된 메소드가 호출된다.
 * 부모클래스의 메소드는 은닉메소드가 된다.
 * 그러나 만일 아들에 동일한(methodA) 메소드가 존재하지 않으면 생성된 객체는 아들객체 이지만 부모에 있는 메소드가 호출된다.
 * ArrayList는 직관적이지 않다 dvd보관 케이스를 생각하면된다  	(ㅣ ㅣ ㅣ ㅣ ㅣ  ) 위에서 본모습 몇번째 뭐가 꽂혀있는지 한 눈에 볼 수 없다.	
 * 
 * 부모에 메소드가 가지고 있으면 상속받아 쓸 수 있지만 아들만 있으면 불법이다.
 * 
 * contains 댓글쓰기에 사용한다 게시판을 원글을 눌렀을때 글 번호가 있니? 그러면 댓글을 누를 수 있게 한다 그룹번호랑 원본글번호랑은 숫자가 다르다 
 */
		
		
		
		Parent p = new Child();
		
		p.methodA();
	}

}
