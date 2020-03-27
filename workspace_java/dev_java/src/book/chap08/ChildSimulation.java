package book.chap08;

public class ChildSimulation {

	public static void main(String[] args) {
		//선언부의 타입이 아닌 생성부의 타입으로 객체가 생성되는 것 이다.
		Parent  p =  new Parent();
		Parent p1 = new Child();
		//도대체 child c는 Parent 클래스의 메소드와 오버라이딩 관계에 있다는 것을 어떻게 알게 되는 걸까요?
		Child	   c = new Child();
		
		//p로 누릴 수 있는 것들을 코드로 작성해 보시오
		
			p.bookRead();
			p.book = "말의 힘";
			System.out.println(p.book);
			p.bookRead(p.book, "자바프로그래밍 입문");
		
			
			//p1으로 누릴 수 있는 것들을 작성해 보시오
		/*문제 : 만일 p1으로 동일한 메소드를 호출한다면 어떤 메소드가 호출될까요? 
		 * 1)부모 클래스의 메소드가 호출된다.
		 * 2)자식 클래스의 메소드가 호출된다. o
		 */
			c.book = "자바스크립트";
			p1.bookRead();
			p1.bookRead(p.book, c.book);
			
		//c로 누릴 수 있는 변수나 메소드 들을 호출해 보시오
		/*Child에 선언되지 않은 변수이지만 상속관계에 있으므로 누릴 수 있다.
		 * 동일한 메소드가 부모와 자식 모두에게 있을 경우 선언한 타입에서 제공하는 메소드가 호출된다.
		 * 이때 부모가 가진 bookRead()메소드는 호출 할 수 없다
		 * 왜냐하면 자녀 타입으로 선언하였으므로 자식클래스에 선언된 메소드가 호출된다.
		 */
			c.car = "포르쉐 파나메라";
			
			
			c.bookRead();
			c.carDrive();
			
	}

}
